
package ticket.platform.ticketplatform.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ticket.platform.ticketplatform.entity.Ticket;
import ticket.platform.ticketplatform.enums.TicketStatus;
import ticket.platform.ticketplatform.service.TicketService;

@RestController
@CrossOrigin
@RequestMapping("api/tickets")
public class TicketRestController {
    private final TicketService ticketService;

    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;

    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getById(@PathVariable Long id) {
        Ticket ticket = ticketService.getById(id);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ticket);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllticket() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping(value = "/all-by-category")
    public ResponseEntity<List<Ticket>> getAllByCategory(@RequestParam Long categoryId) {
        return ResponseEntity.ok(ticketService.getTicketsByCategoryId(categoryId));
    }

    @GetMapping(value = "/all-by-status")
    public ResponseEntity<List<Ticket>> getAllByStatus(@RequestParam TicketStatus status) {
        return ResponseEntity.ok(ticketService.getTicketsByStatus(status));
    }
}