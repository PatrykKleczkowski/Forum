package forum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import forum.security.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

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

   // @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    //private Set<Notification> notifications;

    private int likes;

    private boolean edited = false;

    @JoinColumn(name = "post_topic")
    private boolean postTopic = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
