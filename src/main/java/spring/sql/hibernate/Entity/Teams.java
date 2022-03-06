package spring.sql.hibernate.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", columnDefinition = "varchar(200) not null unique")
    @NonNull
    private String name;

    /* Below is optional - for bidirectional Relation*/
    @OneToMany(mappedBy = "teams", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Employees> employees;


    @ManyToOne(targetEntity = CompanyBuildings.class, cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "building_id", referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "FK_TEAM_BUILDING_CS", foreignKeyDefinition = "foreign key (building_id)\n" +
                        "references company_buildings(id)\n" +
                        "on update cascade\n" +
                        "on delete set null"))
    private CompanyBuildings companyBuildings;

}
