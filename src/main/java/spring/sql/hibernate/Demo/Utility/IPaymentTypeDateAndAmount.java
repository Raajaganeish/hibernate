package spring.sql.hibernate.Demo.Utility;

import java.sql.Date;

public interface IPaymentTypeDateAndAmount {
    String getPaymentType();
    Date getBookingDate();
    Double getTotalAmount();
}
