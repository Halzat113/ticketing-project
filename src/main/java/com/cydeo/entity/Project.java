package com.cydeo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Project extends BaseEntity{
    private String name;
    private String code;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}
