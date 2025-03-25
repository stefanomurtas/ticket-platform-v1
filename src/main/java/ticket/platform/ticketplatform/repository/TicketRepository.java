package ticket.platform.ticketplatform.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ticket.platform.ticketplatform.entity.Ticket;
import ticket.platform.ticketplatform.enums.TicketStatus;


@Repository
public interface  TicketRepository extends JpaRepository<Ticket,Long>{
    
    public List<Ticket> findAllByCategoryId(Long categoryId);
    public List<Ticket> findAllByOperatorId(Long operatorId);
    public List<Ticket> findAllByStatus(TicketStatus ticketStatus);
    public List<Ticket> findByTitleContaining(String title);
    public List<Ticket> findByDetailsContaining(String details); 
    public List<Ticket> findByTitleContainingAndDetailsContaining(String title, String details);
    Ticket getById(Integer id);
}
   