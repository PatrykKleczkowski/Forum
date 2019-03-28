package forum.service;

import forum.exception.UserIsNotAuthorException;
import forum.model.Category;
import forum.model.Post;
import forum.model.Topic;
import forum.model.dto.TopicWithPostLikes;
import forum.repository.CategoryRepository;
import forum.repository.PostRepository;
import forum.repository.TopicRepository;
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
public class TopicService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserHelper userHelper;
    @Autowired
    private PostRepository postRepository;

    public List<Topic> getTopicsFromCategory(String categoryName) {
        Category cat = categoryRepository.findByTitle(categoryName);
        List<Topic> topics = cat.getTopics();
        return topics;
    }

    public void deleteTopic(Long id) {
        Topic topic = topicRepository.getOne(id);
        if (topic.getTopicAuthor() == userHelper.getLoggedUser()) {
            topic.setCategory(categoryRepository.getOne((long) 99));
            topic.setEnabledForUsers(false);
            topicRepository.save(topic);
        } else throw new UserIsNotAuthorException();
    }

    public void pinTopic(Long id) {
        Topic topic = topicRepository.getOne(id);
        topic.setPinned(true);
        topicRepository.save(topic);
    }

    public Topic newestTopic(Long id) {
        Category category = categoryRepository.getOne(id);
        Date date = new Date(1919 - 01 - 17);
        Topic newestTopic = new Topic();
        for (Topic topic : category.getTopics()) {
            if (date.compareTo(topic.getCreatedDate()) < 0) {
                date = topic.getCreatedDate();
                newestTopic = topic;
            }
        }
        return newestTopic;
    }

    public Topic getTopic(Long id) {
        Topic topic = topicRepository.getOne(id);
        topic.setDisplayed(topic.getDisplayed() + 1);
        return topicRepository.save(topic);
    }

    public Page<TopicWithPostLikes> getPostWithLikes(Pageable pageable) {
        Page<Post> posts = postRepository.findAllByPostTopicIsTrue(pageable);
        return new PageImpl<>(posts.stream().map(post -> new TopicWithPostLikes(
                post.getTopic().getTitle(),
                post.getLikes())).collect(Collectors.toList()), pageable, posts.getTotalElements()
        );
    }

}