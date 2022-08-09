package com.kuehne.nagel.decathlon.calculation;

import com.kuehne.nagel.decathlon.utils.StringUtil;

public class SecondsEventCalculator extends BaseEventCalculator {

    public SecondsEventCalculator(String eventName, EventReferences eventReference) {
        super(eventName, eventReference);
    }

    @Override
    public Integer calculate(String athletePerformance){
        return super.calculate(StringUtil.convertFromMinuteToSecond(athletePerformance));
    }

}
