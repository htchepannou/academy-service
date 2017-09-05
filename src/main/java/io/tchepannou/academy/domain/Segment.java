package io.tchepannou.academy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "T_SEGMENT")
public class Segment extends CourseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="lesson_fk")
    private Integer lessonId;

    @Column(name="type_fk")
    private Integer typeId;

    @Column(name="video_fk")
    private Integer videoId;

    private String summary;
    private String description;

    public Segment() {
    }

    public Segment(final Course course) {
        super(course);
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(final Integer lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(final Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(final Integer videoId) {
        this.videoId = videoId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(final String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
