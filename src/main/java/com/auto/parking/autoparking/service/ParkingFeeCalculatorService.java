package com.auto.parking.autoparking.service;

import com.auto.parking.autoparking.exception.BusinessException;

import java.time.LocalDateTime;

public interface ParkingFeeCalculatorService {

    /**
     * Calculate parking fee base on zone and parking duration.
     * @param zone zone to be calculated.
     * @param from parking from date and time.
     * @param to   parking to date and time.
     * @return {@code Double} value after calculation.
     * @throws BusinessException in case zone is not support.
     */
    Double calculate(String zone, LocalDateTime from, LocalDateTime to) throws BusinessException;
}
