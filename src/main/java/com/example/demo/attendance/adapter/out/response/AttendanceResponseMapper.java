package com.example.demo.attendance.adapter.out.response;

import com.example.demo.attendance.domain.Attendance;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
public class AttendanceResponseMapper {

    // 검색 결과 조회 리스폰
    public static List<SearchAttendanceResponse> mapToSearchAttendanceResponses(List<Attendance> attendances) {
        List<SearchAttendanceResponse> searchAttendanceResponses = new ArrayList<>();
        for (Attendance attendance : attendances) {
            SearchAttendanceResponse response = SearchAttendanceResponse.builder()
                    .department(attendance.getDepartment())
                    .name(attendance.getName())
                    .workDate(attendance.getWorkDate())
                    .dayType(attendance.getDayType())
                    .startTime(attendance.getStartTime())
                    .endTime(attendance.getEndTime())
                    .build();

            searchAttendanceResponses.add(response);
        }

        return searchAttendanceResponses;
    }

}
