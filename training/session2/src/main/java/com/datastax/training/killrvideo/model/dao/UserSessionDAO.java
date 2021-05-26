package com.datastax.training.killrvideo.model.dao;

import java.util.UUID;

import com.datastax.training.killrvideo.model.UserSession;

/**
 * Copyright 2019 DataStax
 */
public interface UserSessionDAO {

    void addSession(UserSession userSession, int ttl);

    UserSession getUserSession(UUID token);

}
