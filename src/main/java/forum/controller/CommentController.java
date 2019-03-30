package forum.controller;

import forum.model.dto.NewCommentDTO;
import forum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/posts/id/comments")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable("id") Long id) {

        return ResponseEntity.ok(this.commentService.getCommentsById(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/createComment")
    public ResponseEntity<?> createComment(@RequestBody NewCommentDTO newCommentDTO) {
        commentService.createNewComment(newCommentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
