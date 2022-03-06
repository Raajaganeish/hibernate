package spring.sql.hibernate.Demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.sql.hibernate.Demo.Entity.ConstraintDemo;
import spring.sql.hibernate.Demo.Service.IBookingService;
import spring.sql.hibernate.Demo.Service.IConstraintDemoService;
import spring.sql.hibernate.Demo.Utility.IDateAndNumOfGuests;
import spring.sql.hibernate.Demo.Utility.IPaymentTypeDateAndAmount;
import spring.sql.hibernate.POJO.EmpStatus;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/createAndGetDetails", method = RequestMethod.GET)
public class DemoController {

    @Autowired
    IConstraintDemoService iConstraintDemoService;

    @Autowired
    IBookingService iBookingService;

    @GetMapping(value = "/demo")
    public ConstraintDemo createAndGetDetails(@RequestParam Map<String, String> params) {

        ConstraintDemo constraintDemo = new ConstraintDemo(
                params.get("email"),
                Integer.valueOf(params.get("rangeId")),
                EmpStatus.valueOf(params.get("empStatus").toUpperCase()),
                params.get("productId")
        );
        return iConstraintDemoService.save(constraintDemo);
    }

    @GetMapping(value = "/bookings")
    public List<IDateAndNumOfGuests> getBookingsData(@RequestParam Map<String, String> params) {
        return iBookingService.getNumberOfGuestOnEachDay();
    }

    @GetMapping(value = "/numberOfPayment")
    public Double getTotalNumberOfPaymentsUsingCriteria(@RequestParam(required = false, name = "paymentType") String paymentType) {
        String res = URLDecoder.decode(paymentType,StandardCharsets.UTF_8);
        System.out.println(res);
        return iBookingService.getNumberOfPaymentsBy(res);
    }


    @GetMapping(value = "/getPaymentData")
    public List<IPaymentTypeDateAndAmount> getAllPaymentData() {
        return iBookingService.getAllPaymentData();
    }

    @GetMapping(value = "/getMinBookingDate")
    public Date getMinBookingDate() {
        return iBookingService.getBookingDateWhichHasMAXMINAmt();
    }
}
