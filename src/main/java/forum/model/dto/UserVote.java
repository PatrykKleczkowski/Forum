package forum.model.dto;

import forum.model.Vote;
import forum.security.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor

@Entity
public class UserVote implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "vote_id")
    private Vote vote;

    private boolean liked;
    private boolean disliked;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserVote)) return false;
        UserVote that = (UserVote) o;
        return Objects.equals(user.getId(), that.user.getId()) &&
                Objects.equals(vote.getId(), that.vote.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(vote.getId(), user.getId(), liked, disliked);
    }


}
