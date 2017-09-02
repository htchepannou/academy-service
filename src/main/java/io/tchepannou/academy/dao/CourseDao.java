package io.tchepannou.academy.dao;

import io.tchepannou.academy.domain.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDao extends CrudRepository<Course, Integer>{
}
