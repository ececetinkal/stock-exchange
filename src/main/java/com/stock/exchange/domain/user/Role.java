package com.stock.exchange.domain.user;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="app_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="role_name")
    private String roleName;

    @Column(name="description")
    private String description;
}
