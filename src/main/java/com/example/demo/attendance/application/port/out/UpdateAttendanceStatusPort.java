package com.example.demo.attendance.application.port.out;

import com.example.demo.attendance.domain.FixedStartTime;

public interface UpdateAttendanceStatusPort {

    void updateAttendanceStatus(FixedStartTime fixedStartTime);

}
