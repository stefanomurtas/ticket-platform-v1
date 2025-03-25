package ticket.platform.ticketplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ticket.platform.ticketplatform.entity.Operator;

public interface OperatorRepository extends JpaRepository<Operator,Long> {

    boolean existsByUsername(String username);
}

