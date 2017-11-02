package io.tchepannou.academy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.tchepannou.academy.dao.StudentDao;
import io.tchepannou.academy.dao.StudentSegmentDao;
import io.tchepannou.academy.domain.Student;
import io.tchepannou.academy.domain.StudentSegment;
import io.tchepannou.academy.client.course.StudentRequest;
import io.tchepannou.academy.client.course.StudentResponse;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql({"classpath:/sql/clean.sql", "classpath:/sql/StudentController.sql"})
public class StudentControllerIT {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private StudentDao courseAttendanceDao;

    @Autowired
    private StudentSegmentDao segmentAttendanceDao;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setUp(){
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void done() throws Exception {
        // Given
        final StudentRequest request = new StudentRequest();
        request.setRoleId(1);
        request.setSegmentId(3011);

        // When
        final String jsonRequest = mapper.writeValueAsString(request);
        final String jsonResponse = mockMvc
                .perform(
                        post("/academy/v1/courses/300/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.student.roleId", is(1)))
                .andExpect(jsonPath("$.student.courseId", is(300)))
                .andReturn()
                .getResponse()
                .getContentAsString()
                ;

        // Then
        final StudentResponse resp = mapper.readValue(jsonResponse, StudentResponse.class);
        final Student courseAttendance = courseAttendanceDao.findOne(resp.getStudent().getId());
        assertThat(courseAttendance.getRoleId()).isEqualTo(1);
        assertThat(courseAttendance.getCourseId()).isEqualTo(300);
        assertThat(courseAttendance.getCourseSegmentCount()).isEqualTo(2);
        assertThat(courseAttendance.getAttendedSegmentCount()).isEqualTo(1);

        final List<StudentSegment> segments = segmentAttendanceDao.findByStudentId(courseAttendance.getId());
        assertThat(segments).hasSize(1);

        final StudentSegment segment = segments.get(0);
        assertThat(segment.getStudentId()).isEqualTo(courseAttendance.getId());
        assertThat(segment.getSegmentId()).isEqualTo(3011);
    }

    @Test
    public void doneSegmentAlreadyViewed() throws Exception {
        final StudentRequest request = new StudentRequest();
        request.setRoleId(1);
        request.setSegmentId(4011);

        // When
        final String jsonRequest = mapper.writeValueAsString(request);
        final String jsonResponse = mockMvc
                .perform(
                        post("/academy/v1/courses/400/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.student.roleId", is(1)))
                .andExpect(jsonPath("$.student.courseId", is(400)))
                .andReturn()
                .getResponse()
                .getContentAsString()
                ;

        // Then
        final StudentResponse response = mapper.readValue(jsonResponse, StudentResponse.class);
        final Student courseAttendance = courseAttendanceDao.findOne(response.getStudent().getId());
        assertThat(courseAttendance.getRoleId()).isEqualTo(1);
        assertThat(courseAttendance.getCourseId()).isEqualTo(400);
        assertThat(courseAttendance.getCourseSegmentCount()).isEqualTo(3);
        assertThat(courseAttendance.getAttendedSegmentCount()).isEqualTo(1);

        final StudentSegment segment = segmentAttendanceDao.findOne(401);
        assertThat(segment.getStudentId()).isEqualTo(400);
        assertThat(segment.getSegmentId()).isEqualTo(4011);
    }

    @Test
    public void doneAllSegments() throws Exception {
        // Given
        final StudentRequest request = new StudentRequest();
        request.setRoleId(1);
        request.setSegmentId(5013);

        // When
        final String jsonRequest = mapper.writeValueAsString(request);
        final String jsonResponse = mockMvc
                .perform(
                        post("/academy/v1/courses/500/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.student.roleId", is(1)))
                .andExpect(jsonPath("$.student.courseId", is(500)))
                .andReturn()
                .getResponse()
                .getContentAsString()
                ;

        // Then
        final StudentResponse response = mapper.readValue(jsonResponse, StudentResponse.class);
        final Student courseAttendance = courseAttendanceDao.findOne(response.getStudent().getId());
        assertThat(courseAttendance.getRoleId()).isEqualTo(1);
        assertThat(courseAttendance.getCourseId()).isEqualTo(500);
        assertThat(courseAttendance.getCourseSegmentCount()).isEqualTo(3);
        assertThat(courseAttendance.getAttendedSegmentCount()).isEqualTo(3);
    }

    @Test
    public void shouldReturnStudent() throws Exception{
        mockMvc
                .perform(
                        get("/academy/v1/courses/400/students/1")
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.student.roleId", is(1)))
                .andExpect(jsonPath("$.student.courseId", is(400)))
                .andExpect(jsonPath("$.student.courseSegmentCount", is(3)))

                .andExpect(jsonPath("$.student.segments.length()", is(1)))
                .andExpect(jsonPath("$.student.segments[0].segmentId", is(4011)))
                ;

    }
}
