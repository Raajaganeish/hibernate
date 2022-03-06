package spring.sql.hibernate.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.sql.hibernate.Entity.Projects;

public interface ProjectJPA extends JpaRepository<Projects, Integer> {
}
