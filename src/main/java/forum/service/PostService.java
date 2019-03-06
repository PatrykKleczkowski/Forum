package forum.service;

import forum.model.Post;
import forum.model.Topic;
import forum.model.dto.PostDTO;
import forum.repository.CategoryRepository;
import forum.repository.PostRepository;
import forum.repository.TopicRepository;
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
    private UserRepository userRepository;

//    public Topic createNewTopic(PostDTO postDTO) {
//        Topic topic = new Topic();
//        topic.setTitle(postDTO.getTopicTitle());
//        topic.setCategory(getCategoryFromDto(postDTO.getCategoryTitle()));
//        User loggedUser = userHelper.getLoggedUser();
//        topic.setTopicAuthor(loggedUser);
//        topic.setCreatedDate(new Date());
//
//        topicRepository.save(topic);
//        createNewPost(postDTO.getContent(), topic);
//
//        return topicRepository.save(topic);
//    }

//    private Category getCategoryFromDto(String categoryName) {
//        return categoryRepository.findByTitle(categoryName);
//
//    }

    public Post createNewPost(PostDTO postDTO) {
        Post newPost = new Post();
        newPost.setPostContent(postDTO.getContent());
        newPost.setCreatedDate(new Date());
        newPost.setTopic(getTopicFromTitle(postDTO.getTopicTitle()));
        User loggedUser = userHelper.getLoggedUser();
        newPost.setPostAuthor(loggedUser);

        return postRepository.save(newPost);
    }

//    private Topic addPostToTopic(Post post, Topic topic){
//        topic.getPosts().add(post);
//        return topic;
//    }

    private Topic getTopicFromTitle(String title) {
        return topicRepository.findByTitle(title);
    }


}
