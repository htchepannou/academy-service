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
import io.tchepannou.academy.dto.lesson.CreateLessonRequest;
import io.tchepannou.academy.dto.lesson.LessonResponse;
import io.tchepannou.academy.dto.lesson.UpdateLessonRequest;
import io.tchepannou.academy.dto.segment.SegmentListResponse;
import io.tchepannou.academy.dto.segment.SegmentResponse;
import io.tchepannou.academy.service.CourseService;
import io.tchepannou.academy.service.LessonService;
import io.tchepannou.academy.service.SegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/academy/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/academy/v1", description = "Courses")
public class CourseController extends BaseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private SegmentService segmentService;

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

    @RequestMapping(path = "/course/{courseId}/lessons", method = RequestMethod.POST)
    @ApiOperation(value = "addLesson", notes = "Add a lesson to a course")
    public LessonResponse addLesson(@PathVariable Integer courseId, @RequestBody @Valid CreateLessonRequest request) {
        return init(lessonService.addLeg(courseId, request));
    }

    @RequestMapping(path = "/course/{courseId}/lesson/{lessonId}", method = RequestMethod.POST)
    @ApiOperation(value = "updateLesson", notes = "Update a lesson")
    public LessonResponse updateLesson(
            @PathVariable Integer courseId,
            @PathVariable Integer lessonId,
            @RequestBody @Valid UpdateLessonRequest request
    ) {
        return init(lessonService.updateLeg(courseId, lessonId, request));
    }


    //----- SEGMENT
    @RequestMapping(path = "/course/{courseId}/segment/{segmentId}", method = RequestMethod.GET)
    @ApiOperation(value = "findSegmentById", notes = "Find a segment")
    public SegmentResponse findSegmentById(@PathVariable Integer courseId, @PathVariable Integer segmentId) {
        return init(segmentService.findById(courseId, segmentId));
    }

    @RequestMapping(path = "/course/{courseId}/segments", method = RequestMethod.GET)
    @ApiOperation(value = "findSegments", notes = "Find all the segments of a lesson")
    public SegmentListResponse findSegmentsByLessonId(
            @PathVariable Integer courseId,
            @RequestParam(name="lessonId", required = true) Integer lessonId
    ){
        return init(segmentService.findByLessonId(courseId, lessonId));
    }
}
