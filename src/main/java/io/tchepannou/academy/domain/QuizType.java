package io.tchepannou.academy.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "T_QUIZ_TYPE")
public class QuizType extends PersistentEnum {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Override
    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

}
