package forum.model.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TopicPaginationDto {
    private Long id;
    private String title;
    private String topicAuthor;
    private String postAuthor;
    private int displayed;
    private boolean pinned;
    private int answers;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date postCreatedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date topicCreatedDate;


}
