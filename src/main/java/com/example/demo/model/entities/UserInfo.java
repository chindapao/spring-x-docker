package com.example.demo.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_user_info")
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -5002308008901628187L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String gender;
    private String age;
    private String address;
    private String postalCode;
    private String identifyId;
    private String gmail;
    private String password;

    private String status;
    private String title;
    private String position;
    private String userPf;

    private Date regDate;
    private Date uptDate;

    @PrePersist
    void createdAt() {
        this.regDate = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.uptDate = new Date();
    }

}
