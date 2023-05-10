package com.ldnhat.embeddedserver.common.repository;

import com.ldnhat.embeddedserver.common.entity.Data;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends CassandraRepository<Data, String> {

}
