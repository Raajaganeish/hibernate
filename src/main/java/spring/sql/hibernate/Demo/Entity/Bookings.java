package spring.sql.hibernate.Demo.Entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "bookings")
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "booking_date", columnDefinition = "DATE NOT NULL")
    @NonNull
    private Date booking_date;

    @Column(name = "num_guests", columnDefinition = "INT NOT NULL")
    @NonNull
    private Integer num_guests;

    @Column(name = "amount_billed", columnDefinition = "NUMERIC(6, 2) NOT NULL")
    @NonNull
    private Double amount_billed;

    @Column(name = "amount_tipped", columnDefinition = "NUMERIC(6, 2)")
    @NonNull
    private Double amount_tipped;

    @ManyToOne(targetEntity = PaymentMethods.class)
    @JoinColumn(name = "payment_id", referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "FK_BK_PY_CS", foreignKeyDefinition = "foreign key (payment_id)\n" +
                        "references payment_methods(id)\n" +
                        "on update cascade\n" +
                        "on delete set null"))
    @NonNull
    private PaymentMethods payment;


    @ManyToOne(targetEntity = Tables.class)
    @JoinColumn(name = "table_id", referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "FK_BK_TB_CS", foreignKeyDefinition = "foreign key (table_id)\n" +
                        "references tables(id)\n" +
                        "on update cascade\n" +
                        "on delete cascade"))
    @NonNull
    private Tables table;
}
