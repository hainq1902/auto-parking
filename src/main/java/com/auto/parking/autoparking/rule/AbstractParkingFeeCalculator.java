package com.auto.parking.autoparking.rule;

import com.auto.parking.autoparking.util.ParkingFeePeriod;

import java.time.LocalDateTime;
import java.util.List;

public abstract class AbstractParkingFeeCalculator implements ParkingFeeCalculator {

    @Override
    public Double calculate(LocalDateTime from, LocalDateTime to) {
        assert to != null: "To date cannot be null";
        assert from != null: "From date cannot be null";
        assert to.isAfter(from): "To date must be greater than from date";

        final List<ParkingFeePeriod> parkingFeePeriods = findParkingFeePeriods(from, to);
        return calculate(parkingFeePeriods);
    }

    protected abstract Double calculate(List<ParkingFeePeriod> parkingFeePeriods);

    protected abstract List<ParkingFeePeriod> findParkingFeePeriods(LocalDateTime from, LocalDateTime to);
}
