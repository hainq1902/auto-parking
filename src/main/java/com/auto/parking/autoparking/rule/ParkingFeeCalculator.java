package com.auto.parking.autoparking.rule;

import java.time.LocalDateTime;

/**
 * Interface to support implementing different calculator dynamically.
 */
public interface ParkingFeeCalculator {

    /**
     * Calculate fee
     * @param from from date and time
     * @param to to date and time
     * @return {@code Double} fee in {@code Double}
     */
    Double calculate(LocalDateTime from, LocalDateTime to);
}
