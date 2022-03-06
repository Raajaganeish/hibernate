package spring.sql.hibernate.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.sql.hibernate.Entity.Employees;
import spring.sql.hibernate.POJO.EmpIntranetAC;
import spring.sql.hibernate.POJO.EmpTeamBuildObj;

import java.util.List;
import java.util.Optional;

public interface EmployeeJPA extends JpaRepository<Employees, Integer> {

    @Query(value = "select * from employees as e where e.email = :qEmail", nativeQuery = true)
    Optional<Employees> findByEmailId(@Param(value = "qEmail") String email);

    @Query(value = "select new spring.sql.hibernate.POJO.EmpIntranetAC(e.id, e.firstName, e.lastName, e.birthdate, e.email, intraAcc.password) from Employees as e inner join IntranetAccounts as intraAcc on e.email = intraAcc.employees")
    List<EmpIntranetAC> getEmpIntranetAC();

    @Query(value = "select * from employees as e where e.team_id = :teamId", nativeQuery = true)
    List<Employees> findAllEmployeesByTeams(@Param(value = "teamId") Integer teamId);

    @Query(value = "select new spring.sql.hibernate.POJO.EmpTeamBuildObj(e.id, e.firstName, e.lastName, e.email, t.id, t.name, cb.id, cb.name) from Employees as e " +
            "inner join Teams as t on e.teams = t.id " +
            "inner join CompanyBuildings as cb on t.companyBuildings = cb.id")
    List<EmpTeamBuildObj> getEmpTeamBuildObj();
}
