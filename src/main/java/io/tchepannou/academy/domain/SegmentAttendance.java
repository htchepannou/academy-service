package io.tchepannou.academy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table( name = "T_SEGMENT_ATTENDANCE")
public class SegmentAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="course_attendance_fk")
    private Integer courseAttendanceId;

    @Column(name="segment_fk")
    private Integer segmentId;

    @Column(name="attendance_datetime")
    private Date attendanceDateTime;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getCourseAttendanceId() {
        return courseAttendanceId;
    }

    public void setCourseAttendanceId(final Integer courseAttendanceId) {
        this.courseAttendanceId = courseAttendanceId;
    }

    public Integer getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(final Integer segmentId) {
        this.segmentId = segmentId;
    }

    public Date getAttendanceDateTime() {
        return attendanceDateTime;
    }

    public void setAttendanceDateTime(final Date attendanceDateTime) {
        this.attendanceDateTime = attendanceDateTime;
    }
}
