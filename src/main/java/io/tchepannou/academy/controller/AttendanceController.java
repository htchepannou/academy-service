package io.tchepannou.academy.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.tchepannou.academy.dto.attendance.AttendanceResponse;
import io.tchepannou.academy.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/academy/v1/attendances", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/academy/v1/attendances", description = "Course Attendance")
public class AttendanceController extends BaseController {

    @Autowired
    private AttendanceService attendanceService;

    @RequestMapping(path="/students/{studentId}/segments/{segmentId}/done", method = RequestMethod.POST)
    @ApiOperation(value = "Done", notes = "Done attending a segment")
    public AttendanceResponse done(@PathVariable Integer studentId, @PathVariable Integer segmentId) {
        return init(attendanceService.done(studentId, segmentId));
    }

    @RequestMapping(path="/students/{studentId}/courses/{courseId}", method = RequestMethod.GET)
    @ApiOperation(value = "findByCourseByStudent", notes = "Done attending a segment")
    public AttendanceResponse findByStudentAndCourse(@PathVariable Integer studentId, @PathVariable Integer courseId) {
        return init(attendanceService.findByStudentAndCourse(studentId, courseId));
    }
}
