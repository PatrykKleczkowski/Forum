package forum.controller;

import forum.model.Post;
import forum.model.Topic;
import forum.model.dto.PostDTO;
import forum.security.service.UserHelper;
import forum.security.service.UserService;
import forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class PostController {


    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserHelper userHelper;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/createPost")
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO) {

        postService.createNewPost(postDTO, false);
        userService.assignRank(userHelper.getLoggedUser());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/topics/{id}/newestPost")
    public ResponseEntity<Post> newestPostDateByTopic(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.postService.newestPost(id));
    }

    @GetMapping("/category/{id}/newestPost")
    public ResponseEntity<Post> newestPostDateByCategory(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.postService.newestPostDateByCategory(id));
    }
    @GetMapping("/topics/{id}/withUsers")
    public ResponseEntity<?> getPostsByTopicId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.postService.getPostsByTopic(id));
    }
}
