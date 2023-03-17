package com.chandrapal.qna;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.chandrapal.qna.controller.web.AccountController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QnaprojectApplicationTests {

	 @Autowired
	 private AccountController controller;
	 
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
