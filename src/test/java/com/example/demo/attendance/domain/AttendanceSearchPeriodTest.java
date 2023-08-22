package com.example.demo.attendance.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

@DisplayName("AttendanceSearchPeriod 단위 테스트")
public class AttendanceSearchPeriodTest {

    @Test
    @DisplayName("AttendanceSearchPeriod의 equals와 hashCode 메서드 검증")
    public void AttendanceSearchPeriod_EqualsAndHashCodeTest() {
        LocalDate startDate1 = LocalDate.of(2022, 5, 1);
        LocalDate endDate1 = LocalDate.of(2022, 5, 31);

        LocalDate startDate2 = LocalDate.of(2022, 6, 1);
        LocalDate endDate2 = LocalDate.of(2022, 6, 30);

        AttendanceSearchPeriod period1 = AttendanceSearchPeriod.builder()
                .startDate(startDate1)
                .endDate(endDate1)
                .build();

        AttendanceSearchPeriod period2 = AttendanceSearchPeriod.builder()
                .startDate(startDate2)
                .endDate(endDate2)
                .build();

        AttendanceSearchPeriod period3 = AttendanceSearchPeriod.builder()
                .startDate(startDate1)
                .endDate(endDate1)
                .build();

        assertNotEquals(period1, period2);
        assertNotEquals(period1.hashCode(), period2.hashCode());

        assertEquals(period1, period3);
        assertEquals(period1.hashCode(), period3.hashCode());
    }
}