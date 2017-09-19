package io.tchepannou.academy.service;

import io.tchepannou.academy.dao.CourseAttendanceDao;
import io.tchepannou.academy.dao.LessonDao;
import io.tchepannou.academy.dao.SegmentAttendanceDao;
import io.tchepannou.academy.dao.SegmentDao;
import io.tchepannou.academy.domain.CourseAttendance;
import io.tchepannou.academy.domain.Lesson;
import io.tchepannou.academy.domain.Segment;
import io.tchepannou.academy.domain.SegmentAttendance;
import io.tchepannou.academy.dto.attendance.AttendanceResponse;
import io.tchepannou.academy.dto.attendance.CourseAttendanceDto;
import io.tchepannou.academy.exception.BusinessError;
import io.tchepannou.academy.exception.BusinessException;
import io.tchepannou.academy.exception.NotFoundException;
import io.tchepannou.academy.mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AttendanceService {
    @Autowired
    private SegmentDao segmentDao;

    @Autowired
    private LessonDao lessonDao;

    @Autowired
    private CourseAttendanceDao courseAttendanceDao;

    @Autowired
    private SegmentAttendanceDao segmentAttendanceDao;

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Transactional
    public AttendanceResponse start(final Integer studentId, final Integer segmentId){
        final Integer courseId = getCourseId(segmentId);
        CourseAttendance attendance = courseAttendanceDao.findByStudentIdAndCourseId(studentId, courseId);
        if (attendance == null){
            attendance = createCourseAttendance(studentId, segmentId, courseId);
            courseAttendanceDao.save(attendance);
        }
        attendance.setCurrentSegmentId(segmentId);
        courseAttendanceDao.save(attendance);


        final AttendanceResponse response = new AttendanceResponse();
        final CourseAttendanceDto attendanceDto = attendanceMapper.toCourseAttendanceDto(attendance);
        response.setAttendance(attendanceDto);
        return response;
    }

    @Transactional
    public AttendanceResponse done(final Integer studentId, final Integer segmentId){
        final Integer courseId = getCourseId(segmentId);
        SegmentAttendance segmentAttendance;
        CourseAttendance attendance = courseAttendanceDao.findByStudentIdAndCourseId(studentId, courseId);
        if (attendance == null){
            attendance = createCourseAttendance(studentId, segmentId, courseId);
            courseAttendanceDao.save(attendance);
        } else {
            attendance.setCurrentSegmentId(segmentId);
            courseAttendanceDao.save(attendance);

        }

        segmentAttendance = segmentAttendanceDao.findByCourseAttendanceIdAndSegmentId(attendance.getId(), segmentId);
        if (segmentAttendance == null){
            segmentAttendance = createSegmentAttendance(segmentId, attendance);
            segmentAttendanceDao.save(segmentAttendance);
        }

        final AttendanceResponse response = new AttendanceResponse();
        final CourseAttendanceDto attendanceDto = attendanceMapper.toCourseAttendanceDto(attendance);
        response.setAttendance(attendanceDto);
        return response;
    }

    public AttendanceResponse findByStudentAndCourse(final Integer studentId, final Integer courseId) {
        final CourseAttendance attendance = courseAttendanceDao.findByStudentIdAndCourseId(studentId, courseId);
        if (attendance == null){
            throw new NotFoundException(BusinessError.ATTENDANCE_NOT_FOUND);
        }

        final List<SegmentAttendance> segments = segmentAttendanceDao.findByCourseAttendanceId(attendance.getId());

        final AttendanceResponse response = new AttendanceResponse();
        final CourseAttendanceDto attendanceDto = attendanceMapper.toCourseAttendanceDto(attendance);
        attendanceDto.setSegments(
                segments.stream()
                    .map(s -> attendanceMapper.toSegmentAttendanceDto(s))
                    .collect(Collectors.toList())
        );
        response.setAttendance(attendanceDto);
        return response;
    }

    private CourseAttendance createCourseAttendance (Integer studentId, Integer segmentId, Integer courseId){
        final CourseAttendance attendance = new CourseAttendance();
        attendance.setCourseId(courseId);
        attendance.setCurrentSegmentId(segmentId);
        attendance.setAttendanceDateTime(now());
        attendance.setStudentId(studentId);

        return attendance;
    }

    private SegmentAttendance createSegmentAttendance(final Integer segmentId, final CourseAttendance attendance) {
        final SegmentAttendance segmentAttendance;
        segmentAttendance = new SegmentAttendance();
        segmentAttendance.setCourseAttendanceId(attendance.getId());
        segmentAttendance.setSegmentId(segmentId);
        segmentAttendance.setAttendanceDateTime(now());
        return segmentAttendance;
    }


    private Integer getCourseId(Integer segmentId){
        final Segment segment = segmentDao.findOne(segmentId);
        if (segment == null){
            throw new BusinessException(BusinessError.SEGMENT_NOT_FOUND);
        }

        final Lesson lesson = lessonDao.findOne(segment.getLessonId());
        if (lesson == null){
            throw new BusinessException(BusinessError.LESSON_NOT_FOUND);
        }

        return lesson.getCourseId();
    }

    private Timestamp now (){
        return new Timestamp(System.currentTimeMillis());
    }
}
