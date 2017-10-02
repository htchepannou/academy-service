package io.tchepannou.academy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "T_INSTRUCTOR")
public class Instructor extends Persistent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="course_fk")
    private Integer courseId;

    @Column(name="role_fk")
    private Integer roleId;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(final Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(final Integer roleId) {
        this.roleId = roleId;
    }
}
