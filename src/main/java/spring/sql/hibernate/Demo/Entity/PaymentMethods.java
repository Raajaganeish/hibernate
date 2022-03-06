package spring.sql.hibernate.Demo.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "payment_methods")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class PaymentMethods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", columnDefinition = "VARCHAR(200)")
    @NonNull
    private String name;
}
