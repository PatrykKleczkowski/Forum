package forum.model;

import forum.security.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor

public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_one_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User userOne;
    @JoinColumn(name = "user_two_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User userTwo;


    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    private List<Message> conversationMessages = new ArrayList<>();

    @Column(name = "conversation_created_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date conversationCreatedDate;

    public Conversation(){}

    public Conversation(User userOne, User userTwo, Date conversationCreatedDate){
        this.userOne = userOne;
        this.userTwo = userTwo;
        this.conversationCreatedDate = conversationCreatedDate;
    }
}
