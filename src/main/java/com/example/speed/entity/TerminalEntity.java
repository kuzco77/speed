package com.example.speed.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "terminal")
public class TerminalEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "device_name")
    private String deviceName;
    @Column(name = "command")
    private String command;
    @Column(name = "created_date")
    private Timestamp createdDate;
    @Column(name = "status")
    private Boolean status;
}
