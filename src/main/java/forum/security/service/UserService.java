package forum.security.service;

import forum.model.Rank;
import forum.model.dto.ProfileUserDto;
import forum.repository.PostRepository;
import forum.repository.TopicRepository;
import forum.security.exception.UsernameAlreadyExistsException;
import forum.security.model.User;
import forum.security.model.UserCredentials;
import forum.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;


    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        if (user.isBanned()) {
            throw new UsernameNotFoundException("Your account has been banned");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    public Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoles().getName()));

        return authorities;
    }


    public User createUser(UserCredentials user) {
        checkUsername(user.getUsername());
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setRoles(roleService.getUserRole());
        newUser.setRegistered(new Date());
        userRepository.save(newUser);
        return userRepository.save(newUser);
    }

    private void checkUsername(String username) {
        boolean usernameExists = userRepository.existsUserByUsername(username);

        if (usernameExists) throw new UsernameAlreadyExistsException();
    }

    public void assignRank(User user) {
        int numberOfPosts = user.getPosts().size();
        switch (numberOfPosts) {
            case 1:
                user.setRank(Rank.POCZATKUJACY);
                break;
            case 2:
                user.setRank(Rank.JUNIOR);
                break;
            case 3:
                user.setRank(Rank.MID);
                break;
            case 4:
                user.setRank(Rank.SENIOR);
                break;
            case 5:
                user.setRank(Rank.TEAMLEADER);
                break;
            case 6:
                user.setRank(Rank.MENADZER);
                break;
            default:
                user.setRank(Rank.EXPERT);
        }
        userRepository.save(user);
    }


    public User deleteUser(Long id) {
        User user = userRepository.getOne(id);

        if (user == null || !user.isActive()) {
            throw new UsernameNotFoundException("User doesn't exist");
        }
        user.setActive(false);
        return userRepository.save(user);
    }

    public void banUser(Long id) {
        User user = userRepository.getOne(id);

        if (user == null || user.isBanned()) {
            throw new UsernameNotFoundException("User doesn't exist or its actually banned");
        }
        user.setBanned(true);
        userRepository.save(user);
    }

    public void unbanUser(Long id) {
        User user = userRepository.getOne(id);

        if (user == null || (!user.isBanned())) {
            throw new UsernameNotFoundException("User doesn't exists or itsnt actually banned");
        }
        user.setBanned(false);
        userRepository.save(user);
    }

    public ProfileUserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return new ProfileUserDto(user.getUsername(), user.getRank(), user.getRegistered(), user.getLastLogin(),
                user.getPoints());
    }
}

