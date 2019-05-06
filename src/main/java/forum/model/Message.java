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
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "message_content")
    private String messageContent;
    @Column(name = "send_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date sendDate;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User sender;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Conversation conversation;

}
