package forum.model.dto;

import lombok.Data;

@Data
public class NewCommentDTO {

    private Long post_id;
    private String commentContent;
}
