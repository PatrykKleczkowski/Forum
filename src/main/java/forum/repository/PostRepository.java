package forum.repository;

import forum.model.Post;
import forum.model.Topic;
import forum.security.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByPostTopicIsTrue(Pageable pageable);

    List<Post> findAllByTopicId(Long id);

    List<Post> findAllByPostAuthor(User user);
}
