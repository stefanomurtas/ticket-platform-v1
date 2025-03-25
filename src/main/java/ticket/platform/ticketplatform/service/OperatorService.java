package ticket.platform.ticketplatform.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ticket.platform.ticketplatform.entity.Operator;
import ticket.platform.ticketplatform.repository.OperatorRepository;

@Service
public class OperatorService {
    @Autowired

    private final OperatorRepository operatorRepository;
    private Long Id;

    public OperatorService(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    // Ottieni tutti gli operatori
    public List<Operator> getAllOperators() {
        return operatorRepository.findAll();
    }

    // Ottieni un operatore per ID
    public Operator getById(Long operatorId) {
        return operatorRepository.findById(operatorId).orElseThrow(() -> new IllegalArgumentException("Operatore non trovato"));
    }

    public Operator createOperator(String email, String username, String password, Boolean active) {
        if (operatorRepository.existsById(Id)) {
            throw new RuntimeException("Email già esistente");
        }
        if (operatorRepository.existsByUsername(username)) {
            throw new RuntimeException("Username già esistente");
        }
    
        Operator operator = new Operator();
        return operatorRepository.save(operator);
    }

   

    
}
