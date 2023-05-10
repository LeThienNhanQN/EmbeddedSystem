package com.ldnhat.embeddedserver.api.user;

import com.datastax.astra.sdk.AstraClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/test")
@RequiredArgsConstructor
public class UserAstraController {
    private final CassandraTemplate cassandraTemplate;
    private final AstraClient astraClient;

    @GetMapping("/ping")
    public String ping() {
        return astraClient.apiDevopsOrganizations()
                .organizationId();
    }

    @GetMapping("/datacenter")
    public String datacenter() {
        return cassandraTemplate
                .getCqlOperations()
                .queryForObject("SELECT data_center FROM system.local", String.class);
    }
}
