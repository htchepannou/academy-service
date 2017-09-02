package io.tchepannou.academy.service;

import io.tchepannou.academy.domain.Course;
import io.tchepannou.academy.dto.course.SearchCourseRequest;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

@Component
public class SearchCourseQueryBuilder {
    @PersistenceContext
    private EntityManager em;

    public CriteriaQuery<Course> createQuery(final SearchCourseRequest request){
        return null;
    }
}
