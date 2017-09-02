package io.tchepannou.academy.dto.video;

@SuppressWarnings("CPD-START")
public class VideoDto {
    private Integer id;
    private String type;
    private String videoId;
    private Integer durationSecond;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(final String videoId) {
        this.videoId = videoId;
    }

    public Integer getDurationSecond() {
        return durationSecond;
    }

    public void setDurationSecond(final Integer durationSecond) {
        this.durationSecond = durationSecond;
    }
}
