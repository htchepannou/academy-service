package io.tchepannou.academy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table( name = "T_COURSE")
public class Course extends Persistent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
    private String summary;
    private String description;
    private String language;

    @Column(name="level_fk")
    private Integer levelId;

    @Column(name="status_fk")
    private Integer statusId;

    @Column(name="published_datetime")
    private Timestamp publishedDateTime;

    @Column(name="updated_datetime")
    private Timestamp updateDateTime;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(final Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(final Integer statusId) {
        this.statusId = statusId;
    }

    public Timestamp getPublishedDateTime() {
        return publishedDateTime;
    }

    public void setPublishedDateTime(final Timestamp publishedDateTime) {
        this.publishedDateTime = publishedDateTime;
    }

    public Timestamp getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(final Timestamp updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
