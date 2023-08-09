package com.example.demo.attendance.application.port.out;

import com.example.demo.attendance.domain.Attendance;
import com.example.demo.attendance.domain.AttendanceSearchCriteria;

import java.util.List;

public interface SearchAttendancePort {

    List<Attendance> searchAttendanceByCriteria(AttendanceSearchCriteria criteria);

}
