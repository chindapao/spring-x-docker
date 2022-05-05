package com.example.demo.service.impl;

import com.example.demo.model.entities.UserInfo;
import com.example.demo.repository.UserInfoRepo;
import com.example.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    public ResponseEntity<List<UserInfo>> getAll() {
        try {

            List<UserInfo> items = new ArrayList<UserInfo>(repository.findAll());

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
                /* UserInfo info2 = new UserInfo(); */
                // TODO: Logic
                info2.setFirstName(info.getFirstName());
                info2.setLastName(info.getLastName());
                info2.setUserName(info.getUserName());
                info2.setGender(info.getGender());
                info2.setGmail(info.getGmail());
                info2.setIdentifyId(info.getIdentifyId());
                info2.setPassword(info.getPassword());
                info2.setPostalCode(info.getPostalCode());
                info2.setAddress(info.getAddress());
                info2.setAge(info.getAge());
                info2.setUptDate(new Date());
                return new ResponseEntity<>(repository.save(info2), HttpStatus.OK);
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
