package com.kuehne.nagel.decathlon.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class StringUtilTest {

    @Test
    void only_given_second_conversion_should_be_success() {

        // Act
        String result = StringUtil.convertFromMinuteToSecond("41.1");

        // Assert
        Assertions.assertEquals("41.1", result);
    }

    @Test
    void with_minute_conversion_should_be_success() {

        // Act
        String result = StringUtil.convertFromMinuteToSecond("1:41.1");

        // Assert
        Assertions.assertEquals("101.1", result);
    }

    @Test
    void with_hour_conversion_should_be_success() {

        // Act
        DecathlonException decathlonException = assertThrows(DecathlonException.class, () -> StringUtil.convertFromMinuteToSecond("1:1:41.1"));

        // Assert
        Assertions.assertEquals("input format should be mm:ss.SSS", decathlonException.getMessage());
    }

}