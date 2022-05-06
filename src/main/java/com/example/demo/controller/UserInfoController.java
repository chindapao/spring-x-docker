package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.entities.UserInfo;
import com.example.demo.service.impl.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "/api/v2/")
public class UserInfoController {
    @Autowired
    private final UserInfoServiceImpl service;

    public UserInfoController(UserInfoServiceImpl service) {
        this.service = service;
    }

    @GetMapping(value = "user")
    public ResponseEntity<List<UserInfo>> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "id/{id}")
    public ResponseEntity<UserInfo> getById(@RequestParam Long id) {
        return service.getById(id);
    }

    @PostMapping(value = "post")
    public ResponseEntity<UserInfo> create(@RequestBody UserInfo info) {
        return service.create(info);
    }

    @PutMapping(value = "id/{id}")
    public ResponseEntity<UserInfo> update(@RequestParam Long id, @RequestBody UserInfo info) {
        return service.update(id, info);
    }

    @DeleteMapping(value = "id")
    public ResponseEntity<HttpStatus> delete(@RequestParam Long id) {
        return service.delete(id);
    }
}
