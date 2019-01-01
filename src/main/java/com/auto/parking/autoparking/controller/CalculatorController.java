package com.auto.parking.autoparking.controller;

import com.auto.parking.autoparking.dto.ParkingFee;
import com.auto.parking.autoparking.exception.BusinessException;
import com.auto.parking.autoparking.service.ParkingFeeCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class CalculatorController {

    private final ParkingFeeCalculatorService parkingFeeCalculatorService;

    @Autowired
    public CalculatorController(ParkingFeeCalculatorService parkingFeeCalculatorService) {this.parkingFeeCalculatorService = parkingFeeCalculatorService;}

    @RequestMapping(value = "/calculate/{zone}", method = RequestMethod.GET)
    public ParkingFee calculateM1(@PathVariable("zone") String zone,
                                  @RequestParam("from")
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                          LocalDateTime from,
                                  @RequestParam("to")
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                              LocalDateTime to) throws BusinessException {
        final Double fee = parkingFeeCalculatorService.calculate(zone, from, to);
        return new ParkingFee(zone, from, to, fee);
    }
}
