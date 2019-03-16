package forum.service;

import forum.model.Post;
import forum.repository.PostRepository;
import forum.repository.VoteRepository;
import forum.security.model.User;
import forum.security.repository.UserRepository;
import forum.security.service.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserHelper userHelper;

    @Autowired
    private UserRepository userRepository;

    public void voteLike(Long id) {

        Post post = postRepository.getOne(id);
        User user = userHelper.getLoggedUser();

        if (user.getVotes().contains((post.getVote()))) {

            user.getVotes().remove(post.getVote());
            post.getVote().setLikes(post.getVote().getLikes() - 1);
        }

        else {
            post.getVote().setLikes(post.getVote().getLikes() + 1);
            user.addVotes(post.getVote());
        }

        saveVotes(post,user);
    }

    public void voteDislike(Long id){
        Post post = postRepository.getOne(id);
        User user = userHelper.getLoggedUser();

        if (user.getVotes().contains((post.getVote()))) {

            user.getVotes().remove(post.getVote());
            post.getVote().setDislikes(post.getVote().getDislikes() - 1);
        }

        else {
            post.getVote().setDislikes(post.getVote().getDislikes() + 1);
            user.addVotes(post.getVote());
        }

        saveVotes(post,user);
    }

    public void saveVotes(Post post, User user){
        post.setLikes(post.getVote().getLikes() - post.getVote().getDislikes());
        postRepository.save(post);
        userRepository.save(user);
    }
}
