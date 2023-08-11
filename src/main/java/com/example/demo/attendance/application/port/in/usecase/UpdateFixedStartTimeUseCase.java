package com.example.demo.attendance.application.port.in.usecase;

import com.example.demo.attendance.application.port.in.command.UpdateFixedStartTimeCommand;

public interface UpdateFixedStartTimeUseCase {

    void UpdateFixedStartTime(UpdateFixedStartTimeCommand command);

}
