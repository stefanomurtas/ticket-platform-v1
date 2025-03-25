package ticket.platform.ticketplatform.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import ticket.platform.ticketplatform.entity.Category;
import ticket.platform.ticketplatform.entity.Operator;
import ticket.platform.ticketplatform.entity.Ticket;
import ticket.platform.ticketplatform.enums.TicketStatus;
import ticket.platform.ticketplatform.repository.CategoryRepository;
import ticket.platform.ticketplatform.repository.OperatorRepository;
import ticket.platform.ticketplatform.repository.TicketRepository;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final CategoryRepository categoryRepository;
    private final OperatorRepository operatorRepository;

    public TicketService(TicketRepository ticketRepository, CategoryRepository categoryRepository,
            OperatorRepository operatorRepository) {
        this.ticketRepository = ticketRepository;
        this.categoryRepository = categoryRepository;
        this.operatorRepository = operatorRepository;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Operatore non trovato"));
    }

    public List<Ticket> getTicketsByCategoryId(Long categoryId) {
        return ticketRepository.findAllByCategoryId(categoryId);
    }

    public List<Ticket> getTicketsByStatus(TicketStatus ticketStatus) {
        return ticketRepository.findAllByStatus(ticketStatus);
    }

    public List<Ticket> getTicketsByOperatorId(Long operatorId) {
        return ticketRepository.findAllByOperatorId(operatorId);
    }

    public Ticket createTicket(String title, String details, TicketStatus status, Long categoryId, Long operatorId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Categoria non trovata"));
        Operator operator = operatorRepository.findById(operatorId)
                .orElseThrow(() -> new IllegalArgumentException("Operatore non trovato"));

        Ticket ticket = new Ticket(status, title, details, category, operator, LocalDateTime.now(),
                LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Ticket updatedTicket) {
        Ticket existingTicket = ticketRepository.findById(updatedTicket.getId())
                .orElseThrow(() -> new IllegalArgumentException("Ticket non trovato"));
    
        existingTicket.setTitle(updatedTicket.getTitle());
        existingTicket.setDetails(updatedTicket.getDetails());
        existingTicket.setStatus(updatedTicket.getStatus());
        existingTicket.setCategory(updatedTicket.getCategory());
        existingTicket.setOperator(updatedTicket.getOperator());
        existingTicket.setUpdatedAt(LocalDateTime.now());
    
        return ticketRepository.save(existingTicket);
    }
    

    public void deleteTicketById(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }
    public List<Ticket> findByTitle(String title){
        return ticketRepository.findByTitleContaining(title);
    }
    public List<Ticket> findByDetails(String details){
        return ticketRepository.findByDetailsContaining(details);

}

    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }}
