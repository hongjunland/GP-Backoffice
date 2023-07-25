package com.example.demo.attendance.application.port.out;

import com.example.demo.attendance.domain.Attendance;

public interface SaveAttendancePort {

    void saveAttendance(Attendance attendance);

}
