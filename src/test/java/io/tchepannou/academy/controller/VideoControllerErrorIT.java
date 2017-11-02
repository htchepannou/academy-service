package io.tchepannou.academy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.tchepannou.academy.ControllerITSupport;
import io.tchepannou.academy.client.video.CreateVideoRequest;
import io.tchepannou.academy.exception.BusinessError;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql({"classpath:/sql/clean.sql", "classpath:/sql/CourseController.sql"})
public class VideoControllerErrorIT extends ControllerITSupport {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper mapper;


    @Before
    public void setUp(){
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturn404WhenFindVideoWithInvalidId() throws Exception {
        // When
        expectBusinessError(0, BusinessError.VIDEO_NOT_FOUND,
            mockMvc
                .perform(
                        get("/academy/v1/videos/999999")
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.errors.length()", is(1)))
        );
    }

    @Test
    public void shouldReturn400WhenCreateVideoWithInvalidType() throws Exception {
        // Given
        final CreateVideoRequest req = new CreateVideoRequest();
        req.setUrl("http://www.foo.com");

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        expectBusinessError(0, BusinessError.BAD_VIDEO_URL,
                mockMvc
                        .perform(
                                post("/academy/v1/videos")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(jsonRequest)
                        )

                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.transactionId", notNullValue()))
                        .andExpect(jsonPath("$.errors.length()", is(1)))
        );
    }

    @Test
    public void shouldReturn400WhenCreateVideoWithNullUrl() throws Exception {
        // Given
        final CreateVideoRequest req = new CreateVideoRequest();
        req.setUrl(null);

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        mockMvc
                .perform(
                        post("/academy/v1/videos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.errors.length()", is(1)))
                .andExpect(jsonPath("$.errors[0].code", is(BusinessError.BAD_REQUEST.getCode())))
                .andExpect(jsonPath("$.errors[0].description", is("url=null - may not be null")))
        ;
    }
}
