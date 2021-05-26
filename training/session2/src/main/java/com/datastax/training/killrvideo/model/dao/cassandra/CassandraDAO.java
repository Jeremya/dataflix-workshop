package com.datastax.training.killrvideo.model.dao.cassandra;

import com.datastax.training.killrvideo.util.CassandraSession;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.dse.DseSession;

/**
 * DataStax Academy Sample Application
 * <p>
 * Copyright 2019 DataStax
 */

// This class provides convenience methods for the other DAO Classes

public class CassandraDAO {

    protected PreparedStatement prepare(String query) {
        return getCassandraSession().prepare(query);
    }

    protected ResultSet execute(BoundStatement bound) {
        return getCassandraSession().execute(bound);
    }

    protected DseSession getCassandraSession() {
        return CassandraSession.getSession();
    }
}
