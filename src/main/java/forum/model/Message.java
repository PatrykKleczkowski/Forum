package forum.model;

import forum.security.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "message_content")
    private String messageContent;
    @Column(name = "send_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date sendDate;

    @JoinColumn(name = "sender_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User sender;

    @JoinColumn(name = "conversation_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Conversation conversation;

    public Message(){}
    public Message(String messageContent, Date sendDate, User sender, Conversation conversation) {
        this.messageContent= messageContent;
        this.sendDate= sendDate;
        this.sender = sender;
        this.conversation = conversation;
    }
}
