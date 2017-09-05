package io.tchepannou.academy.service;

import io.tchepannou.academy.dao.CourseDao;
import io.tchepannou.academy.dao.LessonDao;
import io.tchepannou.academy.domain.Course;
import io.tchepannou.academy.domain.Lesson;
import io.tchepannou.academy.dto.lesson.LessonDto;
import io.tchepannou.academy.dto.lesson.LessonListResponse;
import io.tchepannou.academy.dto.lesson.LessonResponse;
import io.tchepannou.academy.exception.BusinessError;
import io.tchepannou.academy.exception.NotFoundException;
import io.tchepannou.academy.mapper.LessonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LessonService {
    @Autowired
    private LessonDao lessonDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private LessonMapper mapper;


    public LessonResponse findById(final Integer courseId, final Integer lessonId) {
        final Lesson lesson = lessonDao.findOne(lessonId);
        if (lesson == null || !lesson.getCourseId().equals(courseId)){
            throw new NotFoundException(BusinessError.LESSON_NOT_FOUND);
        }

        final LessonDto dto = mapper.toLessonDto(lesson);
        return new LessonResponse(dto);
    }

    public LessonListResponse findByCourse(final Integer courseId){
        final PageRequest pageable = new PageRequest(0, Integer.MAX_VALUE, Sort.Direction.ASC, "rank");
        final List<Lesson> lessons = lessonDao.findByCourseId(courseId, pageable);

        final LessonListResponse response = new LessonListResponse();
        for (final Lesson lesson : lessons){
            final LessonDto dto = mapper.toLessonDto(lesson);
            response.add(dto);
        }
        return response;
    }

    private Course findCourseById(final Integer id){
        Course course = courseDao.findOne(id);
        if (course == null){
            throw new NotFoundException(BusinessError.COURSE_NOT_FOUND);
        }
        return course;
    }
}
