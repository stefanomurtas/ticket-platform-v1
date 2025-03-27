
package ticket.platform.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import ticket.platform.entity.Category;
import ticket.platform.entity.Operator;
import ticket.platform.entity.Ticket;
import ticket.platform.enums.TicketStatus;
import ticket.platform.repository.CategoryRepository;
import ticket.platform.repository.OperatorRepository;
import ticket.platform.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final CategoryRepository categoryRepository;
    private final OperatorRepository operatorRepository;

    public TicketServiceImpl(TicketRepository ticketRepository, CategoryRepository categoryRepository, OperatorRepository operatorRepository) {
        this.ticketRepository = ticketRepository;
        this.categoryRepository = categoryRepository;
        this.operatorRepository = operatorRepository;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Operatore non trovato"));
    }

    @Override
    public List<Ticket> getTicketsByCategoryId(Long categoryId) {
        return ticketRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public List<Ticket> getTicketsByStatus(TicketStatus ticketStatus) {
        return ticketRepository.findAllByStatus(ticketStatus);
    }

    @Override
    public List<Ticket> getTicketsByOperatorId(Long operatorId) {
        return ticketRepository.findAllByOperatorId(operatorId);
    }

    @Override
    public Ticket createTicket(String title, String details, TicketStatus status, Category category, Operator operator) {
        return ticketRepository.save(new Ticket(status, title, details, category, operator, LocalDateTime.now(), LocalDateTime.now()));
    }

    @Override
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

    @Override
    public void deleteTicketById(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }

    @Override
    public List<Ticket> search(String query) {
        return ticketRepository.findByTitleLikeIgnoreCaseOrDetailsLikeIgnoreCase("%" + query + "%", "%" + query + "%");
    }

    @Override
    public List<Ticket> findTicketsByOperatorId(Long operatorId) {
        return ticketRepository.findAllByOperatorId(operatorId);
    }

    @Override
    public List<Ticket> getTicketsAssignedToOperator(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  
}
