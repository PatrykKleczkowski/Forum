package forum.model;

import forum.model.dto.NotificationDTO;
import forum.security.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String message;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date dateAndTime;

    private boolean displayed;

    private NotificationType notificationType;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "received_user_id")
    private User receivedUser;

    @ManyToOne
    @JoinColumn(name = "sender_user_id")
    private User senderUser;

    public static NotificationDTO convertToDTO(Notification notification) {
        NotificationDTO dto = new NotificationDTO();
        BeanUtils.copyProperties(notification, dto);
        dto.setCategoryId(notification.getPost().getTopic().getCategory().getId());
        return dto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
