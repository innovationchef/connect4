package com.innovationchef.controller;

import com.innovationchef.constant.ApiConstant;
import com.innovationchef.model.ApiResponse;
import com.innovationchef.model.GameStartRes;
import com.innovationchef.model.GameMoveReq;
import com.innovationchef.service.GameExecution;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("v1")
public class GameControllerV1 extends BaseController {

    private GameExecution execution;

    public GameControllerV1(GameExecution execution) {
        this.execution = execution;
    }

    @PostMapping(value = START_GAME, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameStartRes> initializeGame() {
        String token = this.execution.createSession();
        GameStartRes responseObj = new GameStartRes(true, ApiConstant.READY);
        responseObj.setToken(token);
        return ResponseEntity.ok(responseObj);
    }

    @PostMapping(value = MAKE_MOVE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> makeMove(@Valid @RequestBody GameMoveReq req) {
        String message = this.execution.makeMove(req);
        ApiResponse resObj = ApiResponse.builder()
                .success(true)
                .message(message)
                .build();
        return ResponseEntity.ok(resObj);
    }
}
