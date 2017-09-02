package io.tchepannou.academy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.tchepannou.academy.dto.video.VideoResponse;
import io.tchepannou.academy.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/academy/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/academy/v1", description = "Videos")
public class VideoController extends BaseController {
    @Autowired
    private VideoService videoService;

    @RequestMapping(path = "/video/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "findById", notes = "Find a video")
    public VideoResponse findById(@PathVariable Integer id) {
        return init(videoService.findById(id));
    }

}
