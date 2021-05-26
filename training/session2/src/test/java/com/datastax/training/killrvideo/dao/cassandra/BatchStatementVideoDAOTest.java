package com.datastax.training.killrvideo.dao.cassandra;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.InvalidQueryException;
import com.datastax.training.killrvideo.model.LatestVideo;
import com.datastax.training.killrvideo.model.Video;
import com.datastax.training.killrvideo.model.VideoAlreadyExistsException;
import com.datastax.training.killrvideo.model.dao.cassandra.CassandraVideoDAO;
import com.datastax.training.killrvideo.testutilities.AbstractVideoTest;
import com.datastax.training.killrvideo.util.CassandraSession;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class BatchStatementVideoDAOTest extends AbstractVideoTest {

    @Test
    public void testBatchInsert() throws VideoAlreadyExistsException {

        Video originalVideo = createVideo();
        CassandraVideoDAO videoDAO = new CassandraVideoDAO();
        Session session = CassandraSession.getSession();

        //TODO: Add code here


        //TODO: Your code ends here
    }
}