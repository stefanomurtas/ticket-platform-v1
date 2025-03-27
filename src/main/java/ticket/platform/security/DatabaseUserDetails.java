package ticket.platform.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
 
import ticket.platform.entity.Operator;
import ticket.platform.entity.Role;

public class DatabaseUserDetails implements UserDetails {
    private final Long id;
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> authorities;

    public DatabaseUserDetails(Operator operator) {
        this.id = operator.getId();
        this.username = operator.getUsername();
        this.password = operator.getPassword();
        this.authorities = new HashSet<GrantedAuthority>();
        for (Role role : operator.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Puoi restituire le autorità/ruoli dell'operatore se hai una gestione dei
        // ruoli
        return this.authorities; // Da implementare se hai i ruoli
    }

    @Override
    public String getPassword() {
        return this.password; }

    @Override
    public String getUsername() {
        return this.username;
    }

    public Long getId() {
        return this.id;
    }
    // @Override
    // public boolean isAccountNonExpired() {
    // return true; // Imposta a true se non vuoi che l'account scada
    // }

    @Override
    public boolean isAccountNonLocked() {
    return true;  
     
    }
    @Override
 public boolean isEnabled() {
    return true; 
    
     }
    // @Override
    // public boolean isCredentialsNonExpired() {
    // return true; // Imposta a true se non vuoi che le credenziali scadano
    // }

 

    // // Metodo aggiuntivo per ottenere l'operatore
    // public Operator getOperator() {
    // return operator;
    // }
}
