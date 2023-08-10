package com.example.demo.attendance.application.port.out;

import com.example.demo.attendance.domain.FixedStartTime;

public interface LoadFixedStartTimePort {

    FixedStartTime loadFixedStartTime(Long userId);

}
