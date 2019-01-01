package com.auto.parking.autoparking.service;

import com.auto.parking.autoparking.exception.BusinessException;
import com.auto.parking.autoparking.rule.ParkingFeeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ParkingFeeCalculatorServiceImpl implements ParkingFeeCalculatorService {

    private static final Map<String, ParkingFeeCalculator> calculatorMap = new HashMap<>();

    private final ApplicationContext applicationContext;

    @Autowired
    public ParkingFeeCalculatorServiceImpl(ApplicationContext applicationContext) {this.applicationContext = applicationContext;}

    @Override
    public Double calculate(String zone, LocalDateTime from, LocalDateTime to) throws BusinessException {
        final ParkingFeeCalculator calculator = calculatorMap.get(zone.toUpperCase());
        if (calculator == null) {
            throw new BusinessException(String.format("%s is not supported.", zone));
        }
        return calculator.calculate(from, to);
    }

    @PostConstruct
    public void init() {
        final Map<String, ParkingFeeCalculator> beans = applicationContext.getBeansOfType(ParkingFeeCalculator.class);
        if (beans != null) {
            beans.forEach((k,v) -> calculatorMap.put(k.toUpperCase(), v));
        }
    }
}
