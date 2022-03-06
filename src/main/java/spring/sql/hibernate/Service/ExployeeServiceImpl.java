package spring.sql.hibernate.Service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.sql.hibernate.Entity.Employees;
import spring.sql.hibernate.Entity.Projects;
import spring.sql.hibernate.Entity.Teams;
import spring.sql.hibernate.POJO.EmpIntranetAC;
import spring.sql.hibernate.POJO.EmpTeamBuildObj;
import spring.sql.hibernate.Repository.EmployeeJPA;
import spring.sql.hibernate.Repository.ProjectJPA;
import spring.sql.hibernate.Repository.TeamJPA;

import java.sql.Date;
import java.util.*;

@Service
public class ExployeeServiceImpl implements IEmployeesService {

    @Autowired
    EmployeeJPA employeeJPA;

    @Autowired
    TeamJPA teamJPA;

    @Autowired
    ProjectJPA projectJPA;

    @Override
    public Employees addEmployeeToDB(String f_name, String l_name, String date, String email) {
        return employeeJPA.save(new Employees(f_name, l_name, new Date(Calendar.getInstance().getTime().getTime()), email));
    }

    @Override
    public List<Employees> findAllEmployees() {
        return employeeJPA.findAll();
    }

    @Override
    public Employees findById(Integer id) {
        return employeeJPA.findById(id).orElseThrow(() -> new ObjectNotFoundException(Math.random(), "Object Not Found"));
    }

    @Override
    public Employees findByEmailId(String email) {
        return employeeJPA.findByEmailId(email).orElseThrow(() -> new ObjectNotFoundException(Math.random(), "Object Not Found"));
    }

    @Override
    public List<EmpIntranetAC> getEmpIntranetAC() {
        return employeeJPA.getEmpIntranetAC();
    }

    @Override
    public void deleteById(Integer id) {
        employeeJPA.deleteById(id);

    }

    @Override
    @Transactional
    public Employees addEmployeeToDB(String f_name, String l_name, String date, String email, String teamName) {
        Employees emp = new Employees(f_name, l_name, new Date(Calendar.getInstance().getTime().getTime()), email);
        Optional<Teams> optionalVal = teamJPA.findByName(teamName);
        Teams team = null;
        if (optionalVal.isPresent()) {
            team = optionalVal.get();
        } else {
            team = new Teams(teamName);
            team = teamJPA.save(team);
        }
        emp.setTeams(team);
        return employeeJPA.save(emp);
    }

    @Override
    public List<Employees> findAllEmployeesByTeam(Integer id) {
        return employeeJPA.findAllEmployeesByTeams(id);
    }

    @Override
    public List<EmpTeamBuildObj> findAllEmployeesWhoWorksInBuildingId(Integer id) {
        return employeeJPA.getEmpTeamBuildObj();
    }

    @Override
    @Transactional
    public Employees addEmployeesWithMultipleProjects(String f_name, String l_name, String date, String email, Set<Integer> projectIds) {
        Employees emp = new Employees(f_name, l_name, new Date(Calendar.getInstance().getTime().getTime()), email);
        Set<Projects> projects = new HashSet<>();
        projectIds.forEach((id) -> {
            Optional<Projects> optProject = projectJPA.findById(id);
            if (optProject.isPresent()) {
                projects.add(optProject.get());
            } else {
                Projects project = new Projects("New Hire Onboarding" + String.valueOf(Math.floor(Math.random())));
                project.setDeadline(new Date(Calendar.getInstance().getTime().getTime() + 10000));
                projects.add(project);
            }
        });
        emp.setProjects(projects);
        return employeeJPA.save(emp);
    }
}
