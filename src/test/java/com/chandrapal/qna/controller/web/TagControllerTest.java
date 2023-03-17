package com.chandrapal.qna.controller.web;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.chandrapal.qna.controller.AbstractControllerTest;

import dev.chandrapal.qna.entities.Tag;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static dev.chandrapal.qna.controller.ControllerConstants.TAGS_PATH;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TagControllerTest extends AbstractControllerTest {

    private final String CONTENT_TYPE = ContentType.HTML.withCharset(StandardCharsets.UTF_8);

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        given(tagService.findAll(any(Pageable.class))).willAnswer(invocationOnMock -> {
            Pageable pageable = invocationOnMock.getArgument(0);
            List<Tag> tagsList = Arrays.asList(
                    Tag.builder().name("windows").questions(Collections.emptySet()).build(),
                    Tag.builder().name("open-source").questions(Collections.emptySet()).build());
            return new PageImpl<>(tagsList, pageable, tagsList.size());
        });
    }

    @Test
    @WithMockUser
    void findAll() throws Exception {
        mvc.perform(get(TAGS_PATH + "/" ))
            .andExpect(status().isOk())
            .andExpect(content().contentType(CONTENT_TYPE))
            .andExpect(content().string(containsString("windows")))
            .andExpect(content().string(containsString("open-source")));
    }
}
