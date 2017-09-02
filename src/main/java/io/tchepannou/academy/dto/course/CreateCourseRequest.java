package io.tchepannou.academy.dto.course;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class CreateCourseRequest {
    @NotNull
    @Length(min=1, max = 100)
    private String title;

    @Length(max = 255)
    private String summary;

    private String description;

    @Length(max = 2)
    private String language;

    @NotNull
    private String level;

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

    public String getLevel() {
        return level;
    }

    public void setLevel(final String level) {
        this.level = level;
    }
}
