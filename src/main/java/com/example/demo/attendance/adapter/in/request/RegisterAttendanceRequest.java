package com.example.demo.attendance.adapter.in.request;

import com.example.demo.attendance.domain.constant.DayType;
import com.example.demo.attendance.domain.constant.Department;
import com.example.demo.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalTime;

@Value
@EqualsAndHashCode(callSuper = false)
public class RegisterAttendanceRequest extends SelfValidating<RegisterUserRequest> {

    // DTO는 일방적으로 불변해야하므로 final을 붙여줬습니다.
    @NotEmpty(message = "유저ID 는 반드시 입력해야 합니다.")
    private final Long userId;

    @NotEmpty(message = "부서는 반드시 입력해야 합니다.")
    private final Department department;

    @NotEmpty(message = "이름은 반드시 입력해야 합니다.")
    private final String name;

    @Pattern(regexp = "^\\d{4}/\\d{2}/\\d{2}$", message = "근무일자는 'YYYY/MM/DD' 형식이어야 합니다.")
    private final LocalDate workDate;

    @NotEmpty(message = "근무일명칭은 반드시 입력해야 합니다.")
    private final DayType dayType;

    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "출근은 'HH:MM' 형식이어야 합니다.")
    private final LocalTime startTime;

    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "퇴근은 'HH:MM' 형식이어야 합니다.")
    private final LocalTime endTime;

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
