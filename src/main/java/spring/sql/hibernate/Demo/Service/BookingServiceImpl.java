package spring.sql.hibernate.Demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.sql.hibernate.Demo.Entity.Bookings;
import spring.sql.hibernate.Demo.Repository.BookingsJPA;
import spring.sql.hibernate.Demo.Utility.IDateAndNumOfGuests;
import spring.sql.hibernate.Demo.Utility.IPaymentTypeDateAndAmount;

import java.sql.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements IBookingService{

    @Autowired
    BookingsJPA bookingsJPA;

    @Override
    public List<IDateAndNumOfGuests> getNumberOfGuestOnEachDay() {
        return bookingsJPA.findNumberOfGuestsOnEachDate();
    }

    @Override
    public Double getNumberOfPaymentsBy(String paymentType) {
        return bookingsJPA.findNumberOfPaymentsByType(paymentType);
    }

    @Override
    public List<IPaymentTypeDateAndAmount> getAllPaymentData() {
        return bookingsJPA.findTotalAmountGroupedByDateAndPaymentType();
    }

    @Override
    public Date getBookingDateWhichHasMAXMINAmt() {
        return bookingsJPA.findBookingDateWhichHasMinAmountBilled();
    }
}
