package com.auto.parking.autoparking.service;

/**
 * Service to limit the amount of request
 */
public interface RequestRateLimitService {

    /**
     * The method will consume a request then the amount of max request for {@param clientId} should be reduce by 1 after finishing.
     * @param clientId client id, not null
     * @return {@code Boolean} true if a request can be execute, false if {@param clientId} exceeds its max allowed request
     */
    Boolean consumeRequest(String clientId);
}
