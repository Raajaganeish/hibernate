package spring.sql.hibernate.Demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
import spring.sql.hibernate.POJO.EmpStatus;

import javax.persistence.*;

@Entity
@Table(name = "constraint_demo")
@Check(constraints = "range_id > 20")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConstraintDemo {
    @Id
    @Column(name = "email", columnDefinition = "varchar(250) not null check (email like '%@%.com')")
    private String email;

    @Column(name = "range_id")
    private Integer rangeId;

    @Enumerated(EnumType.STRING)
    private EmpStatus empStatus;

    @Column(name = "product_id", columnDefinition = "varchar(250) not null check (product_id like '%_PID_%')")
    private String productId;
}
