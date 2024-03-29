package com.chandrapal.qna.controller.web;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.chandrapal.qna.controller.AbstractControllerTest;

import dev.chandrapal.qna.entities.Account;
import dev.chandrapal.qna.entities.Answer;
import dev.chandrapal.qna.entities.Question;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static dev.chandrapal.qna.controller.ControllerConstants.QUESTIONS_PATH;
import static org.hamcrest.CoreMatchers.containsString;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AnswerControllerTest extends AbstractControllerTest {

    private static final String CONTENT_TYPE = ContentType.HTML.withCharset(StandardCharsets.UTF_8);

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        auditingHandler = getAuditingHandler();
        Account account = createAccountWithId(EXISTED_USER_ID, DEFAULT_USER_NAME, DEFAULT_USER_EMAIL);
        Question question = createQuestionWithId(EXISTED_USER_ID, account);
        Answer answer = createAnswer(account, question);
        question.addAnswer(answer);
        auditingHandler.markCreated(question);
        auditingHandler.markCreated(answer);
        when(questionService.findById(EXISTED_USER_ID)).thenReturn(Optional.of(question));
    }

    @Test
    @WithMockUser
    void createAnswer() throws Exception {
        mvc.perform(get(QUESTIONS_PATH + "/" + EXISTED_QUESTION_ID))
            .andExpect(status().isOk())
            .andExpect(content().contentType(CONTENT_TYPE))
            .andExpect(content().string(containsString("test answer")));
    }
}