package spring.sql.hibernate.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.sql.hibernate.Entity.CompanyBuildings;
import spring.sql.hibernate.Entity.Teams;
import spring.sql.hibernate.Repository.CBuildingJPA;
import spring.sql.hibernate.Repository.TeamJPA;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TeamsServiceImpl implements ITeamsService{

    @Autowired
    TeamJPA teamJPA;

    @Autowired
    CBuildingJPA cBuildingJPA;

    @Override
    public Teams findByName(String name) {
        return teamJPA.findByName(name).orElseThrow();
    }

    @Override
    public List<Teams> findAll() {
        return teamJPA.findAll();
    }

    @Override
    @Transactional
    public Teams saveTeamWithBuildingInfo(String teamName, Integer buildingId) {
        Teams team = teamJPA.findByName(teamName).orElse(new Teams(teamName));
        Optional<CompanyBuildings> companyBuildings = cBuildingJPA.findById(buildingId);
        CompanyBuildings companyBuildings1 = null;
        if (companyBuildings.isPresent()) {
            companyBuildings1 = companyBuildings.get();
        } else {
            companyBuildings1 = new CompanyBuildings("Darkroom");
        }
        team.setCompanyBuildings(companyBuildings1);
        return teamJPA.save(team);
    }


}
