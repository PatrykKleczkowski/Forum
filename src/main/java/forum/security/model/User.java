package forum.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import forum.model.Comment;
import forum.model.Post;
import forum.model.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @NonNull
    private String username;

    @JsonIgnore
    @NonNull
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date registered;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date lastLogin;

    @JsonIgnore
    @ManyToOne
    @NonNull
    @JoinColumn(name = "id_role")
    private Role roles;

    @OneToMany(mappedBy = "topicAuthor", cascade = CascadeType.ALL)
    private List<Topic> topics = new ArrayList<>();

    @OneToMany(mappedBy = "postAuthor", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "postComment", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}
