package forum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import forum.security.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "comment_content")
    private String commentContent;

    @JoinColumn(name = "created_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date createdDate;

    @JsonIgnore
    @NonNull
    @JoinColumn(name = "post_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Post post;

    @JsonIgnore
    @NonNull
    @JoinColumn(name = "author_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private User commentAuthor;
}
