package com.ldnhat.embeddedserver.common.repository;

import com.ldnhat.embeddedserver.common.entity.DataCurrent;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.Optional;

public interface DataCurrentRepository extends CassandraRepository<DataCurrent, String> {
}
