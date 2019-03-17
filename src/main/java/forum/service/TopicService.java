package forum.service;

import forum.exception.UserIsNotAuthorException;
import forum.model.Category;
import forum.model.Topic;
import forum.repository.CategoryRepository;
import forum.repository.TopicRepository;
import forum.security.service.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TopicService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserHelper userHelper;

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
        Date date = new Date(1919-01-17);
        Topic newestTopic = new Topic();
        for (Topic topic : category.getTopics()) {
            if (date.compareTo(topic.getCreatedDate())<0) {
            date = topic.getCreatedDate();
            newestTopic=topic;
            }
        }
        return newestTopic;
    }
}