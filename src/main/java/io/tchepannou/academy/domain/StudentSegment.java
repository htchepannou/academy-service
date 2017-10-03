package io.tchepannou.academy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "T_STUDENT_SEGMENT")
public class StudentSegment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="student_fk")
    private Integer studentId;

    @Column(name="segment_fk")
    private Integer segmentId;

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

    public Integer getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(final Integer segmentId) {
        this.segmentId = segmentId;
    }
}
