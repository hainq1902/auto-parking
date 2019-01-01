package com.auto.parking.autoparking.rule;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class ZoneM3ParkingFeeCalculatorTest {
    @Test
    public void calculateLessThan1Min() {
        final LocalDateTime toDateTime = LocalDateTime.of(2018, 12, 22, 19, 46, 45);
        final LocalDateTime fromDateTime = LocalDateTime.of(2018, 12, 22, 19, 45, 55);

        final ZoneM3ParkingFeeCalculator calculator = new ZoneM3ParkingFeeCalculator();
        assertEquals(Double.valueOf(0), calculator.calculate(fromDateTime, toDateTime));
    }

    @Test
    public void calculatePerMin() {
        final LocalDateTime toDateTime = LocalDateTime.of(2018, 12, 22, 9, 46, 45);
        final LocalDateTime fromDateTime = LocalDateTime.of(2018, 12, 22, 8, 45, 5);

        final ZoneM3ParkingFeeCalculator calculator = new ZoneM3ParkingFeeCalculator();
        assertEquals(Double.valueOf(2), calculator.calculate(fromDateTime, toDateTime));
    }

    @Test
    public void calculate2HourMin() {
        final LocalDateTime toDateTime = LocalDateTime.of(2018, 12, 22, 10, 46, 45);
        final LocalDateTime fromDateTime = LocalDateTime.of(2018, 12, 22, 8, 45, 5);

        final ZoneM3ParkingFeeCalculator calculator = new ZoneM3ParkingFeeCalculator();
        assertEquals(Double.valueOf(122), calculator.calculate(fromDateTime, toDateTime));
    }

    @Test
    public void calculatePerMinPrice2() {
        final LocalDateTime toDateTime = LocalDateTime.of(2018, 12, 22, 19, 46, 45);
        final LocalDateTime fromDateTime = LocalDateTime.of(2018, 12, 22, 19, 45, 5);

        final ZoneM3ParkingFeeCalculator calculator = new ZoneM3ParkingFeeCalculator();
        assertEquals(Double.valueOf(3), calculator.calculate(fromDateTime, toDateTime));
    }

    @Test
    public void calculateSunday() {
        final LocalDateTime toDateTime = LocalDateTime.of(2018, 12, 23, 23, 46, 45);
        final LocalDateTime fromDateTime = LocalDateTime.of(2018, 12, 23, 19, 45, 5);

        final ZoneM3ParkingFeeCalculator calculator = new ZoneM3ParkingFeeCalculator();
        assertEquals(Double.valueOf(0), calculator.calculate(fromDateTime, toDateTime));
    }

    @Test
    public void calculateDays() {
        final LocalDateTime toDateTime = LocalDateTime.of(2018, 12, 25, 23, 46, 0);
        final LocalDateTime fromDateTime = LocalDateTime.of(2018, 12, 22, 7, 45, 0);

        final ZoneM3ParkingFeeCalculator calculator = new ZoneM3ParkingFeeCalculator();
        assertEquals(Double.valueOf(9963), calculator.calculate(fromDateTime, toDateTime));
    }


    @Test
    public void c1() {
        final LocalDateTime toDateTime = LocalDateTime.of(2018, 12, 24, 10, 0, 0);
        final LocalDateTime fromDateTime = LocalDateTime.of(2018, 12, 23, 7, 45, 55);

        final ZoneM3ParkingFeeCalculator calculator = new ZoneM3ParkingFeeCalculator();
        assertEquals(Double.valueOf((3 * 8 * 60) + (2 * 1 * 60)), calculator.calculate(fromDateTime, toDateTime));
    }
}