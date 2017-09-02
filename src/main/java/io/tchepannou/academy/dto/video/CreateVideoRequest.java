package io.tchepannou.academy.dto.video;

import javax.validation.constraints.NotNull;

public class CreateVideoRequest {
    @NotNull
    private String url;
    private Integer durationSeconds;

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public Integer getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(final Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }
}
