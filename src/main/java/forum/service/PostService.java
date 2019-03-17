package forum.service;

import forum.exception.CategoryIsNotEnabledToAddTopic;
import forum.exception.TopicIsNotEnabledToAddPost;
import forum.model.*;
import forum.model.dto.PostDTO;
import forum.repository.CategoryRepository;
import forum.repository.PostRepository;
import forum.repository.TopicRepository;
import forum.repository.VoteRepository;
import forum.security.model.User;
import forum.security.service.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    public Topic createNewTopic(PostDTO postDTO) {
        Category category = categoryRepository.getOne(postDTO.getCategoryId());
        if(category.getId()==99) throw new CategoryIsNotEnabledToAddTopic();
        Topic topic = new Topic();
        topic.setTitle(postDTO.getTopicTitle());
        topic.setCategory(category);
        User loggedUser = userHelper.getLoggedUser();
        topic.setTopicAuthor(loggedUser);
        topic.setCreatedDate(new Date());
        category.setSize(category.getSize()+1);
        topicRepository.save(topic);

        addPostToTopic(createNewPost(postDTO), topic);

        return topicRepository.save(topic);
    }

    public Post createNewPost(PostDTO postDTO) {
        Topic topic = getTopicFromTitle(postDTO.getTopicTitle());
        if(!topic.isEnabledForUsers()) throw new TopicIsNotEnabledToAddPost();

        Post newPost = new Post();
        newPost.setPostContent(postDTO.getContent());
        newPost.setCreatedDate(new Date());
        newPost.setTopic(topic);
        User loggedUser = userHelper.getLoggedUser();
        newPost.setPostAuthor(loggedUser);
        Vote vote = createNewVote();
        newPost.setVote(vote);

        return postRepository.save(newPost);
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
        Date date = new Date(1919-01-17);
        Post newestPost = new Post();
        for (Post post : topic.getPosts()) {
            if (date.compareTo(post.getCreatedDate())<0) {
                date = post.getCreatedDate();
                newestPost = post;
            }
        }
        return newestPost;
    }
}
