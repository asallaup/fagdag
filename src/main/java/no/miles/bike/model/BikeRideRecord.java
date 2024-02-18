package no.miles.bike.model;

import java.time.OffsetDateTime;

public record BikeRideRecord(
        OffsetDateTime startedAt,
        OffsetDateTime endedAt,
        int duration,
        int startStationId,
        String startStationName,
        String startStationDescription,
        double startStationLatitude,
        double startStationLongitude,
        int endStationId,
        String endStationName,
        String endStationDescription,
        double endStationLatitude,
        double endStationLongitude
) {
    // Additional methods or validations can be added here if needed
}

