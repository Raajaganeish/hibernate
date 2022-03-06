package spring.sql.hibernate.Service;

import spring.sql.hibernate.Entity.Projects;

import java.util.List;
import java.util.Set;

public interface IProjectsService {
    Projects save(Projects project);
    Projects findById(Integer id);
    List<Projects> saveAll(Set<Projects> projects);
    void deleteById(Integer id);
    List<Projects> findAll();
}
