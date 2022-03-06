package spring.sql.hibernate.Demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.sql.hibernate.Demo.Entity.ConstraintDemo;
import spring.sql.hibernate.Demo.Repository.ConstraintDemoJPA;

@Service
public class ConstraintDemoServiceImpl implements IConstraintDemoService{

    @Autowired
    ConstraintDemoJPA constraintDemoJPA;

    @Override
    public ConstraintDemo save(ConstraintDemo constraintDemo) {
        return constraintDemoJPA.save(constraintDemo);
    }
}
