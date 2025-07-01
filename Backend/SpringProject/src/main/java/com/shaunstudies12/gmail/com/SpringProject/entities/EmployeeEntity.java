package com.shaunstudies12.gmail.com.SpringProject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity   //required
@Table(name = "employees")   //table name in sql
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {
    @Id  //declares id as the primary key in Data base
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto id generation for employees
    private Long id;
    private String name;
    private LocalDate dateOfJoining;
    private Boolean isActive;
}
