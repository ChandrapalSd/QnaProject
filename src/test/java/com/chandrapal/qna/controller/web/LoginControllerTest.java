package com.chandrapal.qna.controller.web;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static dev.chandrapal.qna.controller.ControllerConstants.LOGIN_PATH;
import static dev.chandrapal.qna.controller.ControllerConstants.TEST_HOST;
import static io.restassured.RestAssured.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginControllerTest {

	@Value("${local.server.port}")
    private int port;

    @Test
    public void loginPageTest() {
        given().when()
                .get(TEST_HOST + ":" + port + LOGIN_PATH)
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.HTML);
    }

}