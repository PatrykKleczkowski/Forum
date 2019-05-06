package forum.controller;

import forum.model.Topic;
import forum.model.dto.PostDTO;
import forum.model.dto.ProfileTopicsDto;
import forum.model.dto.TopicPaginationDto;
import forum.model.dto.TopicWithPostLikes;
import forum.service.PostService;
import forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class TopicController {

    @Autowired
    private PostService postService;

    @Autowired
    private TopicService topicService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/topics/createTopic")
    public ResponseEntity<?> createTopic(@RequestBody PostDTO postDTO) {
        postService.createNewTopic(postDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("/topics/delete")
    public ResponseEntity<?> deletTopic(@RequestParam("topicTitle") String topicTitle) {
        topicService.deleteTopic(topicTitle);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/topics/{id}/pin")
    public ResponseEntity<?> pinnTopic(@PathVariable("id") Long id) {
        topicService.pinTopic(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/topics/{id}/unpin")
    public ResponseEntity<?> unpinnTopic(@PathVariable("id") Long id) {
        topicService.unpinTopic(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/categories/{id}/newestTopic")
    public ResponseEntity<Topic> newestTopic(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.topicService.newestTopic(id));
    }

    @GetMapping("/topics/{id}")
    public ResponseEntity<Topic> getTopic(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.topicService.getTopic(id));
    }

    @GetMapping("/topics/mostLikes")
    public ResponseEntity<Page<TopicWithPostLikes>> getTopicsWithMostLikes(Pageable pageable) {
        return ResponseEntity.ok(topicService.getPostWithLikes(pageable));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/topics/{id}/isAuthor")
    public ResponseEntity<Boolean> isTopicAuthor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(topicService.isTopicAuthor(id));
    }

    @GetMapping("/users/profile/topics")
    public ResponseEntity<Page<ProfileTopicsDto>> getTopicsToProfile(@RequestParam("username") String username,
                                                                     Pageable pageable) {
        return ResponseEntity.ok(topicService.getUserTopics(pageable, username));
    }

    @GetMapping("/topics/{id}/paging")
    public ResponseEntity<Page<TopicPaginationDto>> getPagedTopics(@PathVariable("id") Long id,
                                                                   Pageable pageable) {
        return ResponseEntity.ok(topicService.getPaginationTopics(id, pageable));
    }


    @GetMapping("/topics/{id}/pinned")
    public ResponseEntity<Page<TopicPaginationDto>> getPagedTopicsPinned(@PathVariable("id") Long id,
                                                                         Pageable pageable) {
        return ResponseEntity.ok(topicService.getPaginationTopicsPinned(id, pageable));
    }
}
