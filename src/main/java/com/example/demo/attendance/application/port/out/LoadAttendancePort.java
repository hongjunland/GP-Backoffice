package com.example.demo.attendance.application.port.out;

import com.example.demo.attendance.domain.Attendance;

public interface LoadAttendancePort {

    Attendance loadAttendance(Attendance.AttendanceId attendanceId);

}
