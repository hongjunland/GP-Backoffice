package com.example.demo.attendance.adapter.in.response;

import com.example.demo.attendance.domain.Attendance;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AttendanceResponseMapper {

    // 검색 결과 조회 리스폰
    public List<SearchAttendanceResponse> mapToSearchAttendanceResponses(List<Attendance> attendances) {
        List<SearchAttendanceResponse> searchAttendanceResponses = new ArrayList<>();
        for (Attendance attendance : attendances) {
            SearchAttendanceResponse response = SearchAttendanceResponse.builder()
                    .attendanceId(attendance.getAttendanceId())
                    .department(attendance.getDepartment())
                    .name(attendance.getName())
                    .workDate(attendance.getWorkDate())
                    .dayType(attendance.getDayType())
                    .startTime(attendance.getStartTime())
                    .endTime(attendance.getEndTime())
                    .attendanceStatus(attendance.getAttendanceStatus())
                    .fixedStartTime(attendance.getFixedStartTime())
                    .lateReason(attendance.getLateReason())
                    .build();

            searchAttendanceResponses.add(response);
        }

        return searchAttendanceResponses;
    }

}
