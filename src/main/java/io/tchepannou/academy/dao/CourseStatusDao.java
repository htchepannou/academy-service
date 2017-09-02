package io.tchepannou.academy.dao;

import io.tchepannou.academy.domain.CourseStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseStatusDao extends PersistentEnumRepository<CourseStatus> {
}
