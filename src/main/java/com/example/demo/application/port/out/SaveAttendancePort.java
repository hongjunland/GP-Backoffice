package com.example.demo.application.port.out;

import com.example.demo.domain.Attendance;

public interface SaveAttendancePort {

    void saveAttendance(Attendance attendance);

}
