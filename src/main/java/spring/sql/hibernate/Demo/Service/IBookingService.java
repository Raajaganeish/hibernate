package spring.sql.hibernate.Demo.Service;

import spring.sql.hibernate.Demo.Entity.Bookings;
import spring.sql.hibernate.Demo.Utility.IDateAndNumOfGuests;
import spring.sql.hibernate.Demo.Utility.IPaymentTypeDateAndAmount;

import java.sql.Date;
import java.util.List;

public interface IBookingService {
    List<IDateAndNumOfGuests> getNumberOfGuestOnEachDay();
    Double getNumberOfPaymentsBy(String paymentType);
    List<IPaymentTypeDateAndAmount> getAllPaymentData();
    Date getBookingDateWhichHasMAXMINAmt();
}
