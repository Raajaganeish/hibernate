package spring.sql.hibernate.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.sql.hibernate.Entity.CompanyBuildings;

import java.util.Optional;

public interface CBuildingJPA extends JpaRepository<CompanyBuildings, Integer> {

}
