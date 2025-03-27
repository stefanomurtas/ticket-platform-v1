package ticket.platform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ticket.platform.entity.Operator;


public interface OperatorRepository extends JpaRepository<Operator, Long> {
    boolean existsByUsername(String operatorUsername);
   
    Optional<Operator> findByUsername(String operatorUsername);  
    List<Operator> findAllByUsername(String operatorUsername);


    boolean existsByEmail(String email);

}