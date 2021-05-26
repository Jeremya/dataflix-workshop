package com.datastax.training.killrvideo.dao.cassandra;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.nio.ByteBuffer;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.datastax.driver.core.querybuilder.BuiltStatement;
import com.datastax.driver.mapping.annotations.*;

import java.nio.ByteBuffer;
import java.util.*;

import breeze.optimize.linear.LinearProgram;
import com.datastax.driver.core.*;
import com.datastax.driver.core.policies.RetryPolicy;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.training.killrvideo.model.Address;
import com.datastax.training.killrvideo.model.UserAlreadyExistsException;
import com.datastax.training.killrvideo.testutilities.TestData;
import com.datastax.training.killrvideo.util.CassandraSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.datastax.training.killrvideo.model.User;
import com.datastax.training.killrvideo.model.dao.cassandra.CassandraUserDAO;
import com.datastax.training.killrvideo.testutilities.AbstractDSETest;
import com.datastax.driver.dse.DseSession;
import scala.collection.JavaConversions;


import junit.framework.Assert;

/**
 * Copyright DataStax Academy 2019
 */

@RunWith(JUnit4.class)
public class UserDAOAddressTest extends AbstractDSETest {

    private UserType udtAddress;
    
    @Test
    public void testAddUserAndUpdateAddress() throws UserAlreadyExistsException {
        //TODO: Begin your code here

        //TODO: End your code here
    }
    
    @Test
    public void writeUDT() {
        //TODO: Begin your code here

        //TODO: End your code here
    }
    
    @Test
    public void updateUDT() {
        //TODO: Begin your code here

        //TODO: End your code here
    }
    
    @Test
    public void readUDT() {
        //TODO: Begin your code here

        //TODO: End your code here
    }
    
    @Test
    public void writeList() {
        //TODO: Begin your code here

        //TODO: End your code here
    }
    
    @Test
    public void updateList() {
        //TODO: Begin your code here

        //TODO: End your code here
    }
    
    @Test
    public void readList() {
        //TODO: Begin your code here

        //TODO: End your code here
    }
    
    @Test
    public void writeSet() {
        //TODO: Begin your code here

        //TODO: End your code here
    }
    
    @Test
    public void updateSet() {
        //TODO: Begin your code here

        //TODO: End your code here
    }
    
    @Test
    public void readSet() {
        //TODO: Begin your code here

        //TODO: End your code here
    }
    

    @Test
    public void writeMap() {
        //TODO: Begin your code here

        //TODO: End your code here
    }
    
    @Test
    public void updateMap() {
        //TODO: Begin your code here

        //TODO: End your code here
    }
    
    @Test
    public void readMap() {
    //TODO: Begin your code here

    //TODO: End your code here
    }
    
    @Test
    public void writeCodeExamples() {
    //TODO: Begin your code here

    //TODO: End your code here
    }
}

// }
