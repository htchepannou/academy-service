package io.tchepannou.academy.dto.segment;

import io.tchepannou.academy.dto.BaseResponse;

public class SegmentResponse extends BaseResponse{
    private SegmentDto segment;

    public SegmentResponse() {
    }

    public SegmentResponse(final SegmentDto segment) {
        this.segment = segment;
    }

    public SegmentDto getSegment() {
        return segment;
    }

    public void setSegment(final SegmentDto segment) {
        this.segment = segment;
    }
}
