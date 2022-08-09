package com.kuehne.nagel.decathlon.model;

import java.util.HashMap;
import java.util.Map;

public class Athlete {

    private String name;
    private Map<String, String> eventResultMap = new HashMap<>();
    private Map<String, Integer> eventScoreMap = new HashMap<>();
    private String place;
    private Integer score;
    private Athlete previous;

    public Athlete(String name) {
        this.name = name;
    }

    public void putResult(String eventName, String result) {
        eventResultMap.put(eventName, result);
    }

    public void putScore(String eventName, Integer score) {
        eventScoreMap.put(eventName, score);
    }

    public int calculateTotalScore() {
        score = eventScoreMap.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        return score;
    }

    public Map<String, String> getEventResultMap() {
        return eventResultMap;
    }

    public Map<String, Integer> getEventScoreMap() {
        return eventScoreMap;
    }

    public String getPlace() {
        return place;
    }

    public Integer getScore() {
        return score;
    }

    public Athlete getPrevious() {
        return previous;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setPrevious(Athlete previous) {
        this.previous = previous;
    }

    public String getName() {
        return name;
    }
}
