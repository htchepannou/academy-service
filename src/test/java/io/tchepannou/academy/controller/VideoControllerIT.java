package io.tchepannou.academy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.tchepannou.academy.ControllerITSupport;
import io.tchepannou.academy.dao.VideoDao;
import io.tchepannou.academy.domain.Video;
import io.tchepannou.academy.client.video.CreateVideoRequest;
import io.tchepannou.academy.client.video.CreateVideoResponse;
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
@Sql({"classpath:/sql/clean.sql", "classpath:/sql/VideoController.sql"})
public class VideoControllerIT extends ControllerITSupport {
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp(){
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void shoulCreateYoutubeVideo () throws Exception{
        // Given
        final CreateVideoRequest req = new CreateVideoRequest();
        req.setUrl("https://www.youtube.com/watch?v=LGmnTILNaPM");
        req.setDurationSeconds(305);

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        final String jsonResponse = mockMvc
                .perform(
                        post("/academy/v1/videos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.video.durationSecond", is(305)))
                .andExpect(jsonPath("$.video.videoId", is("LGmnTILNaPM")))
                .andExpect(jsonPath("$.video.type", is("youtube")))
                .andReturn()
                .getResponse()
                .getContentAsString()
                ;
        CreateVideoResponse resp = mapper.readValue(jsonResponse, CreateVideoResponse.class);

        // Then
        Video video = videoDao.findOne(resp.getVideo().getId());
        assertThat(video.getTypeId()).isEqualTo(1);
        assertThat(video.getVideoId()).isEqualTo("LGmnTILNaPM");
        assertThat(video.getDurationSecond()).isEqualTo(305);
    }

    @Test
    public void shoulNotCreateDuplicateVideo () throws Exception{
        // Given
        final CreateVideoRequest req = new CreateVideoRequest();
        req.setUrl("https://www.youtube.com/watch?v=__created__");
        req.setDurationSeconds(305);

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        mockMvc
                .perform(
                        post("/academy/v1/videos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.video.id", is(200)))
                .andExpect(jsonPath("$.video.durationSecond", is(777)))
                .andExpect(jsonPath("$.video.videoId", is("__created__")))
                .andExpect(jsonPath("$.video.type", is("youtube")))
                ;
    }

    @Test
    public void shouldFindVideo() throws Exception{
        // When
        mockMvc
                .perform(
                        get("/academy/v1/videos/100")
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.video.id", is(100)))
                .andExpect(jsonPath("$.video.type", is("youtube")))
                .andExpect(jsonPath("$.video.durationSecond", is(305)))
                .andExpect(jsonPath("$.video.videoId", is("dfr019")))
        ;
    }

}
