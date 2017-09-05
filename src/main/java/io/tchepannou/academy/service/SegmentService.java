package io.tchepannou.academy.service;

import io.tchepannou.academy.dao.CourseDao;
import io.tchepannou.academy.dao.LessonDao;
import io.tchepannou.academy.dao.SegmentDao;
import io.tchepannou.academy.dao.SegmentTypeDao;
import io.tchepannou.academy.domain.Lesson;
import io.tchepannou.academy.domain.Segment;
import io.tchepannou.academy.domain.SegmentType;
import io.tchepannou.academy.dto.segment.SegmentDto;
import io.tchepannou.academy.dto.segment.SegmentListResponse;
import io.tchepannou.academy.dto.segment.SegmentResponse;
import io.tchepannou.academy.exception.BusinessError;
import io.tchepannou.academy.exception.NotFoundException;
import io.tchepannou.academy.mapper.SegmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SegmentService {
    @Autowired
    private LessonDao lessonDao;

    @Autowired
    private SegmentTypeDao segmentTypeDao;

    @Autowired
    private SegmentDao segmentDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private SegmentMapper mapper;


    public SegmentResponse findById (Integer courseId, Integer segmentId){
        final Segment segment = segmentDao.findOne(segmentId);
        if (segment == null){
            throw new NotFoundException(BusinessError.SEGMENT_NOT_FOUND);
        }

        final Lesson lesson = lessonDao.findOne(segment.getLessonId());
        if (!lesson.getCourseId().equals(courseId)){
            throw new NotFoundException(BusinessError.SEGMENT_NOT_FOUND);
        }

        final SegmentType type = segmentTypeDao.findOne(segment.getTypeId());
        final SegmentDto dto = mapper.toSegmentDto(segment, type);
        return new SegmentResponse(dto);
    }

    public SegmentListResponse findByLessonId(Integer courseId, Integer lessonId){
        Lesson lesson = lessonDao.findOne(lessonId);
        if (lesson == null || !lesson.getCourseId().equals(courseId)){
            throw new NotFoundException(BusinessError.LESSON_NOT_FOUND);
        }

        final PageRequest pageable = new PageRequest(0, Integer.MAX_VALUE, Sort.Direction.ASC, "rank");
        final List<Segment> segments = segmentDao.findByLessonId(lesson.getId(), pageable);

        final SegmentListResponse response = new SegmentListResponse();
        for (final Segment segment : segments){
            final SegmentType type = segmentTypeDao.findOne(segment.getTypeId());
            final SegmentDto dto = mapper.toSegmentSummaryDto(segment, type);
            response.add(dto);
        }
        return response;
    }

}
