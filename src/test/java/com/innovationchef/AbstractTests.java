//package com.innovationchef;
//
//import com.innovationchef.controller.GameControllerV1;
//import com.innovationchef.model.TestMoveSequence;
//import com.innovationchef.service.GameExecution;
//import com.innovationchef.utiltiy.TestFileReader;
//import org.junit.jupiter.api.BeforeAll;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.LinkedList;
//import java.util.List;
//
//abstract class AbstractTests {
//
//    static final String START_GAME = "/v1/connect4/start";
//    static final String MAKE_MOVE = "/v1/connect4/move";
//    static final String VIEW_GAME = "/v1/connect4/view";
//    static final String HEARTBEAT = "/v1/connect4/heartbeat";
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    GameExecution execution;
//
//    @MockBean
//    GameExecution gameExecution;
//
//    static List<TestMoveSequence> testMoveSequences = new LinkedList<>();
//
//    @BeforeAll
//    public static void loadTestCases() {
//        testMoveSequences.addAll(TestFileReader.readMoveSeqTestFile("test-cases.txt"));
//    }
//
//}
