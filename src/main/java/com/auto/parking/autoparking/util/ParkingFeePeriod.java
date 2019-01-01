package com.auto.parking.autoparking.util;

import java.time.LocalDateTime;

public class ParkingFeePeriod {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public ParkingFeePeriod(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }
}
