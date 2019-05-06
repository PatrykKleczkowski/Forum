package forum.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import forum.model.NotificationType;
import forum.model.Post;
import forum.security.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class NotificationDTO {

    private Long id;
    private Boolean displayed;
    @JsonProperty("notificationDate")
    private Date dateAndTime;
    private NotificationType notificationType;
    private Post post;
    private User senderUser;
    private String title;
    private String message;
    private Long categoryId;


}
