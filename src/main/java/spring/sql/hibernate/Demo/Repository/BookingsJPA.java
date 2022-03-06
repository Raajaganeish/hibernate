package spring.sql.hibernate.Demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.sql.hibernate.Demo.Entity.Bookings;
import spring.sql.hibernate.Demo.Utility.IDateAndNumOfGuests;
import spring.sql.hibernate.Demo.Utility.IPaymentTypeDateAndAmount;

import java.sql.Date;
import java.util.List;

@Repository
public interface BookingsJPA extends JpaRepository<Bookings, Integer> {
    @Query(value = "select bks.booking_date as bookingDate, sum(bks.num_guests) as numGuests " +
            "from bookings as bks " +
            "inner join tables as tb on bks.table_id = tb.id\n" +
            "group by bks.booking_date",
            nativeQuery = true)
    List<IDateAndNumOfGuests> findNumberOfGuestsOnEachDate();

    @Query(value = "select sum(bks.num_guests) " +
            "from bookings as bks " +
            "inner join payment_methods as pm on bks.payment_id = pm.id " +
            "where pm.name = :paymentType",
            nativeQuery = true)
    Double findNumberOfPaymentsByType(@Param(value = "paymentType") String paymentType);

    @Query(value = "select bks.booking_date as bookingDate, pm.name as paymentType, sum(bks.amount_billed) as totalAmount " +
            "from bookings as bks " +
            "inner join payment_methods as pm on bks.payment_id = pm.id " +
            "group by bks.booking_date, pm.name", nativeQuery = true)
    List<IPaymentTypeDateAndAmount> findTotalAmountGroupedByDateAndPaymentType();

    @Query(value = "select bks.booking_date from bookings as bks group by bks.booking_date having sum(bks.amount_billed) = (" +
            "select min(totalAmt) " +
            "from (" +
            "select bks.booking_date as bkDate, sum(bks.amount_billed) as totalAmt " +
            "from bookings as bks " +
            "inner join payment_methods as pm on bks.payment_id = pm.id " +
            "group by bks.booking_date) " +
            "as groupedTabled" +
            ")",
        nativeQuery = true)
    Date findBookingDateWhichHasMinAmountBilled();
}
