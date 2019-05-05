package forum.controller;

import forum.model.Message;
import forum.model.dto.NewMessageDto;
import forum.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RepositoryRestController
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("/conversations/sendMessage")
    public ResponseEntity<?> sendMessage(@RequestBody NewMessageDto newMessageDto){
       conversationService.sendMessage(newMessageDto.getId(),newMessageDto.getText());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/conversations/{id}/getMessages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable("id") Long id){
        return ResponseEntity.ok(conversationService.getMessages(id));
    }
}
