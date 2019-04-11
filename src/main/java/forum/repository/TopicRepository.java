package forum.repository;

import forum.model.Topic;
import forum.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Topic findByTitle(String title);

    List<Topic> findAllByTopicAuthor(User user);
}
