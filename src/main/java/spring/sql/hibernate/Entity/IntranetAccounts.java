package spring.sql.hibernate.Entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "intranet_accounts")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class IntranetAccounts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "password", nullable = false)
    @NonNull
    private String password;


    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, targetEntity = Employees.class)
    @JoinColumn(name = "email", unique = true,
                referencedColumnName = "email",
                foreignKey = @ForeignKey(name = "FK_INTRA_AC_CS", foreignKeyDefinition = "foreign key (email)\n" +
                        "references employees (email)\n" +
                        "on update cascade\n" +
                        "on delete set null"))
    private Employees employees;
}
