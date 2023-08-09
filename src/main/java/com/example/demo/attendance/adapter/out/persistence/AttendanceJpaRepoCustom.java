package com.example.demo.attendance.adapter.out.persistence;

import com.example.demo.attendance.domain.AttendanceSearchCriteria;

import java.util.List;

public interface AttendanceJpaRepoCustom {

    void saveAttendance(AttendanceJpaEntity attendanceJpaEntity);

    List<AttendanceJpaEntity> searchAttendanceByCriteria(AttendanceSearchCriteria attendanceSearchCriteria);

}
