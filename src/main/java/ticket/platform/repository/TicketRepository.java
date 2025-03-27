package ticket.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ticket.platform.entity.Ticket;
import ticket.platform.enums.TicketStatus;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findAllByCategoryId(Long categoryId);

    List<Ticket> findAllByOperatorId(Long operatorId);
    List<Ticket> findAllByOperatorUsername(String username);

    List<Ticket> findAllByStatus(TicketStatus ticketStatus);

    List<Ticket> findByTitleLikeIgnoreCaseOrDetailsLikeIgnoreCase(String title, String details);
}