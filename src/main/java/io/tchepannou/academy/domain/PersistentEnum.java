package io.tchepannou.academy.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PersistentEnum extends Persistent {
    private String name;
    private Integer rank;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(final Integer rank) {
        this.rank = rank;
    }
}
