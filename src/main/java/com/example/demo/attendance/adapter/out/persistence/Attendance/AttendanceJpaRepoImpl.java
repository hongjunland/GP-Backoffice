package com.example.demo.attendance.adapter.out.persistence.Attendance;

import com.example.demo.attendance.domain.AttendanceSearchCriteria;
import com.example.demo.attendance.domain.FixedStartTime;
import com.example.demo.attendance.domain.constant.Department;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class AttendanceJpaRepoImpl implements AttendanceJpaRepoCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QAttendanceJpaEntity qAttendanceJpaEntity = QAttendanceJpaEntity.attendanceJpaEntity;

    /*
    AttendanceJpaRepo 대신 의존성을 주입 받아 사용하기 위함.
    JPAQueryFactory는 C(Create)를 지원하지 않아 사용함. -> 나머지 RUD(Read, Update, Delete)는 지원함.
    또한 JpaRepo를 생성자를 통해 사용해도 되지만, 의존성 순환 문제로 사용하지 않음.
     */
    @Autowired
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void saveAttendance(AttendanceJpaEntity attendanceJpaEntity) {
        long affected = updateAttendance(attendanceJpaEntity);
        if (affected == 0) {
            insertAttendance(attendanceJpaEntity);
        }
    }

    private long updateAttendance(AttendanceJpaEntity attendanceJpaEntity) {
        return jpaQueryFactory
                .update(qAttendanceJpaEntity)
                .set(qAttendanceJpaEntity.endTime, attendanceJpaEntity.getEndTime())
                .where(qAttendanceJpaEntity.workDate.eq(attendanceJpaEntity.getWorkDate())
                        .and(qAttendanceJpaEntity.name.eq(attendanceJpaEntity.getName())))
                .execute();
    }

    private void insertAttendance(AttendanceJpaEntity attendanceJpaEntity) {
        entityManager.persist(attendanceJpaEntity);
    }

    @Override
    public List<AttendanceJpaEntity> searchAttendanceByCriteria(AttendanceSearchCriteria attendanceSearchCriteria) {
        BooleanBuilder whereClause = new BooleanBuilder();
        whereClause.and(betweenDate(attendanceSearchCriteria.getStartDate(), attendanceSearchCriteria.getEndDate()));

        Optional.ofNullable(attendanceSearchCriteria.getName())
                .ifPresent(name -> whereClause.and(nameEquals(name)));

        Optional.ofNullable(attendanceSearchCriteria.getDepartment())
                .ifPresent(department -> whereClause.and(departmentEquals(department)));

        return jpaQueryFactory
                .select(qAttendanceJpaEntity)
                .from(qAttendanceJpaEntity)
                .where(whereClause)
                .fetch();
    }

    private BooleanExpression betweenDate(LocalDate startDate, LocalDate endDate) {
        return qAttendanceJpaEntity.workDate.between(startDate, endDate);
    }

    private BooleanExpression nameEquals(String name) {
        return qAttendanceJpaEntity.name.eq(name);
    }

    private BooleanExpression departmentEquals(Department department) {
        return qAttendanceJpaEntity.department.eq(department);
    }

    public void updateAttendanceStatus(FixedStartTime fixedStartTime) {
        // 지각 판단 조건
        BooleanExpression isLate = qAttendanceJpaEntity.startTime.after(fixedStartTime.getFixedStartTime());

        if (Objects.equals(fixedStartTime.getUpdateDate(), LocalDate.now())) {
            jpaQueryFactory
                    .update(qAttendanceJpaEntity)
                    .set(qAttendanceJpaEntity.attendanceStatus,
                            Expressions.cases()
                                    .when(isLate).then("지각")
                                    .otherwise("정시 출근"))
                    .set(qAttendanceJpaEntity.fixedStartTime, fixedStartTime.getFixedStartTime())
                    .where(qAttendanceJpaEntity.userId.eq(fixedStartTime.getUserId()))
                    .execute();
        }

        jpaQueryFactory
                .update(qAttendanceJpaEntity)
                .set(qAttendanceJpaEntity.attendanceStatus,
                        Expressions.cases()
                                .when(isLate).then("지각")
                                .otherwise("정시 출근"))
                .set(qAttendanceJpaEntity.fixedStartTime, fixedStartTime.getFixedStartTime())
                .where(
                        qAttendanceJpaEntity.userId.eq(fixedStartTime.getUserId())
                                .and(qAttendanceJpaEntity.workDate.goe(fixedStartTime.getUpdateDate()))
                )
                .execute();
    }

}
