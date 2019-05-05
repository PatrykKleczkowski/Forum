package forum.service;

import forum.model.Conversation;
import forum.model.Message;
import forum.repository.ConversationRepository;
import forum.repository.MessageRepository;
import forum.security.model.User;
import forum.security.repository.UserRepository;
import forum.security.service.UserHelper;
import forum.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserHelper userHelper;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    public void sendMessage(Long id, String text){
        User loggedUser = userHelper.getLoggedUser();
        User user = userRepository.getOne(id);
        Conversation conversation = getConversation(loggedUser,user);
        if(conversation==null) {
                conversation = new Conversation(loggedUser,user, new Date());
            }

        conversationRepository.save(conversation);
        Message message = new Message(text, new Date(), loggedUser, conversation);
        messageRepository.save(message);
    }

    public List<Message> getMessages(Long id){
        User loggedUser = userHelper.getLoggedUser();
        User user = userRepository.getOne(id);
        Conversation conversation = getConversation(loggedUser,user);
        return conversation.getConversationMessages();
    }

    public Conversation getConversation(User loggedUser, User user){

        if(conversationRepository.existsConversationByUserOneAndUserTwo(loggedUser,user)){
            return  conversationRepository.findConversationByUserOneAndUserTwo(loggedUser,user);
        }
        else if(conversationRepository.existsConversationByUserOneAndUserTwo(user, loggedUser)){
           return conversationRepository.findConversationByUserOneAndUserTwo(user, loggedUser);
        }
        else return null;
    }
}
