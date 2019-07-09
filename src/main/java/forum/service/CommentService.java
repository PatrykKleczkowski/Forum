package forum.service;

import forum.model.Comment;
import forum.model.Post;
import forum.model.dto.NewCommentDTO;
import forum.repository.CommentRepository;
import forum.repository.PostRepository;
import forum.security.model.User;
import forum.security.service.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserHelper userHelper;

    @Autowired
    private NotificationService notificationService;


    public Comment createNewComment(NewCommentDTO newCommentDTO) {
        User loggedUser = userHelper.getLoggedUser();
        Post post = postRepository.getOne(newCommentDTO.getPost_id());

        Comment newComment = new Comment();
        newComment.setCommentAuthor(loggedUser);
        newComment.setCommentContent(newCommentDTO.getCommentContent());
        newComment.setCreatedDate(new Date());
        newComment.setPost(post);

        if (!(userHelper.getLoggedUser().getId() == post.getPostAuthor().getId())) {
            notificationService.sendNewCommentNotification(newComment.getPost(), newComment);
        }

        return commentRepository.save(newComment);
    }
}
