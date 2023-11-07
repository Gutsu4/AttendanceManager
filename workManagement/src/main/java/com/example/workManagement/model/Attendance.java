package com.example.workManagement.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class Attendance {
    private int id;
    private int userId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int breakDuration;
    private LocalTime totalWorkTime;
}


