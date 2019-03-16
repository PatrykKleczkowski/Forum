package forum.service;

import forum.exception.UserIsNotAuthorException;
import forum.model.Category;
import forum.model.Topic;
import forum.repository.CategoryRepository;
import forum.repository.TopicRepository;
import forum.security.service.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void deleteTopic(Long id){
    Topic topic = topicRepository.getOne(id);
    if(topic.getTopicAuthor()==userHelper.getLoggedUser()) {
    topic.setCategory(categoryRepository.getOne((long) 99));
    topic.setEnabledForUsers(false);
    topicRepository.save(topic);}
    else throw new UserIsNotAuthorException();
    }
}
