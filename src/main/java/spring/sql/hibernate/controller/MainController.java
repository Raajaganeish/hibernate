package spring.sql.hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.sql.hibernate.Entity.Employees;
import spring.sql.hibernate.Entity.IntranetAccounts;
import spring.sql.hibernate.Entity.Projects;
import spring.sql.hibernate.Entity.Teams;
import spring.sql.hibernate.POJO.EmpIntranetAC;
import spring.sql.hibernate.POJO.EmpTeamBuildObj;
import spring.sql.hibernate.Service.IEmployeesService;
import spring.sql.hibernate.Service.IIntranetAccountService;
import spring.sql.hibernate.Service.IProjectsService;
import spring.sql.hibernate.Service.ITeamsService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = {"/employees"}, method = RequestMethod.POST)
public class MainController {

    @Autowired
    public IEmployeesService iEmployeesService;

    @Autowired
    public IIntranetAccountService iIntranetAccountService;

    @Autowired
    public ITeamsService iTeamsService;

    @Autowired
    public IProjectsService iProjectsService;

    @PostMapping(value = {"/add"}, consumes = { "application/json"})
    public Employees addEmployeeToDB(@RequestBody Map<String, Object> payload) {
        return iEmployeesService.addEmployeeToDB(
                String.valueOf(payload.getOrDefault("f_name", "firstName")),
                String.valueOf(payload.getOrDefault("l_name", "lastName")),
                "",
                String.valueOf(payload.getOrDefault("email", "email")));

    }


    @GetMapping(value = {"/getAll"})
    public List<Employees> getAllEmployees() {
        return iEmployeesService.findAllEmployees();
    }

    @PostMapping( value = {"/addIA"}, consumes = { "application/json" })
    public IntranetAccounts addIntranetAccount(@RequestBody Map<String, Object> payload) {
        if (payload.get("empEmail") != null) {
            return iIntranetAccountService.addIAToDB(
                    String.valueOf(payload.getOrDefault("password", "random@123")),
                    iEmployeesService.findByEmailId(String.valueOf(payload.get("empEmail")))
            );
        }
        return iIntranetAccountService.addIAToDB(
                String.valueOf(payload.getOrDefault("password", "random@123")));
    }

    @GetMapping( value = { "/getAllIntranetaccounts"})
    public List<IntranetAccounts> getAllIntranetaccounts() {
        return iIntranetAccountService.findAll();
    }

    @GetMapping(value = {"/getAllEmployeesWithIA"})
    public List<EmpIntranetAC> getAllEmployeesWithIA() {
        return iEmployeesService.getEmpIntranetAC();
    }


    @GetMapping(value = {"/deleteById/{id}"})
    public List<Employees> deleteById(@PathVariable(name = "id") Integer id) {
        iEmployeesService.deleteById(id);
        return iEmployeesService.findAllEmployees();
    }

    @PostMapping( value = {"/createEmployeeAndAssignATeam"}, consumes = { "application/json" })
    public Employees createEmployeeAndAssignATeam(@RequestBody Map<String, Object> payload) {
        if (payload.get("teamName") == null) {
            throw new RuntimeException("teamName is missing in payload");
        }

        return iEmployeesService.addEmployeeToDB(
                String.valueOf(payload.getOrDefault("f_name", "firstName")),
                String.valueOf(payload.getOrDefault("l_name", "lastName")),
                "",
                String.valueOf(payload.getOrDefault("email", "email")),
                String.valueOf(payload.get("teamName")));
    }

    @GetMapping(value = {"/getAllEmployeesFromATeam/{teamName}"})
    public List<Employees> getAllEmployeesFromATeam(@PathVariable(name = "teamName") String teamName) {
        Teams teams = iTeamsService.findByName(teamName);
        return iEmployeesService.findAllEmployeesByTeam(teams.getId());
    }

    @GetMapping(value = {"/getAllEmpForATeam/{teamName}"})
    public Set<Employees> getAllEmpForATeam(@PathVariable(name = "teamName") String teamName) {
        return iTeamsService.findByName(teamName).getEmployees();
    }


    @GetMapping(value = {"/getAllTeams"})
    public List<Teams> getAllTeams() {
        return iTeamsService.findAll();
    }

    @PostMapping(value = {"/addTeamWithBuildingInfo" }, consumes = { "application/json" })
    public Teams addTeamWithBuildingInfo(@RequestBody Map<String, String> payload) {
        String teamName = payload.getOrDefault("teamName", "R&D");
        Integer buildingId = Integer.valueOf(payload.get("buildingId"));
        return iTeamsService.saveTeamWithBuildingInfo(teamName, buildingId);
    }


    @GetMapping(value = { "/getAllEmpDetails" })
    public List<EmpTeamBuildObj> getAllEmpDetails(){
        return iEmployeesService.findAllEmployeesWhoWorksInBuildingId(1);
    }


    @PostMapping(value = {"/addEmployeeWithMultipleProject"}, consumes = { "application/json"})
    public Employees addEmployeeWithMultipleProjectToDB(@RequestBody Map<String, Object> payload) {
        Set<Integer> res = Arrays.asList(String.valueOf(payload.get("projectId")).split(",")).stream().map(Integer::valueOf).collect(Collectors.toSet());
        return iEmployeesService.addEmployeesWithMultipleProjects(
                String.valueOf(payload.getOrDefault("f_name", "firstName")),
                String.valueOf(payload.getOrDefault("l_name", "lastName")),
                "",
                String.valueOf(payload.getOrDefault("email", "email")),
                new HashSet<Integer>(res)
        );

    }

    @GetMapping(value = {"/deleteProjectById/{id}"})
    public List<Projects> deleteProjectById(@PathVariable(name = "id") Integer id) {
        iProjectsService.deleteById(id);
        return iProjectsService.findAll();
    }
}
