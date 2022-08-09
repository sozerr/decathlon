package com.kuehne.nagel.decathlon.calculation;

import com.kuehne.nagel.decathlon.utils.DecathlonException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EventCalculatorTest {

    @Test
    void calculate_100m_usain_bolt_point() {
        // WR	 Usain Bolt (JAM)	9.58 s	1,202point
        // Arrange
        SecondsEventCalculator eventCalculator = new SecondsEventCalculator("",   EventReferences.M100);

        // Act
        Integer usainBoltPoint = eventCalculator.calculate("9.58");

        // Assert
        Assertions.assertEquals(1202, usainBoltPoint);

    }

    @Test
    void calculate_1500m_robert_baker_point_for_exception() {
        // DB	 Robert Baker (USA)	3:58.7 min:s	963point
        // Arrange
        MetresEventCalculator eventCalculator = new MetresEventCalculator("",   EventReferences.M1500);

        // Act
        DecathlonException decathlonException = assertThrows(DecathlonException.class, () -> eventCalculator.calculate("3:58.7"));
        ;

        // Assert
        Assertions.assertEquals("invalid athletePerformance input: 3:58.7 , should be number. Please check your input.", decathlonException.getMessage());
    }

    @Test
    void calculate_1500m_robert_baker_point_success() {
        // DB	 Robert Baker (USA)	3:58.7 min:s	963point
        // Arrange
        SecondsEventCalculator eventCalculator = new SecondsEventCalculator("",   EventReferences.M1500);

        // Act
        Integer robertBakerPoint = eventCalculator.calculate("3:58.7");

        // Assert
        Assertions.assertEquals(963, robertBakerPoint);
    }
}