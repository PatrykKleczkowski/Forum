package forum.service;

import forum.model.Post;
import forum.model.dto.UserVote;
import forum.repository.PostRepository;
import forum.security.model.User;
import forum.security.repository.UserRepository;
import forum.security.service.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserHelper userHelper;

    @Autowired
    private UserRepository userRepository;


    public void voteDislike(Long id) {
        Post post = postRepository.getOne(id);
        User user = userHelper.getLoggedUser();
        UserVote userVote = new UserVote();
        boolean find = true;
        List<UserVote> userVoteList = user.getUserVotes();
        for (UserVote userVotes : userVoteList) {
            if (userVotes.getVote() == post.getVote()) {
                userVote = userVotes;
                find = false;
            }
        }
        if (find) {
            post.getVote().setDislikes(post.getVote().getDislikes() + 1);
            userVote.setVote(post.getVote());
            userVote.setDisliked(true);
            userVote.setLiked(false);
            userVote.setUser(user);
            user.addVotes(userVote);
            changePoints(post, -1);
        } else {
            if (userVote.isLiked()) {
                post.getVote().setLikes(post.getVote().getLikes() - 1);
                post.getVote().setDislikes(post.getVote().getDislikes() + 1);
                userVote.setDisliked(true);
                userVote.setLiked(false);
                changePoints(post, -2);

            } else if (userVote.isDisliked()) {
                post.getVote().setDislikes(post.getVote().getDislikes() - 1);
                userVote.setDisliked(false);
                changePoints(post, 1);
            } else {
                post.getVote().setDislikes(post.getVote().getDislikes() + 1);
                userVote.setDisliked(true);
                changePoints(post, -1);
            }
        }
        saveVotes(post, user);
    }

    public void voteLike(Long id) {
        Post post = postRepository.getOne(id);
        User user = userHelper.getLoggedUser();
        UserVote userVote = new UserVote();
        boolean find = true;
        List<UserVote> userVoteList = user.getUserVotes();
        for (UserVote userVotes : userVoteList) {
            if (userVotes.getVote() == post.getVote()) {
                userVote = userVotes;
                find = false;
            }
        }

        if (find) {
            post.getVote().setLikes(post.getVote().getLikes() + 1);
            userVote.setVote(post.getVote());
            userVote.setDisliked(false);
            userVote.setLiked(true);
            userVote.setUser(user);
            user.addVotes(userVote);
            changePoints(post, 1);
        } else {
            if (userVote.isDisliked()) {
                post.getVote().setDislikes(post.getVote().getDislikes() - 1);
                post.getVote().setLikes(post.getVote().getLikes() + 1);
                userVote.setDisliked(false);
                userVote.setLiked(true);
                changePoints(post, 2);
            } else if (userVote.isLiked()) {
                post.getVote().setLikes(post.getVote().getLikes() - 1);
                userVote.setLiked(false);
                changePoints(post, -1);
            } else {
                post.getVote().setLikes(post.getVote().getLikes() + 1);
                userVote.setLiked(true);
                changePoints(post, 1);
            }
        }

        saveVotes(post, user);
    }

    public void saveVotes(Post post, User user) {
        post.setLikes(post.getVote().getLikes() + (-post.getVote().getDislikes()));
        postRepository.save(post);
        userRepository.save(user);
    }

    private void changePoints(Post post, int i) {
        post.getPostAuthor().setPoints(post.getPostAuthor().getPoints() + i);
    }

}