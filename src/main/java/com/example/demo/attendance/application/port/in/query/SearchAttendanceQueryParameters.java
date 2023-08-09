package com.example.demo.attendance.application.port.in.query;

import com.example.demo.attendance.domain.constant.Department;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
// 네이밍 컨벤션을 수정하였습니다. 명확하게 확인하기 위해
// QueryParameters(쿼리에 대한 파라미터)로 사용한다고 기입했습니다.
public class SearchAttendanceQueryParameters {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Department department;
    private final String name;

}
