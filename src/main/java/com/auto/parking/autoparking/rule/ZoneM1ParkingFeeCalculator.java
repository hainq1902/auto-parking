package com.auto.parking.autoparking.rule;

import com.auto.parking.autoparking.util.ParkingFeePeriod;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Component("M1")
public class ZoneM1ParkingFeeCalculator extends AbstractParkingFeeCalculator {

    private static final int M1_PARKING_FEE_PER_MIN = 1;

    @Override
    protected Double calculate(List<ParkingFeePeriod> parkingFeePeriods) {
        final long duration = Duration.between(parkingFeePeriods.get(0).getFrom(), parkingFeePeriods.get(0).getTo()).toMinutes();
        return (double) (duration * M1_PARKING_FEE_PER_MIN);
    }

    @Override
    protected List<ParkingFeePeriod> findParkingFeePeriods(LocalDateTime from, LocalDateTime to) {
        return Collections.singletonList(new ParkingFeePeriod(from, to));
    }
}
