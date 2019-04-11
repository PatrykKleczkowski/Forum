package forum.model.dto;

import forum.model.Post;
import forum.model.Topic;
import forum.security.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileDto {
    private User user;
    private List<Topic> topics = new ArrayList<>();
    private List<Post> posts = new ArrayList<>();
}
