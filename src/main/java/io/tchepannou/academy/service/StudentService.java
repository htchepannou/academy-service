package io.tchepannou.academy.service;

import io.tchepannou.academy.dao.CourseDao;
import io.tchepannou.academy.dao.StudentDao;
import io.tchepannou.academy.dao.StudentSegmentDao;
import io.tchepannou.academy.domain.Course;
import io.tchepannou.academy.domain.Student;
import io.tchepannou.academy.domain.StudentSegment;
import io.tchepannou.academy.dto.student.StudentDto;
import io.tchepannou.academy.dto.student.StudentRequest;
import io.tchepannou.academy.dto.student.StudentResponse;
import io.tchepannou.academy.exception.BusinessError;
import io.tchepannou.academy.exception.NotFoundException;
import io.tchepannou.academy.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StudentService {
    @Autowired
    private CourseDao courseDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private StudentSegmentDao studentSegmentDao;

    @Autowired
    private StudentMapper studentMapper;

    @Transactional
    public StudentResponse updateStudent(final Integer courseId, final StudentRequest request){
        final Course course = courseDao.findOne(courseId);
        if (course == null){
            throw new NotFoundException(BusinessError.COURSE_NOT_FOUND);
        }

        final Integer roleId = request.getRoleId();
        Student student = studentDao.findByCourseIdAndRoleId(course.getId(), roleId);
        if (student == null){
            student = createStudent(roleId, course);
            studentDao.save(student);
        }

        final Integer segmentId = request.getSegmentId();
        StudentSegment segment = studentSegmentDao.findByStudentIdAndSegmentId(student.getId(), segmentId);
        if (segment == null){
            segment = createStudent(segmentId, student);
            studentSegmentDao.save(segment);
        }

        updateStats(course, student);

        final StudentResponse response = new StudentResponse();
        final StudentDto dto = studentMapper.toStudentDto(student);
        response.setStudent(dto);
        return response;
    }

    public StudentResponse findByCourseAndRole(final Integer courseId, final Integer roleId) {
        final Student student = studentDao.findByCourseIdAndRoleId(courseId, roleId);
        if (student == null){
            throw new NotFoundException(BusinessError.STUDENT_NOT_FOUND);
        }

        final List<StudentSegment> segments = studentSegmentDao.findByStudentId(student.getId());
        final StudentResponse response = new StudentResponse();
        final StudentDto attendanceDto = studentMapper.toStudentDto(student);
        attendanceDto.setSegments(
                segments.stream()
                    .map(s -> studentMapper.toStudentDto(s))
                    .collect(Collectors.toList())
        );
        response.setStudent(attendanceDto);
        return response;
    }

    private Student createStudent(Integer roleId, Course course){
        final Student student = new Student();
        student.setCourseId(course.getId());
        student.setRoleId(roleId);
        student.setCourseSegmentCount(course.getSegmentCount());
        return student;
    }

    private void updateStats(final Course course, final Student student){
        student.setCourseSegmentCount(course.getSegmentCount());

        final List<StudentSegment> segments = studentSegmentDao.findByStudentId(student.getId());
        final Set<Integer> segmentIds = segments.stream()
                .map(s -> s.getSegmentId())
                .collect(Collectors.toSet());
        student.setAttendedSegmentCount(segmentIds.size());

        studentDao.save(student);
    }

    private StudentSegment createStudent(final Integer segmentId, final Student attendance) {
        final StudentSegment segmentAttendance;
        segmentAttendance = new StudentSegment();
        segmentAttendance.setStudentId(attendance.getId());
        segmentAttendance.setSegmentId(segmentId);
        return segmentAttendance;
    }
}
