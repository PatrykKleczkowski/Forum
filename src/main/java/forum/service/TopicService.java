package forum.service;

import forum.exception.UserIsNotAuthorException;
import forum.model.Category;
import forum.model.Post;
import forum.model.Topic;
import forum.model.dto.ProfileTopicsDto;
import forum.model.dto.TopicPaginationDto;
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
    @Autowired
    private PostService postService;

    public void deleteTopic(String topicTitle) {
        Topic topic = topicRepository.findByTitle(topicTitle);
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

    public void unpinTopic(Long id) {
        Topic topic = topicRepository.getOne(id);
        topic.setPinned(false);
        topicRepository.save(topic);
    }

    public Topic newestTopic(Long id) {
        Category category = categoryRepository.getOne(id);
        Date date = new Date(1919 - 01 - 17);
        Topic newestTopic = new Topic();
        for (Topic topic : category.getTopics()) {
            if (date.compareTo(topic.getTopicCreatedDate()) < 0) {
                date = topic.getTopicCreatedDate();
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
        Page<Topic> topics = topicRepository.findAllbyPostTopicIsTrue(pageable);
        return new PageImpl<>(topics.stream().map(topic -> new TopicWithPostLikes(
               topic.getTitle() , getTopicPostByTopicTitle(topic.getTitle()).getLikes()
        )).collect(Collectors.toList()), pageable, topics.getTotalElements()
        );
    }

    public Post getTopicPostByTopicTitle(String title){
        for(Post p: postRepository.findAll()){
            if(p.getTopic().getTitle()==title){
                return p;
            }
        }
        return null;
    }

    //
    public Boolean isTopicAuthor(Long id) {
        return userHelper.getLoggedUser().getTopics().contains(topicRepository.getOne(id));
    }

    public Page<ProfileTopicsDto> getUserTopics(Pageable pageable, String username) {
        Page<Topic> topics = topicRepository.findAllByReceivedTopicAuthorUsername(username, pageable);
        return new PageImpl<>(topics.stream().map(topic -> new ProfileTopicsDto(
                topic.getTitle(), topic.getTopicCreatedDate()
        )).collect(Collectors.toList()), pageable, topics.getTotalElements());
    }

    public Page<TopicPaginationDto> getPaginationTopicsPinned(Long id, Pageable pageable) {
        Page<Topic> topics = topicRepository.getTopicsByCategoryIdAndPinnedIsTrue(id, pageable);
        return convertDtoToPageImpl(topics, pageable);
    }

    public Page<TopicPaginationDto> getPaginationTopics(Long id, Pageable pageable) {
        Page<Topic> topics = topicRepository.getTopicsByCategoryIdAndPinnedIsFalse(id, pageable);
        return convertDtoToPageImpl(topics, pageable);
    }

    public Page<TopicPaginationDto> convertDtoToPageImpl(Page<Topic> topics, Pageable pageable) {

        return new PageImpl<>(topics.stream().map(topic -> new TopicPaginationDto(
                topic.getId(),
                topic.getTitle(), topic.getTopicAuthor().getUsername(),
                postService.newestPost(topic.getId()).getPostAuthor().getUsername(),
                topic.getDisplayed(), topic.isPinned(), topic.getPosts().size(),
                postService.newestPost(topic.getId()).getPostCreatedDate(),
                topic.getTopicCreatedDate()
        )).collect(Collectors.toList()), pageable, topics.getTotalElements());
    }

}