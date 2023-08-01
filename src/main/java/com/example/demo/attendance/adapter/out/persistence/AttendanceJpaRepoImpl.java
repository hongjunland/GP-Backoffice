package com.example.demo.attendance.adapter.out.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

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
    public void saveAndUpdateAttendance(AttendanceJpaEntity attendanceJpaEntity) {
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
}
