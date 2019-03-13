package forum.controller;

import forum.model.Topic;
import forum.model.dto.PostDTO;
import forum.service.PostService;
import forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RepositoryRestController
public class TopicController {

    @Autowired
    private PostService postService;

    @Autowired
    private TopicService topicService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/createTopic")
    public ResponseEntity<?> createTopic(@RequestBody PostDTO postDTO) {
        postService.createNewTopic(postDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/categories")
    public ResponseEntity<List<Topic>> getTopicsFromCategory(@RequestParam("categoryName") String categoryName) {

        return ResponseEntity.ok(topicService.getTopicsFromCategory(categoryName));
    }
}
