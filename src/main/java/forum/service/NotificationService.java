package forum.service;

import forum.model.dto.PostDTO;
import forum.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {


    @Autowired
    private NotificationRepository notificationRepository;


    public void SendNotificationForUser(PostDTO postDTO) {

    }

}
