package com.example.demo.attendance.domain;

import com.example.demo.attendance.domain.constant.Department;
import lombok.*;

import java.time.LocalDate;

/*
Attendance 도메인과 관련된 비즈니스 로직을 처리하기 위해 도메인 패키지에 넣었습니다.
service -> port(계층 변환을 위한 객체(AttendanceSearchCriteria)) -> adapter
원래 AttendanceSearchPeriod로 사용하였는데 검색 기준에 이름이 추가되어 AttendanceSearchCriteria로 수정하였습니다.
*/
@Getter
@Builder
@EqualsAndHashCode // VO는 식별자가 없기에
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AttendanceSearchCriteria {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String name;
    private final Department department;

}
