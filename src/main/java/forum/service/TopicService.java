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
import forum.security.repository.UserRepository;
import forum.security.service.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
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
        Page<Post> posts = postRepository.findAllByPostTopicIsTrue(pageable);
        return new PageImpl<>(posts.stream().map(post -> new TopicWithPostLikes(
                post.getTopic().getTitle(), post.getLikes()
        )).collect(Collectors.toList()), pageable, posts.getTotalElements()
        );
    }

    public Page<TopicPaginationDto> getPaginationTopics(Long id, Pageable pageable){
        Page<Topic> topics = topicRepository.getTopicsByCategoryId(id, pageable);

        return new PageImpl<>(topics.stream().map(topic -> new TopicPaginationDto(
                topic.getId(),
                topic.getTitle(), topic.getTopicAuthor().getUsername(),
                newestPost(topic.getId()).getPostAuthor().getUsername(),
                topic.getDisplayed(), topic.isPinned(), topic.getPosts().size(),
                newestPost(topic.getId()).getPostCreatedDate(),
                topic.getTopicCreatedDate()
        )).collect(Collectors.toList()), pageable, topics.getTotalElements());
    }
//
    public Boolean isTopicAuthor(Long id){
        return userHelper.getLoggedUser().getTopics().contains(topicRepository.getOne(id));
    }

    public Page<ProfileTopicsDto> getUserTopics(Pageable pageable, String username){
        Page<Topic> topics = topicRepository.findAllByReceivedTopicAuthorUsername(username, pageable);
        return new PageImpl<>(topics.stream().map(topic -> new ProfileTopicsDto(
                topic.getTitle(), topic.getTopicCreatedDate()
        )).collect(Collectors.toList()),pageable, topics.getTotalElements());
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
}