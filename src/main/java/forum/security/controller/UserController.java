package forum.security.controller;


import forum.model.dto.ProfileUserDto;
import forum.security.model.UserCredentials;
import forum.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RepositoryRestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/signup")
    public ResponseEntity<?> saveUser(@RequestBody UserCredentials userCredentials) {
        userService.createUser(userCredentials);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/users/profile")
    public ResponseEntity<ProfileUserDto> getUserByUsername(@RequestParam("username") String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

}
