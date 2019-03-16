package forum.model;

import forum.security.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "post_content")
    private String postContent;

    @JoinColumn(name = "created_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date createdDate;

    @NonNull
    @JoinColumn(name = "topic_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Topic topic;

    @NonNull
    @JoinColumn(name="author_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User postAuthor;


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToOne
    private Vote vote;

    private int likes;

    private boolean edited = false;
}
