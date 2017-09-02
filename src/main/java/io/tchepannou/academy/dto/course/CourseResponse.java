package io.tchepannou.academy.dto.course;

import io.tchepannou.academy.dto.BaseResponse;

public class CourseResponse extends BaseResponse {
    private CourseDto course;

    public CourseResponse(){

    }
    public CourseResponse(final CourseDto course){
        this.course = course;
    }

    public CourseDto getCourse() {
        return course;
    }

    public void setCourse(final CourseDto course) {
        this.course = course;
    }
}
