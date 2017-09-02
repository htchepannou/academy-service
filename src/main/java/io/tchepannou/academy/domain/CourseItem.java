package io.tchepannou.academy.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class CourseItem extends Persistent {
    @Column(name="course_fk")
    private Integer courseId;

    private String title;
    private Integer rank;

    public CourseItem(){
    }
    public CourseItem(Course course){
        this.courseId = course.getId();
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(final Integer courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(final Integer rank) {
        this.rank = rank;
    }
}
