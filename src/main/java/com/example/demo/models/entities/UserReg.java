package com.example.demo.models.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user_reg")
@Data
public class UserReg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String gmail;
    private String password;
}
