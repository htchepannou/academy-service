package io.tchepannou.academy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.tchepannou.academy.ControllerITSupport;
import io.tchepannou.academy.dto.quiz.QuizAnswerRequest;
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

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql({"classpath:/sql/clean.sql", "classpath:/sql/QuizController.sql"})
public class QuizControllerIT extends ControllerITSupport {
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp(){
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void shouldFindQuiz() throws Exception{
        // When
        mockMvc
                .perform(
                        get("/academy/v1/quiz/10103")
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.quiz.id", is(10103)))
                .andExpect(jsonPath("$.quiz.videoId", is(101031)))
                .andExpect(jsonPath("$.quiz.type", is("multichoice")))
                .andExpect(jsonPath("$.quiz.question", is("Who might be a potential end user of documentation?")))
                .andExpect(jsonPath("$.quiz.description", is("This is the description")))

                .andExpect(jsonPath("$.quiz.choices.length()", is(3)))
                .andExpect(jsonPath("$.quiz.choices[0].id", is(101031)))
                .andExpect(jsonPath("$.quiz.choices[0].rank", is(1)))
                .andExpect(jsonPath("$.quiz.choices[0].answer", is(false)))
                .andExpect(jsonPath("$.quiz.choices[0].text", is("You")))
                .andExpect(jsonPath("$.quiz.choices[1].id", is(101032)))
                .andExpect(jsonPath("$.quiz.choices[1].rank", is(2)))
                .andExpect(jsonPath("$.quiz.choices[1].answer", is(true)))
                .andExpect(jsonPath("$.quiz.choices[1].text", is("Your coworkers")))
                .andExpect(jsonPath("$.quiz.choices[2].id", is(101033)))
                .andExpect(jsonPath("$.quiz.choices[2].rank", is(3)))
                .andExpect(jsonPath("$.quiz.choices[2].answer", is(true)))
                .andExpect(jsonPath("$.quiz.choices[2].text", is("Your users")))
        ;
    }

    @Test
    public void shouldAnswerValidAnswer() throws Exception {
        // Given
        final QuizAnswerRequest req = new QuizAnswerRequest();
        req.setValues(Arrays.asList("101032", "101033"));

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        mockMvc
                .perform(
                        post("/academy/v1/quiz/10103/answer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.valid", is(true)))
                .andExpect(jsonPath("$.message", is("Awesome")))
                ;

    }

    @Test
    public void shouldAnswerInValidAnswer() throws Exception {
        // Given
        final QuizAnswerRequest req = new QuizAnswerRequest();
        req.setValues(Arrays.asList("101032"));

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        mockMvc
                .perform(
                        post("/academy/v1/quiz/10103/answer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.valid", is(false)))
                .andExpect(jsonPath("$.message", is("Looser")))
        ;

    }

}
