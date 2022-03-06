package spring.sql.hibernate.Service;

import spring.sql.hibernate.Entity.Employees;
import spring.sql.hibernate.Entity.IntranetAccounts;

import java.util.List;

public interface IIntranetAccountService {
    public IntranetAccounts addIAToDB(String password);
    public IntranetAccounts addIAToDB(String password, Employees employees);
    public List<IntranetAccounts> findAll();
}
