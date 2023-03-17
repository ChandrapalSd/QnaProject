package com.chandrapal.qna.controller;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mapping.context.PersistentEntities;

import dev.chandrapal.qna.entities.Account;
import dev.chandrapal.qna.entities.Answer;
import dev.chandrapal.qna.entities.Image;
import dev.chandrapal.qna.entities.Question;
import dev.chandrapal.qna.service.AnswerService;
import dev.chandrapal.qna.service.TagService;
import dev.chandrapal.qna.service.impl.account.AccountServiceImpl;
import dev.chandrapal.qna.service.impl.question.QuestionServiceImpl;

public abstract class AbstractControllerTest {

    protected static long EXISTED_USER_ID = 1L;

    protected static long EXISTED_QUESTION_ID = 1L;

    protected final String DEFAULT_USER_EMAIL = "test@gmail.com";

    protected final String DEFAULT_USER_NAME = "Test user";

    protected final String DEFAULT_USER_PASSWORD = "password";

    protected AuditingHandler auditingHandler;

    protected AuditorAware auditorAware;

    @MockBean
    protected AccountServiceImpl accountService;

    @MockBean
    protected QuestionServiceImpl questionService;

    @MockBean
    protected TagService tagService;

    @MockBean
    protected AnswerService answerService;

    protected AuditingHandler getAuditingHandler() {
        return new AuditingHandler(PersistentEntities.of());
    }

    protected Account createAccount(String name, String email) {
        return Account.builder()
                .name(name)
                .email(email)
                .password(DEFAULT_USER_PASSWORD)
                .avatar(new Image(""))
                .questions(Collections.emptyList())
                .answers(Collections.emptyList())
                .roles(Collections.emptySet())
                .build();
    }

    protected Account createAccountWithId(Long id, String name, String email) {
        Account account = createAccount(name, email);
        account.setId(id);
        return account;
    }

    protected Answer createAnswer(Account account, Question question) {
        return Answer.builder()
                .author(account)
                .content("test answer")
                .question(question)
                .negativeVotes(Collections.emptySet())
                .positiveVotes(Collections.emptySet())
                .build();

    }

    protected Question createQuestion(Account account) {
        return Question.builder()
                .title("test question")
                .author(account)
                .body("text for test question")
                .answers(new ArrayList<Answer>())
                .negativeVotes(Collections.emptySet())
                .positiveVotes(Collections.emptySet())
                .tags(Collections.emptySet())
                .build();
    }

    protected Question createQuestionWithId(Long id, Account account) {
        Question question = createQuestion(account);
        question.setId(id);

        return question;
    }

    protected void deleteAccount(final Long id) {
        accountService.deleteById(id);
    }

    protected void deleteQuestion(final Long id) {
        questionService.deleteById(id);
    }

}
