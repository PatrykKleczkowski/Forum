package forum.repository;

import forum.model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query("SELECT n FROM Notification n WHERE n.receivedUser.username = :username "
            + "AND n.displayed = false ORDER BY n.dateAndTime DESC")
    Page<Notification> findAllByReceivedUserUsername(@Param("username") String username, Pageable pageable);
}
