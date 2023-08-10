package com.example.demo.attendance.adapter.in.request;

import com.example.demo.attendance.domain.constant.DayType;
import com.example.demo.attendance.domain.constant.Department;
import com.example.demo.common.SelfValidating;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class RegisterAttendanceRequest extends SelfValidating<RegisterAttendanceRequest> {

    // DTO는 일방적으로 불변해야하므로 final을 붙여줬습니다.
    @NotNull(message = "유저ID는 반드시 입력해야 합니다.")
    private Long userId;

    private Department department;

    @NotEmpty(message = "이름은 반드시 입력해야 합니다.")
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate workDate;

    private DayType dayType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
    private LocalTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
    private LocalTime endTime;

    public RegisterAttendanceRequest(
            Long userId,
            Department department,
            String name,
            LocalDate workDate,
            DayType dayType,
            LocalTime startTime,
            LocalTime endTime
    ) {
        this.userId = userId;
        this.department = department;
        this.name = name;
        this.workDate = workDate;
        this.dayType = dayType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.validateSelf();
    }

}
