package ticket.platform.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import ticket.platform.entity.Category;
import ticket.platform.entity.Operator;
import ticket.platform.entity.Ticket;
import ticket.platform.enums.TicketStatus;
import ticket.platform.service.CategoryService;
import ticket.platform.service.OperatorService;
import ticket.platform.service.TicketService;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;
    private final CategoryService categoryService;
    private final OperatorService operatorService;

    public TicketController(TicketService ticketService, CategoryService categoryService, OperatorService operatorService) {
        this.ticketService = ticketService;
        this.categoryService = categoryService;
        this.operatorService = operatorService;
    }

    @GetMapping
    public String index(Model model) {
        List<Ticket> tickets = ticketService.getAllTickets();
        model.addAttribute("tickets", tickets);
        return "ticket/index";
    }

    @GetMapping("/home")
    public String home() {
        return "ticket/home";
    }

    @GetMapping("/{id}")
    public String showTicket(@PathVariable Long id, Model model) {
        Ticket ticket = ticketService.getById(id);
        model.addAttribute("ticket", ticket);
        return "ticket/show";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "ticket/search";
    }

    @GetMapping("/search/by-title-or-details")
    public String findByDet(@RequestParam(name = "query") String query, Model model) {
        List<Ticket> tickets = ticketService.search(query);
        model.addAttribute("tickets", tickets);
        model.addAttribute("query", query);
        return "ticket/search";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Ticket ticket = new Ticket();
        ticket.setStatus(TicketStatus.TO_DO);

        model.addAttribute("ticket", ticket);
        model.addAttribute("ticketStatuses", TicketStatus.values());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("operators", operatorService.getAllOperators());

        return "ticket/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        Category category = categoryService.getById(ticket.getCategory().getId());
        Operator operator = operatorService.getById(ticket.getOperator().getId());

        if (category == null) {
            bindingResult.addError(new ObjectError("Category", String.format("Categoria con id %d non trovata.", ticket.getCategory().getId())));
        }
        if (operator == null) {
            bindingResult.addError(new ObjectError("Operator", String.format("Operatore con id %d non trovato.", ticket.getOperator().getId())));
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("create", true);
            model.addAttribute("ticketStatuses", TicketStatus.values());
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("operators", operatorService.getAllOperators());
            return "ticket/create";
        }

        try {
            ticketService.createTicket(
                    ticket.getTitle(),
                    ticket.getDetails(),
                    ticket.getStatus(),
                    category,
                    operator
            );
            redirectAttributes.addFlashAttribute("message", String.format("\"%s\" è stato salvato correttamente.", ticket.getTitle()));
            redirectAttributes.addFlashAttribute("messageClass", "alert-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Errore durante la creazione del ticket.");
            redirectAttributes.addFlashAttribute("messageClass", "alert-danger");
        }

        return "redirect:/ticket";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Ticket ticket = ticketService.getById(id);

        if (ticket == null) {
            return "redirect:/ticket";
        }

        model.addAttribute("ticket", ticket);
        model.addAttribute("ticketStatuses", TicketStatus.values());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("operators", operatorService.getAllOperators());

        return "ticket/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, @Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        Ticket existingTicket = ticketService.getById(id);
        if (existingTicket == null) {
            bindingResult.addError(new ObjectError("Ticket", "Ticket con id " + id + " non trovato."));
        }

        Category category = categoryService.getById(ticket.getCategory().getId());
        if (category == null) {
            bindingResult.addError(new ObjectError("Category", "Categoria con id " + ticket.getCategory().getId() + " non trovata."));
        }

        Operator operator = operatorService.getById(ticket.getOperator().getId());
        if (operator == null) {
            bindingResult.addError(new ObjectError("Operator", "Operatore con id " + ticket.getOperator().getId() + " non trovato."));
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("ticketStatuses", TicketStatus.values());
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("operators", operatorService.getAllOperators());
            return "ticket/edit";
        }

        ticket.setCategory(category);
        ticket.setOperator(operator);
        ticketService.updateTicket(ticket);
        redirectAttributes.addFlashAttribute("message", String.format("\"%s\" è stato aggiornato correttamente.", ticket.getTitle()));
        redirectAttributes.addFlashAttribute("messageClass", "alert-success");

        return "redirect:/ticket";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        ticketService.deleteTicketById(id);
        redirectAttributes.addFlashAttribute("message", String.format("Ticket con id %d è stato cancellato correttamente", id));
        redirectAttributes.addFlashAttribute("messageClass", "alert-danger");

        return "redirect:/ticket";
    }
//     @GetMapping("/operator/tickets")
// public String getTicketsForOperator(Model model, Principal principal) {
//     String username = principal.getName();
//     Operator operator = operatorRepository.findByUsername(username)
//             .orElseThrow(() -> new UsernameNotFoundException("Operatore non trovato"));
    
//     List<Ticket> tickets = ticketService.findTicketsByOperatorId(operator.getId());
//     model.addAttribute("tickets", tickets);
//     return "operator/tickets"; 

}