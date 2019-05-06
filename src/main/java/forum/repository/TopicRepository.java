package forum.repository;

import forum.model.Topic;
import forum.security.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TopicRepository extends JpaRepository<Topic, Long> {

    Topic findByTitle(String title);

    List<Topic> findAllByTopicAuthor(User user);

    @Query("Select t From Topic t Where t.topicAuthor.username = :username order by t.topicCreatedDate desc")
    Page<Topic> findAllByReceivedTopicAuthorUsername(@Param("username") String username, Pageable pageable);

    Page<Topic> getTopicsByCategoryIdAndPinnedIsFalse(@Param("id") Long id, Pageable pageable);

    Page<Topic> getTopicsByCategoryIdAndPinnedIsTrue(@Param("id") Long id, Pageable pageable);
}
