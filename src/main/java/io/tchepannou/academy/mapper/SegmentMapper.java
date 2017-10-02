package io.tchepannou.academy.mapper;

import io.tchepannou.academy.domain.Segment;
import io.tchepannou.academy.domain.SegmentType;
import io.tchepannou.academy.dto.segment.SegmentDto;
import org.springframework.stereotype.Component;

@Component
public class SegmentMapper {
    public SegmentDto toSegmentDto(Segment segment, SegmentType type){
        final SegmentDto dto = toSegmentSummaryDto(segment, type);
        dto.setSummary(segment.getSummary());
        dto.setDescription(segment.getDescription());
        dto.setVideoId(segment.getVideoId());
        dto.setQuizId(segment.getQuizId());
        return dto;
    }

    public SegmentDto toSegmentSummaryDto(Segment segment, SegmentType type){
        final SegmentDto dto = new SegmentDto();
        dto.setId(segment.getId());
        dto.setLessonId(segment.getLessonId());
        dto.setRank(segment.getRank());
        dto.setTitle(segment.getTitle());
        dto.setType(type.getName());
        dto.setDurationSecond(segment.getDurationSecond());
        return dto;
    }
}
