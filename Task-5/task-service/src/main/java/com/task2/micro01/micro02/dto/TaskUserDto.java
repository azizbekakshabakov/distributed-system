package com.task2.micro01.micro02.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Deprecated
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskUserDto {
    private Long id;
    private String taskTitle;
    private String taskDescription;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate taskDeadlineDate;
    private int taskStatus;
    private User taskUser;
}