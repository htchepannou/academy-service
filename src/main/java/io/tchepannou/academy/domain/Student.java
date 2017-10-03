package io.tchepannou.academy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "T_STUDENT")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="role_fk")
    private Integer roleId;

    @Column(name="course_fk")
    private Integer courseId;

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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(final Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(final Integer courseId) {
        this.courseId = courseId;
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
