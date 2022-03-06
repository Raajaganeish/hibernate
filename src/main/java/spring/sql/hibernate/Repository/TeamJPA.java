package spring.sql.hibernate.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.sql.hibernate.Entity.Employees;
import spring.sql.hibernate.Entity.Teams;

import java.util.Optional;
import java.util.Set;

public interface TeamJPA extends JpaRepository<Teams, Integer> {
    Optional<Teams> findByName(String name);
}
