package com.example.demo.attendance.application.port.out;

import com.example.demo.attendance.domain.Attendance;

import java.time.LocalDate;

public interface LoadAttendancePort {

    Attendance loadAttendanceByAttendanceId(Attendance.AttendanceId attendanceId);

    Attendance loadAttendanceByUserIdAndWorkDate(Long UserId, LocalDate workDate);

}
