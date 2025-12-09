package com.sevitours.demo.rental_bill;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class RentalBillDto {
    private Integer rentalId;
    private Integer clientId;
    private Float AdultAmount;
    private Float KidAmount;
    private Float iva;
}