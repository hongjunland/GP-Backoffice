package com.example.demo.attendance.application.port.in.usecase;

import com.example.demo.attendance.application.port.in.command.CheckLatenessCommand;

public interface CheckLatenessUseCase {

    void CheckLateness(CheckLatenessCommand command);

}
