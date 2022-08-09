package com.kuehne.nagel.decathlon.calculation;

import com.kuehne.nagel.decathlon.utils.DecathlonException;


public class BaseEventCalculator {

    private String eventName;
    private EventReferences eventReference;

    public BaseEventCalculator(String eventName, EventReferences eventReference) {
        this.eventName = eventName;
        this.eventReference = eventReference;
    }

    public Integer calculate(String athletePerformance) {
        double athletePerformanceDouble;
        try {
            athletePerformanceDouble = Double.parseDouble(athletePerformance);
        } catch (Exception e) {
            throw new DecathlonException("invalid athletePerformance input: " + athletePerformance + " , should be number. Please check your input.");
        }

        Double result = eventReference.getReferenceA() * Math.pow(eventReference.getReferenceB() - athletePerformanceDouble, eventReference.getReferenceC());
        return result.intValue();
    }


    public String getEventName() {
        return eventName;
    }
}
