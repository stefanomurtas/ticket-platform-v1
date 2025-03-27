package ticket.platform.service;

import java.util.List;

import ticket.platform.entity.Category;
import ticket.platform.entity.Operator;
import ticket.platform.entity.Ticket;
import ticket.platform.enums.TicketStatus;

public interface TicketService {
    List<Ticket> getAllTickets();
    Ticket getById(Long ticketId);
    List<Ticket> getTicketsByCategoryId(Long categoryId);
    List<Ticket> getTicketsByStatus(TicketStatus ticketStatus);
    List<Ticket> getTicketsByOperatorId(Long operatorId);
    Ticket createTicket(String title, String details, TicketStatus status, Category category, Operator operator);
    Ticket updateTicket(Ticket updatedTicket);
    void deleteTicketById(Long ticketId);
    List<Ticket> search(String query);
    List<Ticket> findTicketsByOperatorId(Long operatorId);
    List<Ticket> getTicketsAssignedToOperator(String username);
}