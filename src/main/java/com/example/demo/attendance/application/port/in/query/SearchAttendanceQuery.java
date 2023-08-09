package com.example.demo.attendance.application.port.in.query;

import com.example.demo.attendance.domain.Attendance;

import java.util.List;

public interface SearchAttendanceQuery {

    List<Attendance> searchAttendance(SearchAttendanceQueryParameters parameters);

}
