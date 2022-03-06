package spring.sql.hibernate.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.sql.hibernate.Entity.Projects;
import spring.sql.hibernate.Repository.ProjectJPA;

import java.util.List;
import java.util.Set;

@Service
public class ProjectServiceImpl implements IProjectsService{
    @Autowired
    ProjectJPA projectJPA;

    @Override
    public Projects save(Projects project) {
        return projectJPA.save(project);
    }

    @Override
    public Projects findById(Integer id) {
        return projectJPA.findById(id).orElseThrow();
    }

    @Override
    public List<Projects> saveAll(Set<Projects> projects) {
        return projectJPA.saveAll(projects);
    }

    @Override
    public void deleteById(Integer id) {
        projectJPA.deleteById(id);
    }

    @Override
    public List<Projects> findAll() {
        return projectJPA.findAll();
    }
}
