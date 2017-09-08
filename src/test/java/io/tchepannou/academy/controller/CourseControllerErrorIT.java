package io.tchepannou.academy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.tchepannou.academy.ControllerITSupport;
import io.tchepannou.academy.dto.course.CreateCourseRequest;
import io.tchepannou.academy.dto.course.UpdateCourseRequest;
import io.tchepannou.academy.dto.course.UpdateCourseStatusRequest;
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
public class CourseControllerErrorIT extends ControllerITSupport{
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setUp(){
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    //-- COURSE
    @Test
    public void shouldReturn400WhenCreateCourseWithInvalidLevel() throws Exception {
        // Given
        final CreateCourseRequest req = new CreateCourseRequest();
        req.setDescription("This is a sample course");
        req.setLanguage("en");
        req.setLevel("invalid-level");
        req.setSummary("This is the summary");
        req.setTitle("Test course");

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        mockMvc
                .perform(
                        post("/academy/v1/courses")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.errors.length()", is(1)))
                .andExpect(jsonPath("$.errors[0].code", is(BusinessError.BAD_COURSE_LEVEL.getCode())))
                .andExpect(jsonPath("$.errors[0].description", is(BusinessError.BAD_COURSE_LEVEL.getDescription())))
        ;
    }

    @Test
    public void shouldReturn404WhenUpdateCourseWithInvalidId() throws Exception {
        // Given
        final UpdateCourseRequest req = new UpdateCourseRequest();
        req.setDescription("This is a sample course");
        req.setLanguage("en");
        req.setLevel("expert");
        req.setSummary("This is the summary");
        req.setTitle("Test course");

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        expectBusinessError(0, BusinessError.COURSE_NOT_FOUND,
            mockMvc
                .perform(
                        post("/academy/v1/courses/9999999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.errors.length()", is(1)))
        );
    }

    @Test
    public void shouldReturn400WhenUpdateCourseWithInvalidLevel() throws Exception {
        // Given
        final UpdateCourseRequest req = new UpdateCourseRequest();
        req.setDescription("This is a sample course");
        req.setLanguage("en");
        req.setLevel("invalid-level");
        req.setSummary("This is the summary");
        req.setTitle("Test course");

        // When
        final BusinessError expected = BusinessError.BAD_COURSE_LEVEL;
        final String jsonRequest = mapper.writeValueAsString(req);
        mockMvc
                .perform(
                        post("/academy/v1/courses/100")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.errors.length()", is(1)))
                .andExpect(jsonPath("$.errors[0].code", is(expected.getCode())))
                .andExpect(jsonPath("$.errors[0].description", is(expected.getDescription())))
        ;
    }

    @Test
    public void shouldReturn404WhenUpdateCourseStatusWithInvlidId() throws Exception {
        // Given
        final UpdateCourseStatusRequest req = new UpdateCourseStatusRequest();
        req.setStatus("published");

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        mockMvc
                .perform(
                        post("/academy/v1/courses/9999999/status")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.errors.length()", is(1)))
                .andExpect(jsonPath("$.errors[0].code", is(BusinessError.COURSE_NOT_FOUND.getCode())))
                .andExpect(jsonPath("$.errors[0].description", is(BusinessError.COURSE_NOT_FOUND.getDescription())))
        ;
    }

    @Test
    public void shouldReturn404WhenFindCourseWithInvalidId() throws Exception {
        // When
        expectBusinessError(0, BusinessError.COURSE_NOT_FOUND,
            mockMvc
                .perform(
                        get("/academy/v1/courses/999999")
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.errors.length()", is(1)))
        );
    }

    @Test
    public void shouldReturn404WhenUpdateCourseStatusWithInvalidStatus() throws Exception {
        // Given
        final UpdateCourseStatusRequest req = new UpdateCourseStatusRequest();
        req.setStatus("invalid-status");

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        expectBusinessError(0, BusinessError.BAD_COURSE_STATUS,
                mockMvc
                        .perform(
                                post("/academy/v1/courses/200/status")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(jsonRequest)
                        )

                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isBadRequest())
                        .andExpect(jsonPath("$.transactionId", notNullValue()))
                        .andExpect(jsonPath("$.errors.length()", is(1)))
        );
    }



    //-- LESSON
    @Test
    public void shouldReturn404WhenFindLessonWithInvalidLessonId() throws Exception {
        // When
        expectBusinessError(0, BusinessError.LESSON_NOT_FOUND,
                mockMvc
                        .perform(
                                get("/academy/v1/courses/300/lessons/999999")
                                        .contentType(MediaType.APPLICATION_JSON)
                        )

                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isNotFound())
                        .andExpect(jsonPath("$.transactionId", notNullValue()))
                        .andExpect(jsonPath("$.errors.length()", is(1)))
        );
    }

    @Test
    public void shouldReturn404WhenFindLessonWithInvalidCourseId() throws Exception {
        // When
        expectBusinessError(0, BusinessError.LESSON_NOT_FOUND,
                mockMvc
                        .perform(
                                get("/academy/v1/courses/99999999/lessons/310")
                                        .contentType(MediaType.APPLICATION_JSON)
                        )

                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isNotFound())
                        .andExpect(jsonPath("$.transactionId", notNullValue()))
                        .andExpect(jsonPath("$.errors.length()", is(1)))
        );
    }

    @Test
    public void shouldReturn404WhenFindLessonWithInvalidCourseIdMismatch() throws Exception {
        // When
        expectBusinessError(0, BusinessError.LESSON_NOT_FOUND,
                mockMvc
                        .perform(
                                get("/academy/v1/courses/100/lessons/310")
                                        .contentType(MediaType.APPLICATION_JSON)
                        )

                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isNotFound())
                        .andExpect(jsonPath("$.transactionId", notNullValue()))
                        .andExpect(jsonPath("$.errors.length()", is(1)))
        );
    }

    @Test
    public void shouldReturn404WhenFindLessonListForInvalidCourse() throws Exception {
        // When
        expectBusinessError(0, BusinessError.COURSE_NOT_FOUND,
                mockMvc
                        .perform(
                                get("/academy/v1/courses/99999/lessons")
                                        .contentType(MediaType.APPLICATION_JSON)
                        )

                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isNotFound())
                        .andExpect(jsonPath("$.transactionId", notNullValue()))
                        .andExpect(jsonPath("$.errors.length()", is(1)))
        );
    }



