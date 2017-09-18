package io.tchepannou.academy.dao;

import io.tchepannou.academy.domain.CourseAttendance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseAttendanceDao extends CrudRepository<CourseAttendance, Integer>{
    CourseAttendance findByStudentIdAndCourseId (Integer studentId, Integer courseId);
}
