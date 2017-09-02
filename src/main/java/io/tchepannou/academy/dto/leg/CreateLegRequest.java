package io.tchepannou.academy.dto.leg;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class CreateLegRequest {
    @NotNull
    @Length(min=1, max = 100)
    private String title;

    private Integer rank;

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
}
