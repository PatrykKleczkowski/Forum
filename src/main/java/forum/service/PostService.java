package forum.service;

import forum.exception.CategoryIsNotEnabledToAddTopic;
import forum.exception.PostContentCannotBeNull;
import forum.exception.TopicIsNotEnabledToAddPost;
import forum.exception.TopicTitleExists;
import forum.model.Category;
import forum.model.Post;
import forum.model.Topic;
import forum.model.Vote;
import forum.model.dto.PostDTO;
import forum.model.dto.ProfilePostsDto;
import forum.repository.CategoryRepository;
import forum.repository.PostRepository;
import forum.repository.TopicRepository;
import forum.repository.VoteRepository;
import forum.security.model.User;
import forum.security.service.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserHelper userHelper;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private NotificationService notificationService;

    public Topic createNewTopic(PostDTO postDTO) {
        Category category = categoryRepository.getOne(postDTO.getCategoryId());
        if (postDTO.getContent() == null) throw new PostContentCannotBeNull();
        if (category.getId() == 99) throw new CategoryIsNotEnabledToAddTopic();
        for (Topic t : topicRepository.findAll()) {
            if (t.getTitle().equals(postDTO.getTopicTitle())) throw new TopicTitleExists();
        }

        Topic topic = new Topic();
        topic.setTitle(postDTO.getTopicTitle());
        topic.setCategory(category);
        User loggedUser = userHelper.getLoggedUser();
        topic.setTopicAuthor(loggedUser);
        topic.setTopicCreatedDate(new Date());
        category.setSize(category.getSize() + 1);
        topicRepository.save(topic);

        addPostToTopic(createNewPost(postDTO, true), topic);

        return topicRepository.save(topic);
    }

    public Post createNewPost(PostDTO postDTO, boolean topicPost) {
        Topic topic = getTopicFromTitle(postDTO.getTopicTitle());
        if (!topic.isEnabledForUsers()) throw new TopicIsNotEnabledToAddPost();

        Post newPost = new Post();
        newPost.setPostContent(postDTO.getContent());
        newPost.setPostCreatedDate(new Date());
        newPost.setTopic(topic);
        newPost.setPostTopic(topicPost);
        User loggedUser = userHelper.getLoggedUser();
        newPost.setPostAuthor(loggedUser);
        Vote vote = createNewVote();
        newPost.setVote(vote);

        postRepository.save(newPost);
        notificationService.sendNewPostNotification(newPost);
        return newPost;
    }

    private Category getCategoryFromDto(String categoryTitle) {
        Category existedCategory = categoryRepository.findByTitle(categoryTitle);

        if (existedCategory == null) {
            return createNewCategory(categoryTitle);
        }
        return existedCategory;
    }

    private Category createNewCategory(String categoryTitle) {
        Category newCategory = new Category();
        newCategory.setTitle(categoryTitle);
        return categoryRepository.save(newCategory);
    }


    private Topic addPostToTopic(Post post, Topic topic) {
        topic.getPosts().add(post);
        return topic;
    }

    private Topic getTopicFromTitle(String title) {
        return topicRepository.findByTitle(title);
    }

    private Vote createNewVote() {
        Vote vote = new Vote();
        return voteRepository.save(vote);
    }

    public Post newestPost(Long id) {
        Topic topic = topicRepository.getOne(id);
        Date date = new Date(1919 - 01 - 17);
        Post newestPost = new Post();
        for (Post post : topic.getPosts()) {
            if (date.compareTo(post.getPostCreatedDate()) < 0) {
                date = post.getPostCreatedDate();
                newestPost = post;
            }
        }
        return newestPost;
    }

    public List<Post> getPostsByTopic(Long id) {
        return postRepository.findAllByTopicId(id);
    }

    public Post newestPostDateByCategory(Long id) {
        Category category = categoryRepository.getOne(id);
        Date date = new Date(1919 - 01 - 17);
        Post newestPost = new Post();
        for (Topic t : category.getTopics()) {
            for (Post post : t.getPosts()) {
                if (date.compareTo(post.getPostCreatedDate()) < 0) {
                    date = post.getPostCreatedDate();
                    newestPost = post;
                }
            }
        }
        return newestPost;
    }

    public Page<ProfilePostsDto> getUserPosts(Pageable pageable, String username) {
        Page<Post> posts = postRepository.findAllByReceivedPostAuthorUsername(username, pageable);

        return new PageImpl<>(posts.stream().map(post -> new ProfilePostsDto(
                post.getTopic().getTitle(), post.getPostContent(), post.getPostCreatedDate()
        )).collect(Collectors.toList()), pageable, posts.getTotalElements());
    }

   /// public Page<ProfilePostsDto> getUserPosts(Pageable pageable, Long id){
//        User user = userRepository.getOne(id);
//        Page<Post> posts = postRepository.findAllByPostAuthor(user, pageable);
//        return new PageImpl<>(posts.stream().map(post -> new ProfilePostsDto(
//                post.getTopic().getTitle(),post.getPostContent(),post.getCreatedDate()
//        )).collect(Collectors.toList()),pageable, posts.getTotalElements());
//    }
}

