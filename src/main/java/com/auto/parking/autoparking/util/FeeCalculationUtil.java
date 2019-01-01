package com.auto.parking.autoparking.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public final class FeeCalculationUtil {

    private FeeCalculationUtil() {}

    /**
     * Find all parking time durations which have same fee calculation in a week.
     * @param from from date and time
     * @param to to date and time
     * @param chunkFunction function to find which durations use different fee calculation.
     * @return List of {@code ParkingFeePeriod} which share the same fee calculation.
     */
    public static List<ParkingFeePeriod> findParkingFeePeriods(LocalDateTime from, LocalDateTime to, Function<LocalDateTime, LocalDateTime> chunkFunction) {
        final List<LocalDateTime> timePeriods = new ArrayList<>();
        timePeriods.add(from);
        LocalDateTime currentChunk = chunkFunction.apply(from);

        while(hasMoreChunk(to, currentChunk)) {
            timePeriods.add(currentChunk);
            currentChunk = getNextChunk(timePeriods, chunkFunction);
        }

        timePeriods.add(to);

        return convertToParkingPeriod(timePeriods);
    }

    private static LocalDateTime getNextChunk(List<LocalDateTime> timePeriods, Function<LocalDateTime, LocalDateTime> chunkFunction) {
        return chunkFunction.apply(timePeriods.get(timePeriods.size() - 1));
    }

    private static List<ParkingFeePeriod> convertToParkingPeriod(List<LocalDateTime> timePeriods) {
        final List<ParkingFeePeriod> result = new LinkedList<>();

        for (int i = 0; i < timePeriods.size() - 1; i ++) {
            result.add(new ParkingFeePeriod(timePeriods.get(i), timePeriods.get(i + 1)));
        }
        return result;
    }

    private static boolean hasMoreChunk(LocalDateTime localDateTime, LocalDateTime currentChunk) {
        return localDateTime.isAfter(currentChunk);
    }
}
