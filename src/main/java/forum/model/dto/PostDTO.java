package forum.model.dto;

import lombok.Data;

@Data
public class PostDTO {

    private String topicTitle;
    private String content;
    private String categoryTitle;
}
