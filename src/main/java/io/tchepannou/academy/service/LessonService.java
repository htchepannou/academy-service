package io.tchepannou.academy.service;

import io.tchepannou.academy.dao.CourseDao;
import io.tchepannou.academy.dao.LessonDao;
import io.tchepannou.academy.domain.Course;
import io.tchepannou.academy.domain.Lesson;
import io.tchepannou.academy.dto.lesson.CreateLessonRequest;
import io.tchepannou.academy.dto.lesson.CreateLessonResponse;
import io.tchepannou.academy.dto.lesson.LessonDto;
import io.tchepannou.academy.dto.lesson.UpdateLessonRequest;
import io.tchepannou.academy.dto.lesson.UpdateLessonResponse;
import io.tchepannou.academy.exception.BusinessError;
import io.tchepannou.academy.exception.NotFoundException;
import io.tchepannou.academy.mapper.LessonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class LessonService {
    @Autowired
    private LessonDao lessonDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private LessonMapper mapper;

    @Transactional
    public CreateLessonResponse addLeg(final Integer courseId, final CreateLessonRequest request){
        final Course course = findCourseById(courseId);

        final Lesson lesson = new Lesson(course);
        mapper.toLeg(request, lesson);
        lessonDao.save(lesson);

        final LessonDto dto = mapper.toLegDto(lesson);
        return new CreateLessonResponse(dto);
    }

    @Transactional
    public UpdateLessonResponse updateLeg(final Integer courseId, final Integer lessonId, final UpdateLessonRequest request){
        final Lesson lesson = findLegById(lessonId, courseId);

        mapper.toLeg(request, lesson);
        lessonDao.save(lesson);

        final LessonDto dto = mapper.toLegDto(lesson);
        return new UpdateLessonResponse(dto);
    }

    private Course findCourseById(final Integer id){
        Course course = courseDao.findOne(id);
        if (course == null){
            throw new NotFoundException(BusinessError.COURSE_NOT_FOUND);
        }
        return course;
    }

    private Lesson findLegById(final Integer id, final Integer courseId){
        Course course = findCourseById(courseId);

        Lesson lesson = lessonDao.findOne(id);
        if (lesson == null || !lesson.getCourseId().equals(course.getId())){
            throw new NotFoundException(BusinessError.LESSON_NOT_FOUND);
        }
        return lesson;
    }
}
