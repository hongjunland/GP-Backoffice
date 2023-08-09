package com.example.demo.attendance.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AttendanceStatus {

    ON_TIME("정시 출근"),
    LATE("지각"),
    HALF_DAY("반차"),
    FULL_DAY("연차");

    private final String description;

}
