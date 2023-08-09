package com.example.demo.attendance.adapter.out.persistence.FixedStartTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "fixed_start_time")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FixedStartTimeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "fixed_start_time")
    private LocalTime fixedStartTime;

}
