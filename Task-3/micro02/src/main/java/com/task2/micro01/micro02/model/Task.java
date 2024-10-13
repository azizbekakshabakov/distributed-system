package com.task2.micro01.micro02.model;

import com.task2.micro01.micro02.dto.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "t_tasks")
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Column(name = "deadline_date")
    private LocalDate deadlineDate;
    private int status;

    @Column(name = "user_id")
    private Long userId;
}
