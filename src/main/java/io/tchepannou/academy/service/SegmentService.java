package io.tchepannou.academy.service;

import io.tchepannou.academy.dao.CourseDao;
import io.tchepannou.academy.dao.SegmentDao;
import io.tchepannou.academy.dao.SegmentTypeDao;
import io.tchepannou.academy.domain.Course;
import io.tchepannou.academy.domain.Segment;
import io.tchepannou.academy.domain.SegmentType;
import io.tchepannou.academy.dto.segment.SegmentDto;
import io.tchepannou.academy.dto.segment.SegmentResponse;
import io.tchepannou.academy.exception.BusinessError;
import io.tchepannou.academy.exception.NotFoundException;
import io.tchepannou.academy.mapper.SegmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SegmentService {
    @Autowired
    private SegmentDao segmentDao;

    @Autowired SegmentTypeDao segmentTypeDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private SegmentMapper mapper;


    public SegmentResponse findById (Integer courseId, Integer segmentId){
        final Segment segment = findSegmentById(segmentId, courseId);
        final SegmentType type = segmentTypeDao.findOne(segment.getTypeId());
        final SegmentDto dto = mapper.toSegmentDto(segment, type);
        return new SegmentResponse(dto);
    }

    private Segment findSegmentById(final Integer segmentId, final Integer courseId){
        final Course course = findCourseById(courseId);

        final Segment segment = segmentDao.findOne(segmentId);
        if (segment == null || !segment.getCourseId().equals(course.getId())){
            throw new NotFoundException(BusinessError.SEGMENT_NOT_FOUND);
        }
        return segment;
    }

    private Course findCourseById(final Integer id){
        Course course = courseDao.findOne(id);
        if (course == null){
            throw new NotFoundException(BusinessError.COURSE_NOT_FOUND);
        }
        return course;
    }


}
