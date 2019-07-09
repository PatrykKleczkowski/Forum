package forum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import forum.model.dto.UserVote;
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
    private int dislikes;

    @JsonIgnore
    @OneToOne(mappedBy = "vote", cascade = CascadeType.ALL)
    private Post post;

    @OneToMany(mappedBy = "vote", cascade = CascadeType.ALL)
    private List<UserVote> userVotes = new ArrayList<>();

}
