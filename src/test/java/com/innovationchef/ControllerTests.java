package com.innovationchef;

import com.innovationchef.constant.ApiConstant;
import com.innovationchef.controller.GameControllerV1;
import com.innovationchef.controller.HealthCheckController;
import com.innovationchef.model.GameStartRes;
import com.innovationchef.repository.DatabaseAccessObject;
import com.innovationchef.service.GameExecution;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({GameControllerV1.class, HealthCheckController.class})
public class ControllerTests {

    static final String START_GAME = "/v1/connect4/start";
    static final String MAKE_MOVE = "/v1/connect4/move";
    static final String VIEW_GAME = "/v1/connect4/view";
    static final String HEARTBEAT = "/v1/connect4/heartbeat";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GameExecution gameExecution;

    @MockBean
    DatabaseAccessObject dao;

    @Test
    public void startGameTest() throws Exception {
        GameStartRes gameStartRes = new GameStartRes(true, ApiConstant.READY);
        when(gameExecution.createSession()).thenReturn(String.valueOf(gameStartRes));
        mockMvc.perform(post(START_GAME)
                .header("X-C4-TrackingId", "random-value")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("READY")));
    }

    @Test
    public void heartbeatTest() throws Exception {
        doNothing().when(dao).ping();
        mockMvc.perform(get(HEARTBEAT)
                .header("X-C4-TrackingId", "random-value")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("HEALTHY")));
    }

    @Test
    public void headerMissingExceptionTest() throws Exception {
        doNothing().when(dao).ping();
        mockMvc.perform(get(HEARTBEAT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message", is("Mandatory fields are not passed")));
    }
}
