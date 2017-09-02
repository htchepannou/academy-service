package io.tchepannou.academy.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "T_LEG")
public class Leg extends CourseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Leg() {
    }

    public Leg(final Course course) {
        super(course);
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }
}
