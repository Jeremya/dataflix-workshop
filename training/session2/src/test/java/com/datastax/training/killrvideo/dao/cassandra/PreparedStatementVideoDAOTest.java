package com.datastax.training.killrvideo.dao.cassandra;

import com.datastax.training.killrvideo.model.Video;
import com.datastax.training.killrvideo.model.VideoAlreadyExistsException;
import com.datastax.training.killrvideo.model.dao.cassandra.CassandraVideoDAO;
import com.datastax.training.killrvideo.testutilities.AbstractVideoTest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PreparedStatementVideoDAOTest extends AbstractVideoTest {

    @Test
    public void testAddVideo() throws VideoAlreadyExistsException {

        Video originalVideo = createVideo();

        CassandraVideoDAO videoDAO = new CassandraVideoDAO();
        videoDAO.addVideo(originalVideo);

    }

}
