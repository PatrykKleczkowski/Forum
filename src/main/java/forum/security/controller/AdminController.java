package forum.security.controller;

import forum.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
@RequestMapping("/users")
public class AdminController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteAccount(@PathVariable("userId") Long id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{userId}/ban")
    public ResponseEntity<?> banAccount(@PathVariable("userId") Long id){
        userService.banUser(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{userId}/unban")
    public ResponseEntity<?> unbanAccount(@PathVariable("userId") Long id){
        userService.unbanUser(id);
        return ResponseEntity.ok().build();
    }
}
