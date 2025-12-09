package com.sevitours.demo.tour_bill;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TourBillDto {
    private Integer tourId;
    private Integer clientId;
    private BigDecimal adultAmount;
    private BigDecimal kidAmount;
    private BigDecimal iva;
}
