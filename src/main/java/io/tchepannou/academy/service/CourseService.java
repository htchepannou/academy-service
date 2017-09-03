package io.tchepannou.academy.service;

import io.tchepannou.academy.dao.CourseDao;
import io.tchepannou.academy.dao.CourseLevelDao;
import io.tchepannou.academy.dao.CourseStatusDao;
import io.tchepannou.academy.dao.LessonDao;
import io.tchepannou.academy.dao.SegmentDao;
import io.tchepannou.academy.dao.SegmentTypeDao;
import io.tchepannou.academy.domain.Course;
import io.tchepannou.academy.domain.CourseLevel;
import io.tchepannou.academy.domain.CourseStatus;
import io.tchepannou.academy.domain.Lesson;
import io.tchepannou.academy.domain.Segment;
import io.tchepannou.academy.domain.SegmentType;
import io.tchepannou.academy.dto.course.CourseDto;
import io.tchepannou.academy.dto.course.CourseResponse;
import io.tchepannou.academy.dto.course.CreateCourseRequest;
import io.tchepannou.academy.dto.course.CreateCourseResponse;
import io.tchepannou.academy.dto.course.UpdateCourseRequest;
import io.tchepannou.academy.dto.course.UpdateCourseResponse;
import io.tchepannou.academy.dto.course.UpdateCourseStatusRequest;
import io.tchepannou.academy.dto.course.UpdateCourseStatusResponse;
import io.tchepannou.academy.dto.lesson.LessonDto;
import io.tchepannou.academy.dto.segment.SegmentDto;
import io.tchepannou.academy.exception.BusinessError;
import io.tchepannou.academy.exception.InvalidRequestException;
import io.tchepannou.academy.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CourseService {
    @Autowired
    private CourseStatusDao statusDao;

    @Autowired
    private CourseLevelDao levelDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private LessonDao legDao;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private LessonMapper legMapper;

    @Autowired
    private SegmentDao segmentDao;

    @Autowired
    private SegmentTypeDao segmentTypeDao;

    @Autowired
    private SegmentMapper segmentMapper;


    //-- Public
    @Transactional
    public CreateCourseResponse create(CreateCourseRequest request){
        final CourseStatus status = statusDao.findByRank(0);
        final CourseLevel level = getLevel(request.getLevel());
        final Course course = new Course();
        courseMapper.toCourse(request, course, level, status);
        courseDao.save(course);

        final CourseDto dto = courseMapper.toCourseDto(course, level, status);
        return new CreateCourseResponse(dto);
    }

    @Transactional
    public UpdateCourseResponse update(final Integer id, UpdateCourseRequest request){
        final Course course = courseDao.findOne(id);
        if (course == null){
            throw new NotFoundException(BusinessError.COURSE_NOT_FOUND);
        }

        final CourseStatus status = statusDao.findOne(course.getStatusId());
        final CourseLevel level = getLevel(request.getLevel());
        courseMapper.toCourse(request, course, level, status);
        courseDao.save(course);

        final CourseDto dto = courseMapper.toCourseDto(course, level, status);
        return new UpdateCourseResponse(dto);
    }

    @Transactional
    public UpdateCourseStatusResponse status(Integer id, UpdateCourseStatusRequest request){
        final Course course = courseDao.findOne(id);
        if (course == null){
            throw new NotFoundException(BusinessError.COURSE_NOT_FOUND);
        }

        final CourseStatus status = getStatus(request.getStatus());
        if (!status.getId().equals(course.getStatusId())) {
            course.setStatusId(status.getId());

            if (status.isPublished()){
                Timestamp now = new Timestamp(System.currentTimeMillis());
                if (course.getPublishedDateTime() == null){
                    course.setPublishedDateTime(now);
                }
                course.setUpdateDateTime(now);
            }
        }

        final UpdateCourseStatusResponse response = new UpdateCourseStatusResponse();
        return response;
    }

    public CourseResponse findById(final Integer id){
        final Course course = courseDao.findOne(id);
        if (course == null){
            throw new NotFoundException(BusinessError.COURSE_NOT_FOUND);
        }

        /* course */
        final CourseStatus status = statusDao.findOne(course.getStatusId());
        final CourseLevel level = levelDao.findOne(course.getLevelId());
        final CourseDto dto = courseMapper.toCourseDto(course, level, status);

        /* legs */
        final PageRequest pageRequest = new PageRequest(0, Integer.MAX_VALUE, Sort.Direction.ASC, "rank");
        final List<Lesson> legs = legDao.findByCourseId(id, pageRequest);
        final List<LessonDto> legDtos = legs.stream()
                .map(leg -> legMapper.toLegDto(leg))
                .collect(Collectors.toList());
        dto.setLessons(legDtos);

        /* Segments */
        final Map<Integer, LessonDto> legDtoMap = legDtos.stream()
                .collect(Collectors.toMap(LessonDto::getId, leg -> leg));
        final List<Segment> segments = segmentDao.findByCourseId(id, pageRequest);
        for (final Segment segment : segments){
            final LessonDto legDto = legDtoMap.get(segment.getLessonId());
            final SegmentType segmentType = segmentTypeDao.findOne(segment.getTypeId());
            final SegmentDto segmentDto = segmentMapper.toSegmentDto(segment, segmentType);
            legDto.add(segmentDto);
        }

        return new CourseResponse(dto);
    }


    //-- Private
    private CourseLevel getLevel(final String name){
        final CourseLevel level = levelDao.findByNameIgnoreCase(name);
        if (level == null){
            throw new InvalidRequestException(BusinessError.BAD_COURSE_LEVEL);
        }
        return level;
    }

    private CourseStatus getStatus(final String name){
        final CourseStatus level = statusDao.findByNameIgnoreCase(name);
        if (level == null){
            throw new InvalidRequestException(BusinessError.BAD_COURSE_STATUS);
        }
        return level;
    }

}
