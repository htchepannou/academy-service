package io.tchepannou.academy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "T_QUIZ")
public class Quiz extends Persistent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="type_fk")
    private Integer typeId;

    @Column(name="video_fk")
    private Integer videoId;

    private String question;
    private String description;
    private String answer;

    @Column(name="success_message")
    private String successMessage;

    @Column(name="failure_message")
    private String failureMessage;

    @Column(name="sql_init_script")
    private String sqlInitScript;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(final String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(final String answer) {
        this.answer = answer;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(final String successMessage) {
        this.successMessage = successMessage;
    }

    public String getFailureMessage() {
        return failureMessage;
    }

    public void setFailureMessage(final String failureMessage) {
        this.failureMessage = failureMessage;
    }

    public String getSqlInitScript() {
        return sqlInitScript;
    }

    public void setSqlInitScript(final String sqlInitScript) {
        this.sqlInitScript = sqlInitScript;
    }
}
