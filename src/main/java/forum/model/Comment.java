package forum.model;

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
    @GeneratedValue
    private Long id;

    private String content;

    @JoinColumn(name = "created_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date createdDate;

    @NonNull
    @JoinColumn(name = "post_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Post post;

    @NonNull
    @JoinColumn(name="author_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User commentAuthor;
}
