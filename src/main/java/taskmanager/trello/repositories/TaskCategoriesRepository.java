package taskmanager.trello.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import taskmanager.trello.entities.TaskCategories;

@Repository
@Transactional
public interface TaskCategoriesRepository extends JpaRepository<TaskCategories, Long> {
}
