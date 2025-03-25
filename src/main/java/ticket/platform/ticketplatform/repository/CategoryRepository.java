package ticket.platform.ticketplatform.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import ticket.platform.ticketplatform.entity.Category;


public interface CategoryRepository  extends JpaRepository<Category,Long> {

}