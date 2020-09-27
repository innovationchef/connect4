package com.innovationchef.controller;

import com.innovationchef.constant.ApiConstant;
import com.innovationchef.model.ApiResponse;
import com.innovationchef.repository.DatabaseAccessObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("v1")
public class HealthCheckController extends BaseController {

    private DatabaseAccessObject dao;

    public HealthCheckController(DatabaseAccessObject dao) {
        this.dao = dao;
    }

    @GetMapping(value = HEARTBEAT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> initializeGame() {
        this.dao.dbHealthCheck();
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message(ApiConstant.HEALTHY)
                .build());
    }
}
