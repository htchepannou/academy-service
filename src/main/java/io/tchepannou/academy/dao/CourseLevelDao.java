package io.tchepannou.academy.dao;

import io.tchepannou.academy.domain.CourseLevel;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseLevelDao extends PersistentEnumRepository<CourseLevel> {
}
