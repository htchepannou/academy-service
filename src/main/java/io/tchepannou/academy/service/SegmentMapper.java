package io.tchepannou.academy.service;

import io.tchepannou.academy.domain.Segment;
import io.tchepannou.academy.domain.SegmentType;
import io.tchepannou.academy.dto.segment.SegmentDto;
import org.springframework.stereotype.Component;

@Component
public class SegmentMapper {
    public SegmentDto toSegmentDto(Segment segment, SegmentType type){
        final SegmentDto dto = new SegmentDto();
        dto.setId(segment.getId());
        dto.setVideoId(segment.getVideoId());
        dto.setRank(segment.getRank());
        dto.setSummary(segment.getSummary());
        dto.setTitle(segment.getTitle());
        dto.setType(type.getName());
        return dto;
    }
}
