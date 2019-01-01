package com.auto.parking.autoparking.rule;

import com.auto.parking.autoparking.util.FeeCalculationUtil;
import com.auto.parking.autoparking.util.ParkingFeePeriod;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component("M2")
public class ZoneM2ParkingFeeCalculator extends AbstractParkingFeeCalculator {

    private static final int M2_PARKING_WEEK_DAYS = 100;
    private static final int M2_PARKING_WEEK_END = 200;

    private static boolean isWeekend(LocalDateTime localDateTime) {
        return DayOfWeek.SATURDAY == localDateTime.getDayOfWeek() || DayOfWeek.SUNDAY == localDateTime.getDayOfWeek();
    }

    @Override
    protected List<ParkingFeePeriod> findParkingFeePeriods(LocalDateTime from, LocalDateTime to) {
        return FeeCalculationUtil.findParkingFeePeriods(from, to, ZoneM2ParkingFeeCalculator::findNextChunk);
    }

    private static LocalDateTime findNextChunk(LocalDateTime from) {
        final LocalDateTime nextChunk;
        switch (from.getDayOfWeek()) {
            case SATURDAY:
                nextChunk = LocalDateTime.of(from.toLocalDate().plusDays(2), LocalTime.MIDNIGHT);
                break;
            case SUNDAY:
                nextChunk = LocalDateTime.of(from.toLocalDate().plusDays(1), LocalTime.MIDNIGHT);
                break;
            default:
                nextChunk = LocalDateTime.of(from.toLocalDate().plusDays(DayOfWeek.SATURDAY.getValue() - from.getDayOfWeek().getValue()), LocalTime.MIDNIGHT);
        }
        return nextChunk;
    }

    private static Double calculatePerPeriod(ParkingFeePeriod parkingFeePeriod) {
        final Duration duration = Duration.between(parkingFeePeriod.getFrom(), parkingFeePeriod.getTo());
        if (isWeekend(parkingFeePeriod.getFrom())) {
            return Math.ceil((double) duration.toMinutes() / 60) * M2_PARKING_WEEK_END;
        }
        return Math.ceil((double) duration.toMinutes() / 60) * M2_PARKING_WEEK_DAYS;
    }

    @Override
    protected Double calculate(List<ParkingFeePeriod> parkingFeePeriods) {
        return parkingFeePeriods.parallelStream().mapToDouble(ZoneM2ParkingFeeCalculator::calculatePerPeriod).sum();
    }
}
