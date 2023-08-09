package com.example.demo.attendance.application.service;

import com.example.demo.attendance.application.port.in.query.SearchAttendanceQuery;
import com.example.demo.attendance.application.port.in.query.SearchAttendanceQueryParameters;
import com.example.demo.attendance.application.port.out.SearchAttendancePort;
import com.example.demo.attendance.domain.Attendance;
import com.example.demo.attendance.domain.AttendanceSearchCriteria;
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
    public List<Attendance> searchAttendance(SearchAttendanceQueryParameters parameters) {
        AttendanceSearchCriteria criteria = AttendanceSearchCriteria.builder()
                .startDate(parameters.getStartDate())
                .endDate(parameters.getEndDate())
                .department(parameters.getDepartment())
                .name(parameters.getName())
                .build();

        return searchAttendancePort.searchAttendanceByCriteria(criteria);
    }
}
