package io.tchepannou.academy.service;

import io.tchepannou.academy.domain.Course;
import io.tchepannou.academy.domain.CourseLevel;
import io.tchepannou.academy.domain.CourseStatus;
import io.tchepannou.academy.dto.course.CourseDto;
import io.tchepannou.academy.dto.course.CreateCourseRequest;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public Course toCourse(
            final CreateCourseRequest request,
            final Course course,
            final CourseLevel level,
            final CourseStatus status
    ){
        course.setDescription(request.getDescription());
        course.setLanguage(request.getLanguage());
        course.setLevelId(level.getId());
        course.setStatusId(status.getId());
        course.setSummary(request.getSummary());
        course.setTitle(request.getTitle());

        return course;
    }



    public CourseDto toCourseDto (
            final Course course,
            final CourseLevel level,
            final CourseStatus status
    ){
        CourseDto dto = new CourseDto();
        dto.setDescription(course.getDescription());
        dto.setId(course.getId());
        dto.setLanguage(course.getLanguage());
        dto.setLevel(level.getName());
        dto.setPublishedDateTime(course.getPublishedDateTime());
        dto.setUpdatedDateTime(course.getUpdateDateTime());
        dto.setStatus(status.getName());
        dto.setSummary(course.getSummary());
        dto.setTitle(course.getTitle());
        return dto;
    }
}
