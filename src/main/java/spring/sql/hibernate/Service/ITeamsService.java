package spring.sql.hibernate.Service;

import spring.sql.hibernate.Entity.Employees;
import spring.sql.hibernate.Entity.Teams;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ITeamsService {
    Teams findByName(String name);
    List<Teams> findAll();
    Teams saveTeamWithBuildingInfo(String teamName, Integer buildingId);
}
