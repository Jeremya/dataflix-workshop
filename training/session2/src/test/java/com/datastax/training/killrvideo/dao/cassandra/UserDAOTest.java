package com.datastax.training.killrvideo.dao.cassandra;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.training.killrvideo.model.UserAlreadyExistsException;
import com.datastax.training.killrvideo.testutilities.TestData;
import com.datastax.training.killrvideo.util.CassandraSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import com.datastax.training.killrvideo.model.User;
import com.datastax.training.killrvideo.model.dao.cassandra.CassandraUserDAO;
import com.datastax.training.killrvideo.testutilities.AbstractDSETest;

/**
 * Copyright 2019 DataStax
 */
@RunWith(JUnit4.class)
public class UserDAOTest extends AbstractDSETest {

    @Test
    public void testAddUser() throws UserAlreadyExistsException {
        CassandraUserDAO userDAO = new CassandraUserDAO();
        User userToAdd = TestData.TEST_USER1;
        userDAO.addUser(userToAdd);

    }




}
