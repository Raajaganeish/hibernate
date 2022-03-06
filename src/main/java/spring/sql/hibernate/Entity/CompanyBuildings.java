package spring.sql.hibernate.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "company_buildings")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class CompanyBuildings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", columnDefinition = "varchar(200) not null")
    @NonNull
    private String name;
}
