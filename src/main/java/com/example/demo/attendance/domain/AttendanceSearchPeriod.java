package com.example.demo.attendance.domain;

import lombok.*;

import java.time.LocalDate;

/*
Attendance 도메인과 관련된 비즈니스 로직을 처리하기 위해 도메인 패키지에 넣었습니다.
service -> port(계층 변환을 위한 객체(AttendanceSearchPeriod)) -> adapter
*/
@Getter
@Builder
@EqualsAndHashCode // VO는 식별자가 없기에
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AttendanceSearchPeriod {

    private final LocalDate startDate;
    private final LocalDate endDate;

}
