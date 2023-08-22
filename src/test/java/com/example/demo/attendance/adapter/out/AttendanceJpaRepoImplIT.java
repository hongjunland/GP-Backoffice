package com.example.demo.attendance.adapter.out;

import com.example.demo.attendance.adapter.out.persistence.AttendanceJpaEntity;
import com.example.demo.attendance.adapter.out.persistence.AttendanceJpaRepo;
import com.example.demo.attendance.adapter.out.persistence.AttendanceJpaRepoImpl;
import com.example.demo.attendance.domain.AttendanceSearchPeriod;
import com.example.demo.attendance.domain.constant.DayType;
import com.example.demo.attendance.domain.constant.Department;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("AttendanceJpaRepoImpl 통합 테스트")
// 클래스 명 끝에 IT로 쓴건
public class AttendanceJpaRepoImplIT {

    @Autowired
    private AttendanceJpaRepoImpl attendanceJpaRepoImpl;

    @Autowired
    private AttendanceJpaRepo attendanceJpaRepo;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    @Transactional
    @DisplayName("출결 JPA 저장소 구현 테스트")
    void saveAttendanceTest() {
        // given
        AttendanceJpaEntity attendanceJpaEntity = AttendanceJpaEntity.builder()
                .userId(1L)
                .department(Department.DED)
                .name("Test User")
                .workDate(LocalDate.now())
                .dayType(DayType.WEEKDAY)
                .startTime(LocalTime.now())
                .endTime(LocalTime.now().plusHours(8))
                .build();

        // when
        attendanceJpaRepoImpl.saveAttendance(attendanceJpaEntity);

        // then
        assertNotNull(attendanceJpaEntity.getId());
    }

    @Test
    @Transactional
    @DisplayName("기간별 출결 정보 검색 테스트")
    void searchAttendanceByPeriodTest() {
        // given
        AttendanceJpaEntity attendanceJpaEntity = AttendanceJpaEntity.builder()
                .userId(1L)
                .department(Department.DED)
                .name("Test User")
                .workDate(LocalDate.now())
                .dayType(DayType.WEEKDAY)
                .startTime(LocalTime.now())
                .endTime(LocalTime.now().plusHours(8))
                .build();

        attendanceJpaRepo.save(attendanceJpaEntity);

        AttendanceSearchPeriod searchPeriod = AttendanceSearchPeriod.builder()
                .startDate(LocalDate.now().minusDays(5))
                .endDate(LocalDate.now().plusDays(5))
                .build();

        // when
        List<AttendanceJpaEntity> result = attendanceJpaRepoImpl.searchAttendanceByPeriod(searchPeriod);

        // then
        assertEquals(1, result.size());
    }

    @Test
    @Transactional
    @DisplayName("존재하지 않는 엔티티로 출결 정보 저장 테스트")
    void saveAttendanceTest_WithNonExistingEntity() {
        // given
        AttendanceJpaEntity attendanceJpaEntity = AttendanceJpaEntity.builder()
                .userId(9999L) // userId가 들어올 경우를 대비함.
                .department(Department.DED)
                .name("Test User")
                .workDate(LocalDate.now())
                .dayType(DayType.WEEKDAY)
                .startTime(LocalTime.now())
                .endTime(LocalTime.now().plusHours(8))
                .build();

        // when
        attendanceJpaRepoImpl.saveAttendance(attendanceJpaEntity);

        // then
        assertNull(attendanceJpaEntity.getId());
    }

    @Test
    @Transactional
    @DisplayName("결과 없는 기간의 출결 정보 검색 테스트")
    void searchAttendanceByPeriodTest_NoResult() {
        // request
        AttendanceSearchPeriod searchPeriod = AttendanceSearchPeriod.builder()
                .startDate(LocalDate.now().plusYears(10))
                .endDate(LocalDate.now().plusYears(10).plusDays(5))
                .build();

        // when
        List<AttendanceJpaEntity> result = attendanceJpaRepoImpl.searchAttendanceByPeriod(searchPeriod);

        // then
        assertTrue(result.isEmpty());
    }
}
