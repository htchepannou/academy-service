package io.tchepannou.academy.dto.leg;

import io.tchepannou.academy.dto.BaseResponse;

public class LegResponse extends BaseResponse {
    private LegDto leg;

    public LegResponse() {

    }
    public LegResponse(final LegDto leg) {
        this.leg = leg;
    }

    public LegDto getLeg() {
        return leg;
    }

    public void setLeg(final LegDto leg) {
        this.leg = leg;
    }
}
