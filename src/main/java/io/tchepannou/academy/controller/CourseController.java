package io.tchepannou.academy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.tchepannou.academy.client.course.CourseResponse;
import io.tchepannou.academy.client.course.CreateCourseRequest;
import io.tchepannou.academy.client.course.CreateCourseResponse;
import io.tchepannou.academy.client.course.UpdateCourseRequest;
import io.tchepannou.academy.client.course.UpdateCourseResponse;
import io.tchepannou.academy.client.course.UpdateCourseStatusRequest;
import io.tchepannou.academy.client.course.UpdateCourseStatusResponse;
import io.tchepannou.academy.client.course.StudentRequest;
import io.tchepannou.academy.client.course.StudentResponse;
import io.tchepannou.academy.service.CourseService;
import io.tchepannou.academy.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/academy/v1/courses", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/academy/v1/courses", description = "Courses")
public class CourseController extends BaseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "create", notes = "Create a new travel course")
    public CreateCourseResponse create(@RequestBody @Valid CreateCourseRequest request){
        CreateCourseResponse response = courseService.create(request);
        return init(response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    @ApiOperation(value = "update", notes = "Update the information of the course")
    public UpdateCourseResponse update(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateCourseRequest request
    ) {
        UpdateCourseResponse response = courseService.update(id, request);
        return init(response);
    }

    @RequestMapping(path = "/{id}/status", method = RequestMethod.POST)
    @ApiOperation(value = "status", notes = "Update the status of the course")
    public UpdateCourseStatusResponse status(
            @PathVariable Integer id,
            @RequestBody @Valid UpdateCourseStatusRequest request
    ){
        return courseService.status(id, request);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "findById", notes = "Find a course")
    public CourseResponse findById(@PathVariable Integer id) {
        return init(courseService.findById(id));
    }

    @RequestMapping(path = "/{id}/students", method = RequestMethod.POST)
    @ApiOperation(value = "updateStudent")
    public StudentResponse updateStudent(
            @PathVariable Integer id,
            @RequestBody @Valid StudentRequest request
    ){
        return init(studentService.updateStudent(id, request));
    }

    @RequestMapping(path = "/{id}/students/{roleId}", method = RequestMethod.GET)
    @ApiOperation(value = "findStudent")
    public StudentResponse findStudent(
            @PathVariable Integer id,
            @PathVariable Integer roleId
    ){
        return init(studentService.findByCourseAndRole(id, roleId));
    }
}
