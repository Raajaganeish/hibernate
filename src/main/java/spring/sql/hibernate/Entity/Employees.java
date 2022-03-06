package spring.sql.hibernate.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "employees",
        indexes = @Index(name = "employee_index_email", columnList = "email"))
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Employees implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    @NonNull
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NonNull
    private String lastName;

    @Column(name = "birthdate", nullable = false)
    @NonNull
    private Date birthdate;

    @Column(name = "email", nullable = true, unique = true)
    @NonNull
    private String email;

    @ManyToOne(targetEntity = Teams.class, cascade = { CascadeType.PERSIST})
    @JoinColumn(name = "team_id", referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "FK_EMP_TEAM_CS", foreignKeyDefinition = "foreign key (team_id) " +
                        "references teams(id) " +
                        "on update cascade " +
                        "on delete set null"),
                nullable = true)
    @JsonBackReference
    private Teams teams;

    @ManyToMany(targetEntity = Projects.class, cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinTable(name = "employee_project",
                joinColumns = {
                        @JoinColumn(name = "employee_id",
                                referencedColumnName = "id",
                                foreignKey = @ForeignKey(name = "FK_EMP_CS",
                                        foreignKeyDefinition = "foreign key (employee_id)\n" +
                                                "references employees (id)\n" +
                                                "on update cascade\n" +
                                                "on delete cascade")),
                },
                inverseJoinColumns = {
                        @JoinColumn(name = "project_id", referencedColumnName = "id",
                            foreignKey = @ForeignKey(name = "FK_PROJ_CS",
                                    foreignKeyDefinition = "foreign key (project_id)\n" +
                                    "references projects (id)\n" +
                                    "on update cascade\n" +
                                    "on delete cascade"))
                }
    )
    private Set<Projects> projects;

}
