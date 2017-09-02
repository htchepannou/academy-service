package io.tchepannou.academy.dao;

import io.tchepannou.academy.domain.SegmentType;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentTypeDao extends PersistentEnumRepository<SegmentType> {
}
