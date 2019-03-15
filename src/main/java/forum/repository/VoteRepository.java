package forum.repository;

import forum.model.Post;
import forum.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestResource
public interface VoteRepository extends JpaRepository<Vote, Long> {
}