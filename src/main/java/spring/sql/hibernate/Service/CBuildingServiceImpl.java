package spring.sql.hibernate.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.sql.hibernate.Entity.CompanyBuildings;
import spring.sql.hibernate.Repository.CBuildingJPA;

import java.util.Optional;

@Service
public class CBuildingServiceImpl implements ICBuildingService {

    @Autowired
    CBuildingJPA cBuildingJPA;
    @Override
    public CompanyBuildings findById(Integer id) {
        return cBuildingJPA.findById(id).orElseThrow();
    }
}
