package io.tchepannou.academy.dto.segment;

@SuppressWarnings("CPD-START")
public class SegmentDto {
    private Integer id;
    private Integer videoId;
    private Integer quizId;
    private String type;
    private String title;
    private Integer rank;
    private String summary;
    private String description;
    private Integer durationSecond;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(final Integer quizId) {
        this.quizId = quizId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(final Integer videoId) {
        this.videoId = videoId;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
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

    public Integer getDurationSecond() {
        return durationSecond;
    }

    public void setDurationSecond(final Integer durationSecond) {
        this.durationSecond = durationSecond;
    }
}
