package com.datastax.training.killrvideo.util;

import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.dse.DseCluster;
import com.datastax.driver.dse.DseSession;
import com.datastax.driver.mapping.MappingManager;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * DataStax Academy Sample Application
 * <p>
 * Copyright 2019 DataStax
 */
@Provider
public class SearchSession {

    public final Config config = ConfigFactory.load();
    private static final Logger log = LoggerFactory.getLogger(SearchSession.class);

    private static DseSession session;
    private static MappingManager mappingManager;

    public static DseSession getSession() {
        return session;
    }

    public static MappingManager getMappingManager() {
        return mappingManager;
    }

    public SearchSession() {
        // empty default constructor?
    }

    /**
     * @param username
     * @param password
     * @param dcname
     * @param contactPoints
     */
    public static void initSearchSession(String username, String password, String dcname, String keyspace,
            String... contactPoints) {

        session = DseCluster.builder().addContactPoints(contactPoints).withCredentials(username, password)
                // .withLoadBalancingPolicy(new TokenAwarePolicy(
                // DCAwareRoundRobinPolicy.builder()
                // .withLocalDc(dcname)
                // .build()))
                .build().connect(keyspace);

        mappingManager = new MappingManager(session);

        log.info("Created search session; Connected to: " + session.getCluster().getMetadata().getClusterName());

    }

}
