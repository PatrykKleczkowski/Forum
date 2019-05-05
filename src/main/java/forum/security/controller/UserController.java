package forum.security.controller;


import forum.model.dto.NotificationDTO;
import forum.model.dto.ProfileUserDto;
import forum.security.model.User;
import forum.security.model.UserCredentials;
import forum.security.service.UserService;
import forum.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/users/signup")
    public ResponseEntity<?> saveUser(@RequestBody UserCredentials userCredentials) {
        userService.createUser(userCredentials);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/users/profile")
    public ResponseEntity<ProfileUserDto> getUserByUsername(@RequestParam("username") String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/notifications")
    public ResponseEntity<Page<NotificationDTO>> getNotifications(Pageable pageable) {
        return ResponseEntity.ok(this.userService.getNotifications(pageable));
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/notifications/{notificationId}/setAsDisplayed")
    public ResponseEntity<?> setNotificationAsDisplayed(@PathVariable("notificationId") Long id){
        return ResponseEntity.ok(notificationService.setAsDisplayed(id));
    }

}
