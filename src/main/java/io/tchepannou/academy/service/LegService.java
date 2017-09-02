package io.tchepannou.academy.service;

import io.tchepannou.academy.dao.CourseDao;
import io.tchepannou.academy.dao.LegDao;
import io.tchepannou.academy.domain.Course;
import io.tchepannou.academy.domain.Leg;
import io.tchepannou.academy.dto.leg.CreateLegRequest;
import io.tchepannou.academy.dto.leg.CreateLegResponse;
import io.tchepannou.academy.dto.leg.LegDto;
import io.tchepannou.academy.dto.leg.UpdateLegRequest;
import io.tchepannou.academy.dto.leg.UpdateLegResponse;
import io.tchepannou.academy.exception.BusinessError;
import io.tchepannou.academy.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class LegService {
    @Autowired
    private LegDao legDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private LegMapper mapper;

    @Transactional
    public CreateLegResponse addLeg(final Integer courseId, final CreateLegRequest request){
        final Course course = findCourseById(courseId);

        final Leg leg = new Leg(course);
        mapper.toLeg(request, leg);
        legDao.save(leg);

        final LegDto dto = mapper.toLegDto(leg);
        return new CreateLegResponse(dto);
    }

    @Transactional
    public UpdateLegResponse updateLeg(final Integer courseId, final Integer legId, final UpdateLegRequest request){
        final Leg leg = findLegById(legId, courseId);

        mapper.toLeg(request, leg);
        legDao.save(leg);

        final LegDto dto = mapper.toLegDto(leg);
        return new UpdateLegResponse(dto);
    }

    private Course findCourseById(final Integer id){
        Course course = courseDao.findOne(id);
        if (course == null){
            throw new NotFoundException(BusinessError.COURSE_NOT_FOUND);
        }
        return course;
    }

    private Leg findLegById(final Integer id, final Integer courseId){
        Course course = findCourseById(courseId);

        Leg leg = legDao.findOne(id);
        if (leg == null || !leg.getCourseId().equals(course.getId())){
            throw new NotFoundException(BusinessError.LEG_NOT_FOUND);
        }
        return leg;
    }
}
