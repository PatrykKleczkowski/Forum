package forum.controller;

import forum.model.Post;
import forum.model.dto.PostDTO;
import forum.model.dto.ProfilePostsDto;
import forum.security.service.UserHelper;
import forum.security.service.UserService;
import forum.service.NotificationService;
import forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class PostController {


    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserHelper userHelper;

    @Autowired
    private NotificationService notificationService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/posts/createPost")
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO) {

        postService.createNewPost(postDTO, false);
        userService.assignRank(userHelper.getLoggedUser());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/topics/{id}/newestPost")
    public ResponseEntity<Post> newestPostDateByTopic(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.postService.newestPost(id));
    }

    @GetMapping("/categories/{id}/newestPost")
    public ResponseEntity<Post> newestPostDateByCategory(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.postService.newestPostDateByCategory(id));
    }
    @GetMapping("/topics/{id}/withUsers")
    public ResponseEntity<?> getPostsByTopicId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.postService.getPostsByTopic(id));
    }

    @GetMapping("/users/profile/posts")
    public ResponseEntity<Page<ProfilePostsDto>> getPosts(@RequestParam("username") String username, Pageable pageable) {
        return ResponseEntity.ok(postService.getUserPosts(pageable, username)); // to get not all elements - new
        // PageRequest(0,5)
    }
}
