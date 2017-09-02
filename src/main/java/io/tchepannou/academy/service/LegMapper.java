package io.tchepannou.academy.service;

import io.tchepannou.academy.domain.Leg;
import io.tchepannou.academy.dto.leg.CreateLegRequest;
import io.tchepannou.academy.dto.leg.LegDto;
import org.springframework.stereotype.Component;

@Component
public class LegMapper {
    public Leg toLeg (final CreateLegRequest request, final Leg leg){
        leg.setTitle(request.getTitle());
        leg.setRank(request.getRank());
        return leg;
    }

    public LegDto toLegDto (final Leg leg){
        final LegDto dto = new LegDto();
        dto.setId(leg.getId());
        dto.setRank(leg.getRank());
        dto.setTitle(leg.getTitle());
        return dto;
    }
}
