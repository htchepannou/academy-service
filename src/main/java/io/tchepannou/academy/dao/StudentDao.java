package io.tchepannou.academy.dao;

import io.tchepannou.academy.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends CrudRepository<Student, Integer>{
    Student findByCourseIdAndRoleId(Integer courseId, Integer roleId);
}
