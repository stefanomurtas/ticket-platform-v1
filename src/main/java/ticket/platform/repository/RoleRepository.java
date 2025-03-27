package ticket.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ticket.platform.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByName(String name);
}