package com.practo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String disease;
    private int age;
}