package ticket.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ticket.platform.entity.Operator;

public interface OperatorRepository extends JpaRepository<Operator, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}