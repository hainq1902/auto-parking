package com.auto.parking.autoparking.rule;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class ZoneM1ParkingFeeCalculatorTest {

    @Test
    public void calculateLessThan1Min() {
        final LocalDateTime toDateTime = LocalDateTime.of(2018, 12, 22, 19, 46, 45);
        final LocalDateTime fromDateTime = LocalDateTime.of(2018, 12, 22, 19, 45, 55);

        final ZoneM1ParkingFeeCalculator calculator = new ZoneM1ParkingFeeCalculator();
        assertEquals(Double.valueOf(0), calculator.calculate(fromDateTime, toDateTime));
    }

    @Test
    public void calculatePerMin() {
        final LocalDateTime toDateTime = LocalDateTime.of(2018, 12, 22, 19, 46, 45);
        final LocalDateTime fromDateTime = LocalDateTime.of(2018, 12, 22, 19, 45, 5);

        final ZoneM1ParkingFeeCalculator calculator = new ZoneM1ParkingFeeCalculator();
        assertEquals(Double.valueOf(1), calculator.calculate(fromDateTime, toDateTime));
    }

    @Test
    public void calculate59Min() {
        final LocalDateTime toDateTime = LocalDateTime.of(2018, 12, 22, 20, 0, 45);
        final LocalDateTime fromDateTime = LocalDateTime.of(2018, 12, 22, 19, 0, 55);

        final ZoneM1ParkingFeeCalculator calculator = new ZoneM1ParkingFeeCalculator();
        assertEquals(Double.valueOf(59), calculator.calculate(fromDateTime, toDateTime));
    }

    @Test
    public void calculate1Hour() {
        final LocalDateTime toDateTime = LocalDateTime.of(2018, 12, 22, 20, 1, 45);
        final LocalDateTime fromDateTime = LocalDateTime.of(2018, 12, 22, 19, 0, 55);

        final ZoneM1ParkingFeeCalculator calculator = new ZoneM1ParkingFeeCalculator();
        assertEquals(Double.valueOf(60), calculator.calculate(fromDateTime, toDateTime));
    }

    @Test
    public void calculateOvernight() {
        final LocalDateTime toDateTime = LocalDateTime.of(2018, 12, 23, 0, 1, 45);
        final LocalDateTime fromDateTime = LocalDateTime.of(2018, 12, 22, 23, 0, 55);

        final ZoneM1ParkingFeeCalculator calculator = new ZoneM1ParkingFeeCalculator();
        assertEquals(Double.valueOf(60), calculator.calculate(fromDateTime, toDateTime));
    }

    @Test(expected = AssertionError.class)
    public void calculateException() {
        final LocalDateTime toDateTime = LocalDateTime.of(2018, 12, 23, 0, 1, 45);
        final LocalDateTime fromDateTime = LocalDateTime.of(2018, 12, 22, 23, 0, 55);

        final ZoneM1ParkingFeeCalculator calculator = new ZoneM1ParkingFeeCalculator();
        calculator.calculate(toDateTime, fromDateTime);
    }
}