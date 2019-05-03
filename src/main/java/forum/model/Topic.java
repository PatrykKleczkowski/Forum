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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;


    @NonNull
    @JoinColumn(name = "author_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User topicAuthor;


    @JsonIgnore
    @NonNull
    @JoinColumn(name = "category_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @JoinColumn(name = "topic_created_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date topicCreatedDate;

    @JoinColumn(name = "enabled_for_users")
    private boolean enabledForUsers = true;
    private boolean pinned = false;
    private int displayed;

}
