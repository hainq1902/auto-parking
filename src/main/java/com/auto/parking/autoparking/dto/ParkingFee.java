package com.auto.parking.autoparking.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class ParkingFee {
    private String zone;
    private LocalDateTime from;
    private LocalDateTime to;
    private Double fee;

    public ParkingFee() {
    }

    public ParkingFee(String zone, LocalDateTime from, LocalDateTime to, Double fee) {
        this.zone = zone;
        this.from = from;
        this.to = to;
        this.fee = fee;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }
}
