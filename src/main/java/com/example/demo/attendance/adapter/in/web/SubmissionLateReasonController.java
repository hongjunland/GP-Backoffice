package com.example.demo.attendance.adapter.in.web;

import com.example.demo.attendance.adapter.in.request.SubmissionLateReasonRequest;
import com.example.demo.attendance.application.port.in.command.SubmissionLateReasonCommand;
import com.example.demo.attendance.application.port.in.usecase.SubmissionLateReasonUseCase;
import com.example.demo.common.SuccessApiResponse;
import com.example.demo.common.annotaion.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/late-reason")
public class SubmissionLateReasonController {

    private final SubmissionLateReasonUseCase submissionLateReasonUseCase;

    @PostMapping
    public SuccessApiResponse<String> submissionLateReason(
            @RequestBody SubmissionLateReasonRequest submissionLateReasonRequest
    ) {
        SubmissionLateReasonCommand command = SubmissionLateReasonCommand.builder()
                .attendanceId(submissionLateReasonRequest.getAttendanceId())
                .lateReason(submissionLateReasonRequest.getLateReason())
                .build();

        submissionLateReasonUseCase.submissionLateReason(command);

        return SuccessApiResponse.of();
    }
}
