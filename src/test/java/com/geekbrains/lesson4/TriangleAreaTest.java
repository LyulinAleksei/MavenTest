package com.geekbrains.lesson4;

import com.geekbrains.lesson3.lesson4.TriangleArea;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriangleAreaTest {

    private static final Logger logger = LoggerFactory.getLogger(TriangleAreaTest.class);

    @BeforeAll
    static void beforeAll() {
        logger.info("INFO");
        logger.debug("DEBUG");
        logger.error("ERROR");

    }

    @Test
    @DisplayName("Позитивный тест вычесления площади треугольника по 3 сторонам(валидные)")
    void givenNumbersWhenCollTriangleAreaThenResult() throws Exception {
        double result = TriangleArea.calculateArea(2.5, 3.5, 4.5);
        Assertions.assertEquals(4.353070037341462, result);
    }

    @Test
    @DisplayName("Тест когда одна из сторон треугольника <=0")
    void exceptionWhenCollTriangleAreaNullSide() {
        Assertions.assertThrows(Exception.class, () -> TriangleArea.calculateArea(2, 0, 4));

    }
}
