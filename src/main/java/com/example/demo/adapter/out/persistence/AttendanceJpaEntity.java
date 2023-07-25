package com.example.demo.adapter.out.persistence;

import com.example.demo.domain.constant.DayType;
import com.example.demo.domain.constant.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "attendance")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceJpaEntity {

    /*
    JPAEntity는 데이터베이스와 직접적인 매핑을 담당하기에 데이터베이스 구조에 맞게 Long타입으로 구현했습니다.
    물론, Domain Entity에서 사용한 AttendanceId를 사용할 순 있지만
    그렇게 되면 @Embeddable, @EmbeddedId 사용해야하므로 복잡성이 증가해서 단순하게 작업하였습니다.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "department")
    private Department department;

    @Column(name = "name")
    private String name;

    @Column(name = "work_date")
    private LocalDate workDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_type")
    private DayType dayType;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

}
