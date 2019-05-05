package forum.service;

import forum.model.Notification;
import forum.model.NotificationType;
import forum.model.Post;
import forum.repository.NotificationRepository;
import forum.security.service.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class NotificationService {


    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserHelper userHelper;

    @Transactional
    public void sendNewPostNotification(Post post) {
        Notification notification = createNewPostNotification(post);
        notificationRepository.save(notification);
    }

    @Transactional
    public void sendNewCommentNotification(Post post) {
        Notification notification = createNewCommentNotification(post);
        notificationRepository.save(notification);
    }

    public Page<Notification> findByCurrentUserUsername(String username, Pageable pageable) {
        return this.notificationRepository.findAllByReceivedUserUsername(username, pageable);
    }

    public Notification setAsDisplayed(Long notificationId) {
        Notification notification = notificationRepository.getOne(notificationId);
        notification.setDisplayed(true);
        return notificationRepository.save(notification);
    }


    private Notification createNewPostNotification(Post post) {
        return new Notification(null,
                "Nowy post",
                " dodał post do Twojego tematu",
                new Date(),
                false,
                NotificationType.NEWPOST,
                post,
                post.getTopic().getTopicAuthor(),
                userHelper.getLoggedUser()
        );
    }

    private Notification createNewCommentNotification(Post post) {
        return new Notification(null,
                "Komentarz",
                " skomentował Twój post",
                new Date(),
                false,
                NotificationType.NEWCOMMENT,
                post,
                post.getTopic().getTopicAuthor(),
                userHelper.getLoggedUser()
        );
    }


}
