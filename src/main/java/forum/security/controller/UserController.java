package forum.security.controller;

import forum.security.model.UserCredentials;
import forum.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RepositoryRestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users/signup")
    public ResponseEntity<?> savePatient(@RequestBody UserCredentials userCredentials) {
        userService.createUser(userCredentials);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
