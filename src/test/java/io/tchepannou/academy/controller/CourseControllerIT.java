package io.tchepannou.academy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.tchepannou.academy.ControllerITSupport;
import io.tchepannou.academy.dao.CourseDao;
import io.tchepannou.academy.domain.Course;
import io.tchepannou.academy.dto.course.CreateCourseRequest;
import io.tchepannou.academy.dto.course.CreateCourseResponse;
import io.tchepannou.academy.dto.course.UpdateCourseRequest;
import io.tchepannou.academy.dto.course.UpdateCourseStatusRequest;
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
import static org.hamcrest.CoreMatchers.startsWith;
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
                        post("/academy/v1/courses/100")
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
                        get("/academy/v1/courses/300")
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
                .andExpect(jsonPath("$.course.publishedDateTime", startsWith("2017-01-02")))
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
                        post("/academy/v1/courses/200/status")
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
                        post("/academy/v1/courses/202/status")
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
                        post("/academy/v1/courses/203/status")
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
                        post("/academy/v1/courses/201/status")
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



    //-- LESSON
    @Test
    public void shouldFindLesson() throws Exception{
        // When
        mockMvc
                .perform(
                        get("/academy/v1/courses/300/lessons/310")
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.lesson.id", is(310)))
                .andExpect(jsonPath("$.lesson.rank", is(1)))
                .andExpect(jsonPath("$.lesson.title", is("Introduction")))
        ;
    }

    @Test
    public void shouldFindLessonByCourse() throws Exception{
        // When
        mockMvc
                .perform(
                        get("/academy/v1/courses/300/lessons")
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lessons.length()", is(3)))
                .andExpect(jsonPath("$.lessons[0].id", is(310)))
                .andExpect(jsonPath("$.lessons[0].rank", is(1)))
                .andExpect(jsonPath("$.lessons[0].title", is("Introduction")))

                .andExpect(jsonPath("$.lessons[1].id", is(302)))
                .andExpect(jsonPath("$.lessons[1].rank", is(2)))
                .andExpect(jsonPath("$.lessons[1].title", is("Querying the database")))

                .andExpect(jsonPath("$.lessons[2].id", is(303)))
                .andExpect(jsonPath("$.lessons[2].rank", is(3)))
                .andExpect(jsonPath("$.lessons[2].title", is("Conclusion")))
        ;
    }


    //-- SEGMENT
    @Test
    public void shouldFindSegment() throws Exception{
        // When
        mockMvc
                .perform(
                        get("/academy/v1/courses/300/segments/3101")
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.segment.id", is(3101)))
                .andExpect(jsonPath("$.segment.videoId", is(3101)))
                .andExpect(jsonPath("$.segment.rank", is(1)))
                .andExpect(jsonPath("$.segment.type", is("video")))
                .andExpect(jsonPath("$.segment.title", is("Welcome")))
                .andExpect(jsonPath("$.segment.summary", is("Greeting from author")))
                .andExpect(jsonPath("$.segment.description", is("Presentation of the course and objectives from the author <b>Ray Sponsible</b>")))
        ;
    }

    @Test
    public void shouldFindSegmentsByLesson() throws Exception{
        // When
        mockMvc
                .perform(
                        get("/academy/v1/courses/300/segments")
                                .param("lessonId", "310")
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.size", is(4)))
                .andExpect(jsonPath("$.segments.length()", is(4)))

                .andExpect(jsonPath("$.segments[0].id", is(3101)))
                .andExpect(jsonPath("$.segments[0].rank", is(1)))
                .andExpect(jsonPath("$.segments[0].type", is("video")))
                .andExpect(jsonPath("$.segments[0].title", is("Welcome")))
                .andExpect(jsonPath("$.segments[0].summary", is("Greeting from author")))
                .andExpect(jsonPath("$.segments[0].durationSecond", is(52)))

                .andExpect(jsonPath("$.segments[1].id", is(3102)))
                .andExpect(jsonPath("$.segments[1].rank", is(2)))
                .andExpect(jsonPath("$.segments[1].type", is("video")))
                .andExpect(jsonPath("$.segments[1].title", is("What is a database?")))
                .andExpect(jsonPath("$.segments[1].videoId", is(3102)))
                .andExpect(jsonPath("$.segments[1].durationSecond", is(63)))

                .andExpect(jsonPath("$.segments[2].id", is(3103)))
                .andExpect(jsonPath("$.segments[2].rank", is(3)))
                .andExpect(jsonPath("$.segments[2].type", is("text")))
                .andExpect(jsonPath("$.segments[2].title", is("SQL cheat sheet")))

                .andExpect(jsonPath("$.segments[3].id", is(3104)))
                .andExpect(jsonPath("$.segments[3].rank", is(4)))
                .andExpect(jsonPath("$.segments[3].type", is("quiz")))
                .andExpect(jsonPath("$.segments[3].title", is("Quiz: Search data")))
                .andExpect(jsonPath("$.segments[3].videoId", is(3104)))
                .andExpect(jsonPath("$.segments[3].quizId", is(3104)))
        ;
    }    
}
