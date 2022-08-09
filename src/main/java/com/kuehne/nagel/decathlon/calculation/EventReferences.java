package com.kuehne.nagel.decathlon.calculation;

/**
 * Event	        A	    B	    C
 * m100
 * LongJump
 * ShotPut
 * HighJump
 * m400
 * m110Hurdles
 * DiscusThrow
 * PoleVault
 * JavelinThrow
 * m1500
 */
public enum EventReferences {

    M100(25.4347, 18, 1.81, Unit.SECOND, "m100"),
    LONG_JUMP(0.14354, 220, 1.4, Unit.METER, "LongJump"),
    SHOT_PUT(51.39, 1.5, 1.05, Unit.METER, "ShotPut"),
    HIGH_JUMP(0.8465, 75, 1.42, Unit.METER, "HighJump"),
    M400(1.53775, 82, 1.81, Unit.SECOND, "m400"),
    M110_HURDLES(5.74352, 28.5, 1.92, Unit.SECOND, "m110Hurdles"),
    DISCUS_THROW(12.91, 4, 1.1, Unit.METER, "discusThrow"),
    POLE_VAULT(0.2797, 100, 1.35, Unit.METER, "poleVault"),
    JAVELIN_THROW(10.14, 7, 1.08, Unit.METER, "javelinThrow"),
    M1500(0.03768, 480, 1.85, Unit.SECOND, "m1500");

    private double referenceA;
    private double referenceB;
    private double referenceC;
    private Unit unit;
    public final String xmlLabel;


    EventReferences(double referenceA, double referenceB, double referenceC, Unit unit, String xmlLabel) {
        this.referenceA = referenceA;
        this.referenceB = referenceB;
        this.referenceC = referenceC;
        this.unit = unit;
        this.xmlLabel = xmlLabel;
    }

    public double getReferenceA() {
        return referenceA;
    }

    public double getReferenceB() {
        return referenceB;
    }

    public double getReferenceC() {
        return referenceC;
    }

    public Unit getUnit() {
        return unit;
    }

    public String getXmlLabel() {
        return xmlLabel;
    }
}
