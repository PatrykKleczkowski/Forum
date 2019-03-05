package forum.controller;

import forum.model.dto.PostDTO;
import forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class ForumUserController {


    @Autowired
    private PostService postService;

//    @PostMapping("/createTopic")
//    public ResponseEntity<?> createTopic(@RequestBody PostDTO postDTO) {
//        return ResponseEntity.ok(postService.createNewTopic(postDTO));
//
//    }

    @PostMapping("/createPost")
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO){
        return ResponseEntity.ok(postService.createNewPost(postDTO));


    }
}