    //-- SEGMENT
    @Test
    public void shouldReturn404WhenFindSegmentWithInvalidSegmentId() throws Exception {
        // When
        expectBusinessError(0, BusinessError.SEGMENT_NOT_FOUND,
                mockMvc
                        .perform(
                                get("/academy/v1/courses/300/segments/999999")
                                        .contentType(MediaType.APPLICATION_JSON)
                        )

                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isNotFound())
                        .andExpect(jsonPath("$.transactionId", notNullValue()))
                        .andExpect(jsonPath("$.errors.length()", is(1)))
        );
    }

    @Test
    public void shouldReturn404WhenFindSegmentWithForInvalidCourseId() throws Exception {
        // When
        expectBusinessError(0, BusinessError.SEGMENT_NOT_FOUND,
                mockMvc
                        .perform(
                                get("/academy/v1/courses/9999999/segments/3101")
                                        .contentType(MediaType.APPLICATION_JSON)
                        )

                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isNotFound())
                        .andExpect(jsonPath("$.transactionId", notNullValue()))
                        .andExpect(jsonPath("$.errors.length()", is(1)))
        );
    }

    @Test
    public void shouldReturn404WhenFindSegmentWithWithCourseIdMismatch() throws Exception {
        // When
        expectBusinessError(0, BusinessError.SEGMENT_NOT_FOUND,
                mockMvc
                        .perform(
                                get("/academy/v1/courses/100/segments/3101")
                                        .contentType(MediaType.APPLICATION_JSON)
                        )

                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isNotFound())
                        .andExpect(jsonPath("$.transactionId", notNullValue()))
                        .andExpect(jsonPath("$.errors.length()", is(1)))
        );
    }

    @Test
    public void shouldReturn404WhenFindSegmentListForInvalidCourse() throws Exception {
        // When
        expectBusinessError(0, BusinessError.LESSON_NOT_FOUND,
                mockMvc
                        .perform(
                                get("/academy/v1/courses/99999/segments")
                                        .param("lessonId", "310")
                                        .contentType(MediaType.APPLICATION_JSON)
                        )

                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isNotFound())
                        .andExpect(jsonPath("$.transactionId", notNullValue()))
                        .andExpect(jsonPath("$.errors.length()", is(1)))
        );
    }

    @Test
    public void shouldReturn404WhenFindSegmentListForInvalidLesson() throws Exception {
        // When
        expectBusinessError(0, BusinessError.LESSON_NOT_FOUND,
                mockMvc
                        .perform(
                                get("/academy/v1/courses/300/segments")
                                        .param("lessonId", "77777")
                                        .contentType(MediaType.APPLICATION_JSON)
                        )

                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isNotFound())
                        .andExpect(jsonPath("$.transactionId", notNullValue()))
                        .andExpect(jsonPath("$.errors.length()", is(1)))
        );
    }
}
