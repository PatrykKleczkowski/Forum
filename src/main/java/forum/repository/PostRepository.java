package forum.repository;

import forum.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByPostTopicIsTrue(Pageable pageable);

    @Query("Select p From Post p Where p.postAuthor.username = :username order by p.postCreatedDate desc")
    Page<Post> findAllByReceivedPostAuthorUsername(@Param("username") String username, Pageable pageable);

    List<Post> findAllByTopicId(Long id);
}
