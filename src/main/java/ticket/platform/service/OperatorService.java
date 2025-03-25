package ticket.platform.service;


import org.springframework.stereotype.Service;
import ticket.platform.entity.Operator;
import ticket.platform.repository.OperatorRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OperatorService {

    private final OperatorRepository operatorRepository;

    public OperatorService(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    public List<Operator> getAllOperators() {
        return operatorRepository.findAll();
    }

    public Operator getById(Long operatorId) {
        return operatorRepository.findById(operatorId).orElseThrow(() -> new IllegalArgumentException("Operatore non trovato"));
    }

    public Operator createOperator(String email, String username, String password, Boolean active) {
        if (operatorRepository.existsByEmail(email)) {
            throw new RuntimeException("Email già esistente");
        }
        if (operatorRepository.existsByUsername(username)) {
            throw new RuntimeException("Username già esistente");
        }

        Operator operator = new Operator(LocalDateTime.now(), LocalDateTime.now(), email, username, password, active);
        return operatorRepository.save(operator);
    }
}