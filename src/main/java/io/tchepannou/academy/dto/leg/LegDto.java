package io.tchepannou.academy.dto.leg;

import io.tchepannou.academy.dto.segment.SegmentDto;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CPD-START")
public class LegDto {
    private Integer id;
    private String title;
    private Integer rank;
    private List<SegmentDto> segments;

    public void add(final SegmentDto segment) {
        if (segments == null){
            segments = new ArrayList<>();
        }
        segments.add(segment);
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
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

    public List<SegmentDto> getSegments() {
        return segments;
    }

    public void setSegments(final List<SegmentDto> segments) {
        this.segments = segments;
    }

}
