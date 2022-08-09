package com.kuehne.nagel.decathlon.operations;

import com.kuehne.nagel.decathlon.model.Athlete;
import com.kuehne.nagel.decathlon.calculation.BaseEventCalculator;
import com.kuehne.nagel.decathlon.calculation.EventCalculatorFactory;

import java.util.List;
import java.util.Map;

public class CalculateOperation {

    EventCalculatorFactory eventCalculatorFactory = new EventCalculatorFactory();

    public void calculateAllCompetitionScore(List<Athlete> athletes) {
        Map<String, BaseEventCalculator> eventCalculatorMap = eventCalculatorFactory.createAllEventCalculator();
        athletes.parallelStream()
                .forEach(athlete -> {
                    for (Map.Entry<String, String> entry : athlete.getEventResultMap().entrySet()) {
                        BaseEventCalculator calculator = eventCalculatorMap.get(entry.getKey());
                        athlete.putScore(entry.getKey(), calculator.calculate(entry.getValue()));
                    }
                });
    }

}
