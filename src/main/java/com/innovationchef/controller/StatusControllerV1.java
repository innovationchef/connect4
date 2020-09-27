package com.innovationchef.controller;

import com.innovationchef.constant.ApiConstant;
import com.innovationchef.model.GameStatusReq;
import com.innovationchef.model.GameStatusRes;
import com.innovationchef.model.Move;
import com.innovationchef.service.BizExecution;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("v1")
public class StatusControllerV1 extends BaseController {

    private BizExecution execution;

    public StatusControllerV1(BizExecution execution) {
        this.execution = execution;
    }

    @PostMapping(value = VIEW_GAME, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameStatusRes> visualizeGame(@Valid @RequestBody GameStatusReq statusReq) {
        List<Move> timeSeries = this.execution.fetchGameStatus(statusReq);
        GameStatusRes responseObj = new GameStatusRes(true, ApiConstant.VIEW);
        responseObj.setSessionId(statusReq.getSessionId());
        responseObj.getMoves().addAll(timeSeries);
        return ResponseEntity.ok(responseObj);
    }
}
