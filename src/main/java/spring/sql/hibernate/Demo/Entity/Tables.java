package spring.sql.hibernate.Demo.Entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tables")
@Getter
@Setter
public class Tables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "num_seats", columnDefinition = "int")
    @NonNull
    private int num_seats;

    @Column(name = "category", columnDefinition = "varchar(200)")
    @NonNull
    private String category;
}
