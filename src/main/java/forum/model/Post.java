package forum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import forum.security.model.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "post_content")
    private String postContent;

    @JoinColumn(name = "post_created_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date postCreatedDate;


    @NonNull
    @JoinColumn(name = "topic_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Topic topic;

    @NonNull
    @JoinColumn(name = "author_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User postAuthor;


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @JsonIgnore
    @OneToOne
    private Vote vote;

    private int likes;

    private boolean edited = false;

    @JoinColumn(name = "post_topic")
    private boolean postTopic = false;
}
