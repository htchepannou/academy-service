package io.tchepannou.academy.service;

import io.tchepannou.academy.domain.Video;
import io.tchepannou.academy.domain.VideoType;
import io.tchepannou.academy.dto.video.VideoDto;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper {
    public VideoDto toVideoDto(final Video video, final VideoType videoType){
        final VideoDto dto = new VideoDto();
        dto.setDurationSecond(video.getDurationSecond());
        dto.setId(video.getId());
        dto.setType(videoType.getName());
        dto.setVideoId(video.getVideoId());
        return dto;
    }
}
