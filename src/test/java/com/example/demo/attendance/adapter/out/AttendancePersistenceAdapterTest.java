package com.example.demo.attendance.adapter.out;

import com.example.demo.attendance.adapter.out.persistence.AttendanceJpaRepo;
import com.example.demo.attendance.adapter.out.persistence.AttendancePersistenceAdapter;
import com.example.demo.attendance.adapter.out.persistence.AttendancePersistenceMapper;
import com.example.demo.attendance.domain.Attendance;
import com.example.demo.attendance.domain.constant.DayType;
import com.example.demo.attendance.domain.constant.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayName("AttendancePersistenceAdapter 테스트")
public class AttendancePersistenceAdapterTest {

    @Mock
    private AttendanceJpaRepo attendanceJpaRepo;

    @Mock
    private AttendancePersistenceMapper mapper;

    @InjectMocks
    private AttendancePersistenceAdapter adapter;

    private Attendance attendance;

    @BeforeEach
    void setUp() {
        attendance = Attendance.builder()
                .userId(1L)
                .department(Department.DED)
                .name("John Doe")
                .workDate(LocalDate.now())
                .dayType(DayType.WEEKDAY)
                .startTime(LocalTime.now())
                .endTime(LocalTime.now())
                .build();
    }

    @Test
    @DisplayName("Attendance 저장 및 업데이트 검증")
    void saveAttendanceTest() {
        // when
        adapter.saveAttendance(attendance);

        // then
        Mockito.verify(attendanceJpaRepo, times(1)).saveAttendance(mapper.mapToJpaEntity(attendance));
    }
}
