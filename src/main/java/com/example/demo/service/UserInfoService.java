package com.example.demo.service;

import java.util.List;

import com.example.demo.model.entities.UserInfo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface UserInfoService {
    public ResponseEntity<List<UserInfo>> getAll();

    public ResponseEntity<UserInfo> getById(Long id);

    public ResponseEntity<UserInfo> create(UserInfo info);

    public ResponseEntity<UserInfo> update(Long id, UserInfo info);

    public ResponseEntity<HttpStatus> delele(Long id);
}
