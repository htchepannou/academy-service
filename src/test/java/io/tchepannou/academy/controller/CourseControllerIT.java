package io.tchepannou.academy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.tchepannou.academy.ControllerITSupport;
import io.tchepannou.academy.dao.CourseDao;
import io.tchepannou.academy.dao.LegDao;
import io.tchepannou.academy.domain.Course;
import io.tchepannou.academy.domain.Leg;
import io.tchepannou.academy.dto.course.CreateCourseRequest;
import io.tchepannou.academy.dto.course.CreateCourseResponse;
import io.tchepannou.academy.dto.course.UpdateCourseRequest;
import io.tchepannou.academy.dto.course.UpdateCourseStatusRequest;
import io.tchepannou.academy.dto.leg.CreateLegRequest;
import io.tchepannou.academy.dto.leg.CreateLegResponse;
import io.tchepannou.academy.dto.leg.UpdateLegRequest;
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
@Sql({"classpath:/sql/clean.sql", "classpath:/sql/CourseController.sql"})
public class CourseControllerIT extends ControllerITSupport {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private LegDao legDao;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setUp(){
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    //--- COURSE
    @Test
    public void shouldCreateCourse() throws Exception{
        // Given
        final CreateCourseRequest req = new CreateCourseRequest();
        req.setDescription("This is a sample course");
        req.setLanguage("en");
        req.setLevel("advanced");
        req.setSummary("This is the summary");
        req.setTitle("Test course");

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        final String jsonResponse = mockMvc
                .perform(
                        post("/academy/v1/courses")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.course.description", is("This is a sample course")))
                .andExpect(jsonPath("$.course.language", is("en")))
                .andExpect(jsonPath("$.course.level", is("advanced")))
                .andExpect(jsonPath("$.course.summary", is("This is the summary")))
                .andExpect(jsonPath("$.course.status", is("draft")))
                .andExpect(jsonPath("$.course.title", is("Test course")))
                .andReturn()
                .getResponse()
                .getContentAsString()
                ;
        CreateCourseResponse resp = mapper.readValue(jsonResponse, CreateCourseResponse.class);

        // Then
        Course course = courseDao.findOne(resp.getCourse().getId());
        assertThat(course.getDescription()).isEqualTo("This is a sample course");
        assertThat(course.getLanguage()).isEqualTo("en");
        assertThat(course.getLevelId()).isEqualTo(4);
        assertThat(course.getPublishedDateTime()).isNull();
        assertThat(course.getStatusId()).isEqualTo(1);
        assertThat(course.getSummary()).isEqualTo("This is the summary");
        assertThat(course.getTitle()).isEqualTo("Test course");
    }

    @Test
    public void shouldUpdateCourse() throws Exception{
        // Given
        final UpdateCourseRequest req = new UpdateCourseRequest();
        req.setDescription("This is a sample course1");
        req.setLanguage("fr");
        req.setLevel("advanced");
        req.setSummary("This is the summary1");
        req.setTitle("Test course1");

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        mockMvc
                .perform(
                        post("/academy/v1/course/100")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.course.description", is("This is a sample course1")))
                .andExpect(jsonPath("$.course.language", is("fr")))
                .andExpect(jsonPath("$.course.level", is("advanced")))
                .andExpect(jsonPath("$.course.summary", is("This is the summary1")))
                .andExpect(jsonPath("$.course.status", is("draft")))
                .andExpect(jsonPath("$.course.title", is("Test course1")))
                ;

        // Then
        Course course = courseDao.findOne(100);
        assertThat(course.getDescription()).isEqualTo("This is a sample course1");
        assertThat(course.getLanguage()).isEqualTo("fr");
        assertThat(course.getLevelId()).isEqualTo(4);
        assertThat(course.getPublishedDateTime()).isNull();
        assertThat(course.getStatusId()).isEqualTo(1);
        assertThat(course.getSummary()).isEqualTo("This is the summary1");
        assertThat(course.getTitle()).isEqualTo("Test course1");
    }

    @Test
    public void shouldFindCourse() throws Exception{
        // When
        mockMvc
                .perform(
                        get("/academy/v1/course/300")
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.course.description", is("Description of...")))
                .andExpect(jsonPath("$.course.language", is("en")))
                .andExpect(jsonPath("$.course.level", is("intermediate")))
                .andExpect(jsonPath("$.course.summary", is("Summary of...")))
                .andExpect(jsonPath("$.course.status", is("published")))
                .andExpect(jsonPath("$.course.title", is("Title of...")))
                .andExpect(jsonPath("$.course.publishedDateTime", is("2017-01-02T03:30:00-0500")))

                .andExpect(jsonPath("$.course.legs.length()", is(3)))
                .andExpect(jsonPath("$.course.legs[0].id", is(310)))
                .andExpect(jsonPath("$.course.legs[0].rank", is(1)))
                .andExpect(jsonPath("$.course.legs[0].title", is("Introduction")))
                .andExpect(jsonPath("$.course.legs[0].segments.length()", is(3)))
                .andExpect(jsonPath("$.course.legs[0].segments[0].id", is(3101)))
                .andExpect(jsonPath("$.course.legs[0].segments[0].videoId", is(3101)))
                .andExpect(jsonPath("$.course.legs[0].segments[0].rank", is(1)))
                .andExpect(jsonPath("$.course.legs[0].segments[0].type", is("video")))
                .andExpect(jsonPath("$.course.legs[0].segments[0].title", is("Welcome")))
                .andExpect(jsonPath("$.course.legs[0].segments[0].summary", is("Greeting from author")))
                .andExpect(jsonPath("$.course.legs[0].segments[1].id", is(3102)))
                .andExpect(jsonPath("$.course.legs[0].segments[1].videoId", is(3102)))
                .andExpect(jsonPath("$.course.legs[0].segments[1].rank", is(2)))
                .andExpect(jsonPath("$.course.legs[0].segments[1].type", is("video")))
                .andExpect(jsonPath("$.course.legs[0].segments[1].title", is("What is a database?")))
                .andExpect(jsonPath("$.course.legs[0].segments[2].id", is(3103)))
                .andExpect(jsonPath("$.course.legs[0].segments[2].rank", is(3)))
                .andExpect(jsonPath("$.course.legs[0].segments[2].type", is("quiz")))
                .andExpect(jsonPath("$.course.legs[0].segments[2].title", is("Quiz #1")))

                .andExpect(jsonPath("$.course.legs[1].id", is(302)))
                .andExpect(jsonPath("$.course.legs[1].rank", is(2)))
                .andExpect(jsonPath("$.course.legs[1].title", is("Querying the database")))
                .andExpect(jsonPath("$.course.legs[1].segments.length()", is(2)))
                .andExpect(jsonPath("$.course.legs[1].segments[0].id", is(3029)))
                .andExpect(jsonPath("$.course.legs[1].segments[0].videoId", is(3021)))
                .andExpect(jsonPath("$.course.legs[1].segments[0].rank", is(1)))
                .andExpect(jsonPath("$.course.legs[1].segments[0].type", is("video")))
                .andExpect(jsonPath("$.course.legs[1].segments[0].title", is("Using SELECT")))
                .andExpect(jsonPath("$.course.legs[1].segments[1].id", is(3022)))
                .andExpect(jsonPath("$.course.legs[1].segments[1].rank", is(2)))
                .andExpect(jsonPath("$.course.legs[1].segments[1].type", is("quiz")))
                .andExpect(jsonPath("$.course.legs[1].segments[1].title", is("Quiz #2")))

                .andExpect(jsonPath("$.course.legs[2].id", is(303)))
                .andExpect(jsonPath("$.course.legs[2].rank", is(3)))
                .andExpect(jsonPath("$.course.legs[2].title", is("Conclusion")))
                .andExpect(jsonPath("$.course.legs[2].segments.length()", is(1)))
                .andExpect(jsonPath("$.course.legs[2].segments[0].id", is(3031)))
                .andExpect(jsonPath("$.course.legs[2].segments[0].videoId", is(3031)))
                .andExpect(jsonPath("$.course.legs[2].segments[0].rank", is(1)))
                .andExpect(jsonPath("$.course.legs[2].segments[0].type", is("video")))
                .andExpect(jsonPath("$.course.legs[2].segments[0].title", is("Project")))
        ;
    }

    @Test
    public void shouldChangeStatusFromDraftToPublished() throws Exception{
        // Given
        final UpdateCourseStatusRequest req = new UpdateCourseStatusRequest();
        req.setStatus("published");

        final long now = System.currentTimeMillis();
        Thread.sleep(1000);

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        mockMvc
                .perform(
                        post("/academy/v1/course/200/status")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
        ;

        // Then
        Course course = courseDao.findOne(200);
        assertThat(course.getPublishedDateTime().getTime()).isGreaterThan(now);
        assertThat(course.getUpdateDateTime().getTime()).isEqualTo(course.getPublishedDateTime().getTime());
        assertThat(course.getStatusId()).isEqualTo(2);
    }

    @Test
    public void shouldChangeStatusFromDraftToPublishedV2() throws Exception{
        // Given
        final UpdateCourseStatusRequest req = new UpdateCourseStatusRequest();
        req.setStatus("published");

        final long now = System.currentTimeMillis();
        Thread.sleep(1000);

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        mockMvc
                .perform(
                        post("/academy/v1/course/202/status")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
        ;

        // Then
        Course course = courseDao.findOne(202);
        assertThat(course.getPublishedDateTime().getTime()).isLessThan(now);
        assertThat(course.getUpdateDateTime().getTime()).isGreaterThan(now);
        assertThat(course.getStatusId()).isEqualTo(2);
    }

    @Test
    public void shouldChangeStatusFromPublishedToPublished() throws Exception{
        // Given
        final UpdateCourseStatusRequest req = new UpdateCourseStatusRequest();
        req.setStatus("published");

        final long now = System.currentTimeMillis();

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        mockMvc
                .perform(
                        post("/academy/v1/course/203/status")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
        ;

        // Then
        Course course = courseDao.findOne(203);
        assertThat(course.getPublishedDateTime().getTime()).isLessThan(now);
        assertThat(course.getUpdateDateTime().getTime()).isEqualTo(course.getPublishedDateTime().getTime());
        assertThat(course.getStatusId()).isEqualTo(2);
    }

    @Test
    public void shouldChangeStatusToArchived() throws Exception{
        // Given
        final UpdateCourseStatusRequest req = new UpdateCourseStatusRequest();
        req.setStatus("archived");

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        mockMvc
                .perform(
                        post("/academy/v1/course/201/status")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
        ;

        // Then
        Course course = courseDao.findOne(201);
        assertThat(course.getStatusId()).isEqualTo(3);
    }


    //--- LEG
    @Test
    public void shouldCreateLeg() throws Exception{
        // Given
        final CreateLegRequest req = new CreateLegRequest();
        req.setRank(1);
        req.setTitle("shouldCreateLeg");

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        final String jsonResponse = mockMvc
                .perform(
                        post("/academy/v1/course/400/legs")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.leg.title", is("shouldCreateLeg")))
                .andExpect(jsonPath("$.leg.rank", is(1)))
                .andReturn()
                .getResponse()
                .getContentAsString()
                ;
        CreateLegResponse resp = mapper.readValue(jsonResponse, CreateLegResponse.class);

        // Then
        final Leg leg = legDao.findOne(resp.getLeg().getId());
        assertThat(leg.getTitle()).isEqualTo("shouldCreateLeg");
        assertThat(leg.getRank()).isEqualTo(1);
    }

    @Test
    public void shouldUpdateLeg() throws Exception{
        // Given
        final UpdateLegRequest req = new UpdateLegRequest();
        req.setRank(10);
        req.setTitle("shouldUpdateLeg");

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        mockMvc
                .perform(
                        post("/academy/v1/course/400/leg/401")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.leg.title", is("shouldUpdateLeg")))
                .andExpect(jsonPath("$.leg.rank", is(10)))
        ;

        // Then
        final Leg leg = legDao.findOne(401);
        assertThat(leg.getTitle()).isEqualTo("shouldUpdateLeg");
        assertThat(leg.getRank()).isEqualTo(10);
    }

}
