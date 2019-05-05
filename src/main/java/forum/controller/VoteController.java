package forum.controller;

import forum.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RepositoryRestController
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/posts/{id}/voteUp")
    public ResponseEntity<?> makeVoteUp(@PathVariable("id") Long id) {
        voteService.voteLike(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/posts/{id}/voteDown")
    public ResponseEntity<?> makeVoteDown(@PathVariable("id") Long id) {
        voteService.voteDislike(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
//    @GetMapping("/users/{id}/profile/votes")
//    public ResponseEntity<?> getListOfUsersVotedToUser(@PathVariable("id") Long id){
//        return ResponseEntity.ok(this.voteService.getUserVotes(id));
//    }
}

