package io.tchepannou.academy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.tchepannou.academy.dto.course.CourseResponse;
import io.tchepannou.academy.dto.course.CreateCourseRequest;
import io.tchepannou.academy.dto.course.CreateCourseResponse;
import io.tchepannou.academy.dto.course.UpdateCourseRequest;
import io.tchepannou.academy.dto.course.UpdateCourseResponse;
import io.tchepannou.academy.dto.course.UpdateCourseStatusRequest;
import io.tchepannou.academy.dto.course.UpdateCourseStatusResponse;
import io.tchepannou.academy.dto.leg.CreateLegRequest;
import io.tchepannou.academy.dto.leg.LegResponse;
import io.tchepannou.academy.dto.leg.UpdateLegRequest;
import io.tchepannou.academy.service.CourseService;
import io.tchepannou.academy.service.LegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/academy/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/academy/v1", description = "Courses")
public class CourseController extends BaseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private LegService legService;


    @RequestMapping(path = "/courses", method = RequestMethod.POST)
    @ApiOperation(value = "create", notes = "Create a new travel course")
    public CreateCourseResponse create(@RequestBody @Valid CreateCourseRequest request){
        CreateCourseResponse response = courseService.create(request);
        return init(response);
    }

//    @RequestMapping(path = "/courses", method = RequestMethod.GET)
//    @ApiOperation(value = "search", notes = "Search courses")
//    public SearchCourseResponse search(@RequestBody @Valid SearchCourseRequest request) {
//        return courseService.search(request);
//    }

    @RequestMapping(path = "/course/{id}", method = RequestMethod.POST)
    @ApiOperation(value = "update", notes = "Update the information of the course")
    public UpdateCourseResponse update(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateCourseRequest request
    ) {
        UpdateCourseResponse response = courseService.update(id, request);
        return init(response);
    }

    @RequestMapping(path = "/course/{id}/status", method = RequestMethod.POST)
    @ApiOperation(value = "status", notes = "Update the status of the course")
    public UpdateCourseStatusResponse status(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateCourseStatusRequest request
    ){
        return courseService.status(id, request);
    }

    @RequestMapping(path = "/course/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "findById", notes = "Find a course")
    public CourseResponse findById(@PathVariable Integer id) {
        return init(courseService.findById(id));
    }

    @RequestMapping(path = "/course/{courseId}/legs", method = RequestMethod.POST)
    @ApiOperation(value = "addLeg", notes = "Add a leg to a course")
    public LegResponse addLeg(@PathVariable Integer courseId, @RequestBody @Valid CreateLegRequest request) {
        return init(legService.addLeg(courseId, request));
    }

    @RequestMapping(path = "/course/{courseId}/leg/{legId}", method = RequestMethod.POST)
    @ApiOperation(value = "updateLeg", notes = "Update a leg")
    public LegResponse updateLeg(
            @PathVariable Integer courseId,
            @PathVariable Integer legId,
            @RequestBody @Valid UpdateLegRequest request
    ) {
        return init(legService.updateLeg(courseId, legId, request));
    }

}
