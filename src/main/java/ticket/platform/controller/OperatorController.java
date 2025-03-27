package ticket.platform.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class OperatorController {
    

       @GetMapping("/login")
    public String login() {
        return "auth/login"; // la tua view deve stare in templates/auth/login.html
    }
}

