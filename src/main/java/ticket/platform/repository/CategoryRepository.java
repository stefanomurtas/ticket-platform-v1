package ticket.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ticket.platform.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}