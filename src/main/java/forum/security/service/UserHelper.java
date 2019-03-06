package forum.security.service;

import forum.security.model.User;
import forum.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class UserHelper {

    @Autowired
    private UserRepository userRepository;

    public String getLoggedUserUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null)
            return null;

        Object principal = authentication.getPrincipal();

        if (principal.getClass() == UserDetails.class)
            return ((UserDetails) principal).getUsername();

        if (principal.getClass() == String.class)
            return principal.toString();

        return null;
    }


    public User getLoggedUser() {
        return userRepository.findByUsername(getLoggedUserUsername());
    }



}
