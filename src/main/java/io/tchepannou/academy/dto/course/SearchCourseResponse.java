package io.tchepannou.academy.dto.course;

import java.util.List;

public class SearchCourseResponse {
    private List<CourseDto> courses;

    public List<CourseDto> getCourses() {
        return courses;
    }

    public void setCourses(final List<CourseDto> courses) {
        this.courses = courses;
    }
}
