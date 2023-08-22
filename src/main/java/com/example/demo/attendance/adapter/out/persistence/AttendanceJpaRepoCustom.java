package com.example.demo.attendance.adapter.out.persistence;

import com.example.demo.attendance.domain.AttendanceSearchPeriod;

import java.util.List;

public interface AttendanceJpaRepoCustom {

    void saveAttendance(AttendanceJpaEntity attendanceJpaEntity);

    List<AttendanceJpaEntity> searchAttendanceByPeriod(AttendanceSearchPeriod attendanceSearchPeriod);

}
