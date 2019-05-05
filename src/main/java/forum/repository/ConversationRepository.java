package forum.repository;

import forum.model.Conversation;
import forum.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    boolean existsConversationByUserOneAndUserTwo(User one, User two);

    Conversation findConversationByUserOneAndUserTwo(User one, User two);
}
