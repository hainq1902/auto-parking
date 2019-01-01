package com.auto.parking.autoparking.rule;

import com.auto.parking.autoparking.util.FeeCalculationUtil;
import com.auto.parking.autoparking.util.ParkingFeePeriod;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.function.Function;

@Component("M3")
public class ZoneM3ParkingFeeCalculator extends AbstractParkingFeeCalculator {

    private static final int M3_PARKING_FEE_1 = 2;
    private static final int M3_PARKING_FEE_2 = 3;

    private static final LocalTime FROM_HOUR = LocalTime.of(8, 0);
    private static final LocalTime TO_HOUR = LocalTime.of(16, 0);

    @Override
    protected Double calculate(List<ParkingFeePeriod> parkingFeePeriods) {
        final Function<Long, Long> durationPromotion = new FreeDuration();
        return parkingFeePeriods.parallelStream()
                                .mapToDouble(parkingFeePeriod -> calculatePerPeriod(parkingFeePeriod.getFrom(), parkingFeePeriod.getTo(), durationPromotion))
                                .sum();
    }

    @Override
    protected List<ParkingFeePeriod> findParkingFeePeriods(LocalDateTime from, LocalDateTime to) {
        return FeeCalculationUtil.findParkingFeePeriods(from, to, ZoneM3ParkingFeeCalculator::findNextChunk);
    }

    private static LocalDateTime findNextChunk(LocalDateTime from) {
        final LocalDateTime nextChunk;
        if (from.getDayOfWeek() == DayOfWeek.SUNDAY) {
            nextChunk = LocalDateTime.of(from.toLocalDate().plusDays(1), LocalTime.MIDNIGHT);
        } else {
            nextChunk = LocalDateTime.of(from.toLocalDate().plusDays(DayOfWeek.SUNDAY.getValue() - from.getDayOfWeek().getValue()), LocalTime.MIDNIGHT);
        }
        return nextChunk;
    }

    private static Double calculatePerPeriod(LocalDateTime from, LocalDateTime to, Function<Long, Long> durationPromotion) {
        double result = 0d;
        if (DayOfWeek.SUNDAY != from.getDayOfWeek()) {
            if (from.toLocalDate().equals(to.toLocalDate())) {
                result = calculateTime(from.toLocalTime(), to.toLocalTime(), durationPromotion);
            } else {
                final LocalDateTime fromDate = LocalDateTime.of(from.toLocalDate(), LocalTime.MAX);
                final LocalDateTime toDate = LocalDateTime.of(to.toLocalDate(), LocalTime.MIDNIGHT);

                //Calculate from date
                result += calculateTime(from.toLocalTime(), fromDate.toLocalTime(), durationPromotion);

                //Calculate to date
                result += calculateTime(toDate.toLocalTime(), to.toLocalTime(), durationPromotion);

                //Calculate date between
                result += countDaysBetween(fromDate, toDate) * calculateTime(LocalTime.MIN, LocalTime.MAX, durationPromotion);
            }
        }
        return result;
    }

    private static int countDaysBetween(LocalDateTime fromDate, LocalDateTime toDate) {
        return Period.between(fromDate.toLocalDate(), toDate.toLocalDate()).getDays() - 1;
    }

    private static Double calculateTime(LocalTime from, LocalTime to, Function<Long, Long> freeMins) {
        double result = 0d;
        if (isNotInPromotedHours(from, to)) {
            result = calculateNormalHours(from, to);
        }
        else {
            final LocalTime promotionFromTime = FROM_HOUR.isAfter(from) ? FROM_HOUR : from;
            final LocalTime promotionToTime = TO_HOUR.isAfter(to) ? to : TO_HOUR;

            result += calculateNormalHours(from, promotionFromTime);
            result += calculatePromoteHours(promotionFromTime, promotionToTime, freeMins);
            result += calculateNormalHours(promotionToTime, to);
        }

        return result;
    }

    private static double calculateNormalHours(LocalTime from, LocalTime to) {
        return computeDuration(from, to) * M3_PARKING_FEE_2;
    }

    private static long calculatePromoteHours(LocalTime from, LocalTime to, Function<Long, Long> freeMins) {
        return freeMins.apply(computeDuration(from, to)) * M3_PARKING_FEE_1;
    }

    private static long computeDuration(LocalTime from, LocalTime to) {
        return (LocalTime.MAX == to) ? Duration.between(from, to).toMinutes() + 1 : Duration.between(from, to).toMinutes();
    }

    private static boolean isNotInPromotedHours(LocalTime from, LocalTime to) {
        return TO_HOUR.isBefore(from) || FROM_HOUR.isAfter(to);
    }

    private static class FreeDuration implements Function<Long, Long> {
        private long freeDurationMins = 60;

        @Override
        public Long apply(Long duration) {
            synchronized (this) {
                final Long result = Math.max(duration - freeDurationMins, 0);
                freeDurationMins = Math.max(freeDurationMins - duration, 0);
                return result;
            }
        }
    }
}
