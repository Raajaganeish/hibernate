package spring.sql.hibernate.Service;

import spring.sql.hibernate.Entity.Employees;
import spring.sql.hibernate.POJO.EmpIntranetAC;
import spring.sql.hibernate.POJO.EmpTeamBuildObj;

import java.util.List;
import java.util.Set;

public interface IEmployeesService {
    Employees addEmployeeToDB(String f_name, String l_name, String date, String email);
    List<Employees> findAllEmployees();
    Employees findById(Integer id);
    Employees findByEmailId(String email);
    List<EmpIntranetAC> getEmpIntranetAC();
    void deleteById(Integer id);
    Employees addEmployeeToDB(String f_name, String l_name, String date, String email, String teamName);
    List<Employees> findAllEmployeesByTeam(Integer id);
    List<EmpTeamBuildObj> findAllEmployeesWhoWorksInBuildingId(Integer id);
    Employees addEmployeesWithMultipleProjects(String f_name, String l_name, String date, String email, Set<Integer> projectIds);
}
