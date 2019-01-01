package com.auto.parking.autoparking.rule;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class ZoneM2ParkingFeeCalculatorTest {

    @Test
    public void calculate1HourWeekdays() {
        final LocalDateTime to = LocalDateTime.of(2018, 12, 21, 20, 9, 5);
        final LocalDateTime from = LocalDateTime.of(2018, 12, 21, 19, 9, 5);

        final ZoneM2ParkingFeeCalculator calculator = new ZoneM2ParkingFeeCalculator();
        assertEquals(Double.valueOf(100), calculator.calculate(from, to));
    }

    @Test
    public void calculateLess1HourWeekdays() {
        final LocalDateTime to = LocalDateTime.of(2018, 12, 21, 20, 3, 5);
        final LocalDateTime from = LocalDateTime.of(2018, 12, 21, 19, 9, 5);

        final ZoneM2ParkingFeeCalculator calculator = new ZoneM2ParkingFeeCalculator();
        assertEquals(Double.valueOf(100), calculator.calculate(from, to));
    }

    @Test
    public void calculateMore1HourWeekdays() {
        final LocalDateTime to = LocalDateTime.of(2018, 12, 21, 20, 39, 5);
        final LocalDateTime from = LocalDateTime.of(2018, 12, 21, 19, 9, 5);

        final ZoneM2ParkingFeeCalculator calculator = new ZoneM2ParkingFeeCalculator();
        assertEquals(Double.valueOf(200), calculator.calculate(from, to));
    }


    @Test
    public void calculate1HourWeekend() {
        final LocalDateTime to = LocalDateTime.of(2018, 12, 22, 20, 9, 5);
        final LocalDateTime from = LocalDateTime.of(2018, 12, 22, 19, 9, 5);

        final ZoneM2ParkingFeeCalculator calculator = new ZoneM2ParkingFeeCalculator();
        assertEquals(Double.valueOf(200), calculator.calculate(from, to));
    }

    @Test
    public void calculateLess1HourWeekend() {
        final LocalDateTime to = LocalDateTime.of(2018, 12, 23, 20, 3, 5);
        final LocalDateTime from = LocalDateTime.of(2018, 12, 23, 19, 9, 5);

        final ZoneM2ParkingFeeCalculator calculator = new ZoneM2ParkingFeeCalculator();
        assertEquals(Double.valueOf(200), calculator.calculate(from, to));
    }

    @Test
    public void calculateMore1HourWeekend() {
        final LocalDateTime to = LocalDateTime.of(2018, 12, 22, 20, 39, 5);
        final LocalDateTime from = LocalDateTime.of(2018, 12, 22, 19, 9, 5);

        final ZoneM2ParkingFeeCalculator calculator = new ZoneM2ParkingFeeCalculator();
        assertEquals(Double.valueOf(400), calculator.calculate(from, to));
    }

    @Test
    public void calculateMix() {
        final LocalDateTime to = LocalDateTime.of(2018, 12, 25, 20, 39, 5);
        final LocalDateTime from = LocalDateTime.of(2018, 12, 21, 19, 9, 5);

        final ZoneM2ParkingFeeCalculator calculator = new ZoneM2ParkingFeeCalculator();
        assertEquals(Double.valueOf(14600), calculator.calculate(from, to));
    }
}