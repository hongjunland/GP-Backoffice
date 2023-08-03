package com.example.demo.attendance.application.service;

import com.example.demo.attendance.application.port.in.query.SearchAttendanceQuery;
import com.example.demo.attendance.application.port.in.query.SearchAttendanceCriteria;
import com.example.demo.attendance.application.port.out.SearchAttendancePort;
import com.example.demo.attendance.domain.Attendance;
import com.example.demo.attendance.domain.AttendanceSearchPeriod;
import com.example.demo.common.annotaion.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class SearchAttendanceService implements SearchAttendanceQuery {

    private final SearchAttendancePort searchAttendancePort;

    @Override
    public List<Attendance> searchAttendance(SearchAttendanceCriteria criteria) {
        AttendanceSearchPeriod attendanceSearchPeriod = AttendanceSearchPeriod.builder()
                .startDate(criteria.getStartDate())
                .endDate(criteria.getEndDate())
                .build();

        return searchAttendancePort.searchAttendanceByPeriod(attendanceSearchPeriod);
    }
}
