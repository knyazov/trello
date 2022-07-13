package taskmanager.trello.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import taskmanager.trello.entities.Folders;

@Repository
@Transactional
public interface FoldersRepository extends JpaRepository<Folders, Long> {
}
