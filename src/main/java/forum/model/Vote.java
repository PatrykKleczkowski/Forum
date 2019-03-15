package forum.model;

import forum.security.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int likes;

    @OneToOne(mappedBy = "vote", cascade = CascadeType.ALL)
    private Post post;

    @ManyToMany(mappedBy = "votes")
    private List<User> users = new ArrayList<>();

}
