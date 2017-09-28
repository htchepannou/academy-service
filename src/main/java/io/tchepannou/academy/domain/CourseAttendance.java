package io.tchepannou.academy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table( name = "T_COURSE_ATTENDANCE")
public class CourseAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="student_fk")
    private Integer studentId;

    @Column(name="course_fk")
    private Integer courseId;

    @Column(name="current_segment_fk")
    private Integer currentSegmentId;

    @Column(name="attendance_datetime")
    private Timestamp attendanceDateTime;

    @Column(name="course_segment_count")
    private Integer courseSegmentCount;

    @Column(name="attended_segment_count")
    private Integer attendedSegmentCount;


    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(final Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(final Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getCurrentSegmentId() {
        return currentSegmentId;
    }

    public void setCurrentSegmentId(final Integer currentSegmentId) {
        this.currentSegmentId = currentSegmentId;
    }

    public Timestamp getAttendanceDateTime() {
        return attendanceDateTime;
    }

    public void setAttendanceDateTime(final Timestamp attendanceDateTime) {
        this.attendanceDateTime = attendanceDateTime;
    }

    public Integer getCourseSegmentCount() {
        return courseSegmentCount;
    }

    public void setCourseSegmentCount(final Integer courseSegmentCount) {
        this.courseSegmentCount = courseSegmentCount;
    }

    public Integer getAttendedSegmentCount() {
        return attendedSegmentCount;
    }

    public void setAttendedSegmentCount(final Integer attendedSegmentCount) {
        this.attendedSegmentCount = attendedSegmentCount;
    }
}
