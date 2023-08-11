package com.example.demo.attendance.application.port.in.usecase;

import com.example.demo.attendance.application.port.in.command.SubmissionLateReasonCommand;

public interface SubmissionLateReasonUseCase {

    void submissionLateReason(SubmissionLateReasonCommand command);

}
