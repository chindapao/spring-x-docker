package com.example.demo.service.impl;

import com.example.demo.model.entities.UserInfo;
import com.example.demo.repository.UserInfoRepo;
import com.example.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private final UserInfoRepo repository;

    public UserInfoServiceImpl(UserInfoRepo repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<UserInfo> getAll() {
        try {
            List<UserInfo> userInfos = repository.findAll();
            for (UserInfo userInfo : userInfos) {
                return new ResponseEntity<>(userInfo, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new UserInfo(), HttpStatus.NO_CONTENT);
        }
        return null;
    }

    @Override
    public ResponseEntity<UserInfo> getById(Long id) {
        Optional<UserInfo> info = repository.findById(id);
        return info.map(userInfo -> new ResponseEntity<>(userInfo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new UserInfo(), HttpStatus.NO_CONTENT));
    }

    @Override
    public ResponseEntity<UserInfo> create(UserInfo info) {
        try {
            UserInfo info2 = repository.save(info);
            return new ResponseEntity<>(info2, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public ResponseEntity<UserInfo> update(Long id, UserInfo info) {
        try {
            Optional<UserInfo> optional = repository.findById(id);
            if (optional.isPresent()) {
                UserInfo info2 = optional.get();
                // TODO: Logic
                return new ResponseEntity<>(info2, HttpStatus.OK);
            } else {
                HttpHeaders body = new HttpHeaders();
                body.add("Status", "No update!!");
                return new ResponseEntity<>(body, HttpStatus.EXPECTATION_FAILED);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(new UserInfo(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> delele(Long id) {
        // TODO Auto-generated method stub
        try {
            Optional<UserInfo> condition = repository.findById(id);
            if (condition.isPresent()) {
                repository.deleteById(id);
                HttpHeaders body = new HttpHeaders();
                body.add("Status", "Delete successfully!!");
                return new ResponseEntity<HttpStatus>(body, HttpStatus.NO_CONTENT);
            } else
                return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // TODO: handle exception
            HttpHeaders body = new HttpHeaders();
            body.add("Status", "Data not found!!!");
            return new ResponseEntity<HttpStatus>(body, HttpStatus.NOT_FOUND);
        }
    }
}
