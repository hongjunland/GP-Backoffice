package com.example.demo.attendance.application.port.out;

import com.example.demo.attendance.domain.Attendance;
import com.example.demo.attendance.domain.AttendanceSearchPeriod;

import java.util.List;

public interface SearchAttendancePort {

    List<Attendance> searchAttendanceByPeriod(AttendanceSearchPeriod attendanceSearchPeriod);

}
