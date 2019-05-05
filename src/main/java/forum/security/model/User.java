package forum.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import forum.model.Comment;
import forum.model.Post;
import forum.model.Rank;
import forum.model.Topic;
import forum.model.dto.UserVote;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@AllArgsConstructor
@Where(clause = "active = true")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @NonNull
    private String username;
    @Column(name = "rank_name")
    @Enumerated(value = EnumType.STRING)
    private Rank rank = Rank.NOWY;
    @JsonIgnore
    @NonNull
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date registered;

    @JoinColumn(name = "last_login")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date lastLogin;

    private int points;

    @JsonIgnore
    @ManyToOne
    @NonNull
    @JoinColumn(name = "id_role")
    private Role roles;

    @JsonIgnore
    @OneToMany(mappedBy = "topicAuthor", cascade = CascadeType.ALL)
    private List<Topic> topics = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "postAuthor", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "commentAuthor", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserVote> userVotes = new ArrayList<>();

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "receivedUser")
    // private Set<Notification> receivedNotifications;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "senderUser")
    // private Set<Notification> senderNotifications;



    private boolean banned = false;
    private boolean active = true;

    public User(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = role;
    }

    public User() {
    }

    public void addVotes(UserVote vote) {
        if (vote != null) {
            userVotes.add(vote);
        }
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isBanned() {
        return this.banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
