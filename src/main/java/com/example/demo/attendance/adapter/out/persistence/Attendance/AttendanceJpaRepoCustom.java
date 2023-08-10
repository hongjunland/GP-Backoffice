package com.example.demo.attendance.adapter.out.persistence.Attendance;

import com.example.demo.attendance.domain.AttendanceSearchCriteria;
import com.example.demo.attendance.domain.FixedStartTime;

import java.util.List;

public interface AttendanceJpaRepoCustom {

    // 데이터가 없다면 insert, 데이터가 있다면 update(퇴근시간을 위함).
    void saveAttendance(AttendanceJpaEntity attendanceJpaEntity);

    // 필터(날짜, 부서, 이름)에 대한 리스트 반환.
    List<AttendanceJpaEntity> searchAttendanceByCriteria(AttendanceSearchCriteria attendanceSearchCriteria);

    // 고정 출근 시간 입력시 AttendanceStatus 변경.
    void updateAttendanceStatus(FixedStartTime fixedStartTime);
}
