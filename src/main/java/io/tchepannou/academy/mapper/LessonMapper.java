package io.tchepannou.academy.mapper;

import io.tchepannou.academy.domain.Lesson;
import io.tchepannou.academy.dto.lesson.CreateLessonRequest;
import io.tchepannou.academy.dto.lesson.LessonDto;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {
    public Lesson toLeg (final CreateLessonRequest request, final Lesson leg){
        leg.setTitle(request.getTitle());
        leg.setRank(request.getRank());
        return leg;
    }

    public LessonDto toLegDto (final Lesson leg){
        final LessonDto dto = new LessonDto();
        dto.setId(leg.getId());
        dto.setRank(leg.getRank());
        dto.setTitle(leg.getTitle());
        return dto;
    }
}
