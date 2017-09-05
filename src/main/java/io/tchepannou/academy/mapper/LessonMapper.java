package io.tchepannou.academy.mapper;

import io.tchepannou.academy.domain.Lesson;
import io.tchepannou.academy.dto.lesson.LessonDto;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {
    public LessonDto toLessonDto (final Lesson leg){
        final LessonDto dto = new LessonDto();
        dto.setId(leg.getId());
        dto.setRank(leg.getRank());
        dto.setTitle(leg.getTitle());
        return dto;
    }
}
