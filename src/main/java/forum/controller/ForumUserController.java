package forum.controller;

import forum.model.dto.PostDTO;
import forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ForumUserController {


    @Autowired
    private PostService postService;

//    @PostMapping("/createTopic")
//    public ResponseEntity<?> createTopic(@RequestBody PostDTO postDTO) {
//        return ResponseEntity.ok(postService.createNewTopic(postDTO));
//
//    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/createPost")
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO){
        postService.createNewPost(postDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);


    }
}
