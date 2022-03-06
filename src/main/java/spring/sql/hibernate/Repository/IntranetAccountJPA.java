package spring.sql.hibernate.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.sql.hibernate.Entity.IntranetAccounts;

public interface IntranetAccountJPA extends JpaRepository<IntranetAccounts, Integer> {
}
