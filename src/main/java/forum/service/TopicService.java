package forum.service;

import forum.exception.UserIsNotAuthorException;
import forum.model.Category;
import forum.model.Post;
import forum.model.Topic;
import forum.model.dto.TopicWithPostLikes;
import forum.repository.CategoryRepository;
import forum.repository.TopicRepository;
import forum.security.service.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Topic getTopic(Long id){
        Topic topic = topicRepository.getOne(id);
        topic.setDisplayed(topic.getDisplayed()+1);
        return topicRepository.save(topic);
    }

    public Page<TopicWithPostLikes> getTopicWithLikes(Pageable pageable){
        List<Topic> topics = topicRepository.findAll();
        List<TopicWithPostLikes> topicsWithPostLikes = new ArrayList<>();
        Post post =null;

        for(Topic topic: topics){
            if(topic.getPosts().iterator().hasNext())
                post=topic.getPosts().iterator().next();
            if(post.isPostTopic()){
            TopicWithPostLikes topicWithLikes = new TopicWithPostLikes();

            if(post.getId()!=null){
            topicWithLikes.setLikes(post.getLikes());}
            topicWithLikes.setTopicTitle(topic.getTitle());
            topicsWithPostLikes.add(topicWithLikes);
        }}
        final Page<TopicWithPostLikes> topicWithPostLikes= new PageImpl<TopicWithPostLikes>(topicsWithPostLikes,pageable,
                topicsWithPostLikes.size());
        return topicWithPostLikes;
    }
}