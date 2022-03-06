package spring.sql.hibernate.Service;

import spring.sql.hibernate.Entity.CompanyBuildings;

import java.util.Optional;

public interface ICBuildingService {
    CompanyBuildings findById(Integer id);
}
