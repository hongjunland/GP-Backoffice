package com.example.demo.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("직책 도메인 테스트")
public class PositionTest {
    @Test
    public void testFromLongValue_ValidValue_ReturnsCorrectEnum() {
        Position position = Position.fromLongValue(2L);
        assertEquals(Position.EXECUTIVE, position);
    }
    @DisplayName("범위값 이외 예외 처리")
    @Test
    public void testFromLongValue_InvalidValue_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Position.fromLongValue(5L);
        });
    }

}
