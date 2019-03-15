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
    public void voteUp(Long id) {
    Post post = postRepository.getOne(id);
    User user = userHelper.getLoggedUser();
    post.getVote().setLikes(post.getVote().getLikes()+1);
    user.addVotes(post.getVote());
    post.setLikes(post.getVote().getLikes());
    postRepository.save(post);

    userRepository.save(user);
    }

    public void voteDown(Long id) {
        Post post = postRepository.getOne(id);
        User user = userHelper.getLoggedUser();
        post.getVote().setLikes(post.getVote().getLikes()-1);
        user.addVotes(post.getVote());
        post.setLikes(post.getVote().getLikes());
        postRepository.save(post);

        userRepository.save(user);
    }
}
