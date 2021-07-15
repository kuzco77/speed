package com.example.speed.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "default_config")
public class DefaultConfigEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "device_name")
    private String deviceName;
    @Column(name = "setting")
    private String setting;
    @Column(name = "status")
    private Byte status;
    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "command")
    private String command;

}
