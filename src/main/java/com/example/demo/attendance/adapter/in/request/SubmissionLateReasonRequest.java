package com.example.demo.attendance.adapter.in.request;

import com.example.demo.attendance.domain.Attendance;
import com.example.demo.common.SelfValidating;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class SubmissionLateReasonRequest extends SelfValidating<SubmissionLateReasonRequest> {

    @NotNull(message = "attendanceId는 필수입니다.")
    private Attendance.AttendanceId attendanceId;

    @Size(min = 1, max = 100)
    private String lateReason;

    public SubmissionLateReasonRequest(
            Attendance.AttendanceId attendanceId,
            String lateReason
    ) {
        this.attendanceId = attendanceId;
        this.lateReason = lateReason;
    }

}
