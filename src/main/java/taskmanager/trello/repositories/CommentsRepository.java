package taskmanager.trello.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import taskmanager.trello.entities.Comments;

@Repository
@Transactional
public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
