package ticket.platform.security;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ticket.platform.entity.Operator;
import ticket.platform.repository.OperatorRepository;

public class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired
     private OperatorRepository operatorRepository;
 

     @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         
        Optional<Operator> operatorAttempt = operatorRepository.findByUsername(username);
        if(operatorAttempt.isPresent()){
           return new DatabaseUserDetails(operatorAttempt.get());
        }
        throw new UsernameNotFoundException("Operatore non trovato");
    
     }}
//     @Bean
// public DatabaseUserDetailsService userDetailsService(OperatorRepository operatorRepository) {
//      return new DatabaseUserDetailsService(operatorRepository);
//     }}
   

  
