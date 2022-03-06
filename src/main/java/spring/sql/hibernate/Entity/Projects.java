package spring.sql.hibernate.Entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", columnDefinition = "varchar(200) not null")
    @NonNull
    private String title;

    @Column(name = "deadline", columnDefinition = "date")
    private Date deadline;
}
