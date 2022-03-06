package spring.sql.hibernate.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.sql.hibernate.Entity.Employees;
import spring.sql.hibernate.Entity.IntranetAccounts;
import spring.sql.hibernate.Repository.IntranetAccountJPA;

import java.util.List;

@Service
public class IntranetAccountServiceImpl implements IIntranetAccountService {

    @Autowired
    public IntranetAccountJPA intranetAccountJPA;

    @Override
    public IntranetAccounts addIAToDB(String password) {
        return intranetAccountJPA.save(new IntranetAccounts(password));
    }

    @Override
    public IntranetAccounts addIAToDB(String password, Employees employees) {
        IntranetAccounts intranetAccounts = new IntranetAccounts(password);
        intranetAccounts.setEmployees(employees);
        return intranetAccountJPA.save(intranetAccounts);
    }

    @Override
    public List<IntranetAccounts> findAll() {
        return intranetAccountJPA.findAll();
    }
}
