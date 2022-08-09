package com.kuehne.nagel.decathlon.calculation;


import com.kuehne.nagel.decathlon.utils.DecathlonException;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EventCalculatorFactory {

    public Map<String, BaseEventCalculator> createAllEventCalculator() {
        return Arrays.stream(EventReferences.values())
                .map(eventReferences -> {
                    if (Unit.METER.equals(eventReferences.getUnit())) {
                        return new MetresEventCalculator(eventReferences.getXmlLabel(), eventReferences);
                    } else if (Unit.SECOND.equals(eventReferences.getUnit())) {
                        return new SecondsEventCalculator(eventReferences.getXmlLabel(), eventReferences);
                    } else {
                        throw new DecathlonException("eventReferences unit not implemented unit: " + eventReferences.getUnit());
                    }

                })
                .collect(Collectors.toMap(BaseEventCalculator::getEventName, Function.identity()));
    }

}
