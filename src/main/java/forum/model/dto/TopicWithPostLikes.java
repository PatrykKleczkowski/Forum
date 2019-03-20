package forum.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TopicWithPostLikes {
    private String topicTitle;
    private int likes;
}
