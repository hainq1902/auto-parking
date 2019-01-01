package com.auto.parking.autoparking.service;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class RequestRateLimitServiceImpl implements RequestRateLimitService {

    private static final Integer INTERVAL_MINS = 60;
    private static final Integer MAX_REQUESTS = 5;

    private static final Map<String, Integer> requestRates = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduledService = Executors.newSingleThreadScheduledExecutor();

    @Override
    public Boolean consumeRequest(String clientId) {
        assert clientId != null: "clientId cannot be null";

        boolean consumeRequest = true;
        synchronized (clientId.intern()) {
            if (requestRates.containsKey(clientId)) {
                    if (requestRates.get(clientId) > 0)  {
                        requestRates.put(clientId, requestRates.get(clientId) - 1);
                    }
                    else {
                        consumeRequest = false;
                    }
            }
            else {
                registerRequest(clientId);
            }
        }
        return consumeRequest;
    }

    private void registerRequest(String clientId) {
        RequestRateLimitServiceImpl.requestRates.put(clientId, MAX_REQUESTS);
        scheduledService.schedule(() -> RequestRateLimitServiceImpl.requestRates.remove(clientId), INTERVAL_MINS, TimeUnit.MINUTES);
    }
}
