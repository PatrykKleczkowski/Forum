package forum.service;

import forum.model.Category;
import forum.model.Post;
import forum.model.Topic;
import forum.model.Vote;
import forum.model.dto.PostDTO;
import forum.repository.CategoryRepository;
import forum.repository.PostRepository;
import forum.repository.TopicRepository;
import forum.repository.VoteRepository;
import forum.security.model.User;
import forum.security.repository.UserRepository;
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
        Topic topic = new Topic();
        topic.setTitle(postDTO.getTopicTitle());
        topic.setCategory(getCategoryFromDto(postDTO.getCategoryTitle()));
        User loggedUser = userHelper.getLoggedUser();
        topic.setTopicAuthor(loggedUser);
        topic.setCreatedDate(new Date());

        topicRepository.save(topic);
        addPostToTopic(createNewPost(postDTO), topic);

        return topicRepository.save(topic);
    }

    public Post createNewPost(PostDTO postDTO) {
        Post newPost = new Post();
        newPost.setPostContent(postDTO.getContent());
        newPost.setCreatedDate(new Date());
        newPost.setTopic(getTopicFromTitle(postDTO.getTopicTitle()));
        User loggedUser = userHelper.getLoggedUser();
        newPost.setPostAuthor(loggedUser);
        Vote vote =createNewVote();
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


    private Topic addPostToTopic(Post post, Topic topic){
        topic.getPosts().add(post);
        return topic;
    }

    private Topic getTopicFromTitle(String title) {
        return topicRepository.findByTitle(title);
    }

    private Vote createNewVote(){
        Vote vote = new Vote();
        return voteRepository.save(vote);
    }

}
