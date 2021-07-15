package com.example.speed.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vpn_config")
public class VpnConfigEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "vpn_config_id")
    private int vpnConfigId;
    @Column(name = "connection_name")
    private String connectionName;
    @Column(name = "address")
    private String address;
    @Column(name = "vpn_type")
    private String vpnType;
    @Column(name = "pre_shared_key")
    private String preSharedKey;
    @Column(name = "type_of_sign_in_info")
    private String typeOfSignInInfo;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

}
