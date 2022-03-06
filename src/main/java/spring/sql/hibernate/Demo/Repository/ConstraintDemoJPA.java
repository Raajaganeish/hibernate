package spring.sql.hibernate.Demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.sql.hibernate.Demo.Entity.ConstraintDemo;

@Repository
public interface ConstraintDemoJPA extends JpaRepository<ConstraintDemo, String> {
}
