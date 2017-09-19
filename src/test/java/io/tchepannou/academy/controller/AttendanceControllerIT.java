package io.tchepannou.academy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.tchepannou.academy.dao.CourseAttendanceDao;
import io.tchepannou.academy.dao.SegmentAttendanceDao;
import io.tchepannou.academy.domain.CourseAttendance;
import io.tchepannou.academy.domain.SegmentAttendance;
import io.tchepannou.academy.dto.attendance.AttendanceResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql({"classpath:/sql/clean.sql", "classpath:/sql/AttendanceController.sql"})
public class AttendanceControllerIT {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CourseAttendanceDao courseAttendanceDao;

    @Autowired
    private SegmentAttendanceDao segmentAttendanceDao;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setUp(){
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void startShouldCreateCourseAttendance() throws Exception {
        // Given
        final Date now = new Date();
        Thread.sleep(1000);

        // When
        final String json = mockMvc
                .perform(
                        post("/academy/v1/attendances/students/1/segments/3011/start")
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.attendance.currentSegmentId", is(3011)))
                .andExpect(jsonPath("$.attendance.studentId", is(1)))
                .andExpect(jsonPath("$.attendance.courseId", is(300)))
                .andExpect(jsonPath("$.attendance.attendanceDateTime", notNullValue()))
                .andReturn()
                .getResponse()
                .getContentAsString()
                ;

        // Then
        final AttendanceResponse response = mapper.readValue(json, AttendanceResponse.class);
        final CourseAttendance attendance = courseAttendanceDao.findOne(response.getAttendance().getId());
        assertThat(attendance.getCurrentSegmentId()).isEqualTo(3011);
        assertThat(attendance.getStudentId()).isEqualTo(1);
        assertThat(attendance.getAttendanceDateTime()).isAfter(now);
        assertThat(attendance.getCourseId()).isEqualTo(300);
    }

    @Test
    public void startShouldModifyCourseAttendance() throws Exception {
        // When
        final String json = mockMvc
                .perform(
                        post("/academy/v1/attendances/students/1/segments/4013/start")
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.attendance.currentSegmentId", is(4013)))
                .andExpect(jsonPath("$.attendance.studentId", is(1)))
                .andExpect(jsonPath("$.attendance.courseId", is(400)))
                .andExpect(jsonPath("$.attendance.attendanceDateTime", notNullValue()))
                .andReturn()
                .getResponse()
                .getContentAsString()
                ;

        // Then
        final AttendanceResponse response = mapper.readValue(json, AttendanceResponse.class);
        final CourseAttendance attendance = courseAttendanceDao.findOne(response.getAttendance().getId());
        assertThat(attendance.getCurrentSegmentId()).isEqualTo(4013);
        assertThat(attendance.getStudentId()).isEqualTo(1);
        assertThat(attendance.getAttendanceDateTime().toString()).startsWith("2017-01-02");
        assertThat(attendance.getCourseId()).isEqualTo(400);
    }


    @Test
    public void doneShouldCreateSegmentAttendance() throws Exception {
        // Given
        final Date now = new Date();
        Thread.sleep(1000);

        // When
        final String jsonResponse = mockMvc
                .perform(
                        post("/academy/v1/attendances/students/1/segments/3011/done")
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.attendance.currentSegmentId", is(3011)))
                .andExpect(jsonPath("$.attendance.studentId", is(1)))
                .andExpect(jsonPath("$.attendance.courseId", is(300)))
                .andExpect(jsonPath("$.attendance.attendanceDateTime", notNullValue()))
                .andReturn()
                .getResponse()
                .getContentAsString()
                ;

        // Then
        final AttendanceResponse resp = mapper.readValue(jsonResponse, AttendanceResponse.class);
        final CourseAttendance courseAttendance = courseAttendanceDao.findOne(resp.getAttendance().getId());
        assertThat(courseAttendance.getCurrentSegmentId()).isEqualTo(3011);
        assertThat(courseAttendance.getStudentId()).isEqualTo(1);
        assertThat(courseAttendance.getAttendanceDateTime()).isAfter(now);
        assertThat(courseAttendance.getCourseId()).isEqualTo(300);

        final List<SegmentAttendance> segments = segmentAttendanceDao.findByCourseAttendanceId(courseAttendance.getId());
        assertThat(segments).hasSize(1);

        final SegmentAttendance segment = segments.get(0);
        assertThat(segment.getCourseAttendanceId()).isEqualTo(courseAttendance.getId());
        assertThat(segment.getAttendanceDateTime()).isAfter(now);
        assertThat(segment.getSegmentId()).isEqualTo(3011);
    }

    @Test
    public void doneShouldNotModifyExistingSegmentAttendance() throws Exception {
        // Given
        final Date now = new Date();
        Thread.sleep(1000);

        // When
        final String jsonResponse = mockMvc
                .perform(
                        post("/academy/v1/attendances/students/1/segments/4011/done")
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.attendance.currentSegmentId", is(4011)))
                .andExpect(jsonPath("$.attendance.studentId", is(1)))
                .andExpect(jsonPath("$.attendance.courseId", is(400)))
                .andExpect(jsonPath("$.attendance.attendanceDateTime", notNullValue()))
                .andReturn()
                .getResponse()
                .getContentAsString()
                ;

        // Then
        final AttendanceResponse response = mapper.readValue(jsonResponse, AttendanceResponse.class);
        final CourseAttendance courseAttendance = courseAttendanceDao.findOne(response.getAttendance().getId());
        assertThat(courseAttendance.getCurrentSegmentId()).isEqualTo(4011);
        assertThat(courseAttendance.getStudentId()).isEqualTo(1);
        assertThat(courseAttendance.getAttendanceDateTime().toString()).startsWith("2017-01-02");
        assertThat(courseAttendance.getCourseId()).isEqualTo(400);

        final SegmentAttendance segment = segmentAttendanceDao.findOne(401);
        assertThat(segment.getCourseAttendanceId()).isEqualTo(400);
        assertThat(segment.getAttendanceDateTime().toString()).startsWith("2017-01-02");
        assertThat(segment.getSegmentId()).isEqualTo(4011);
    }

    @Test
    public void shouldReturnAttendance() throws Exception{
        mockMvc
                .perform(
                        get("/academy/v1/attendances/students/1/courses/400")
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.attendance.currentSegmentId", is(4012)))
                .andExpect(jsonPath("$.attendance.studentId", is(1)))
                .andExpect(jsonPath("$.attendance.courseId", is(400)))
                .andExpect(jsonPath("$.attendance.attendanceDateTime", notNullValue()))

                .andExpect(jsonPath("$.attendance.segments.length()", is(1)))
                .andExpect(jsonPath("$.attendance.segments[0].id", is(401)))
                .andExpect(jsonPath("$.attendance.segments[0].segmentId", is(4011)))
                .andExpect(jsonPath("$.attendance.segments[0].attendanceDateTime", startsWith("2017-01-02")))
                ;

    }
}
