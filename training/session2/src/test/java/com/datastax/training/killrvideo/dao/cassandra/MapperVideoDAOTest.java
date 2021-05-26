package com.datastax.training.killrvideo.dao.cassandra;

import com.datastax.driver.dse.DseSession;
import com.datastax.driver.core.exceptions.InvalidQueryException;
import com.datastax.training.killrvideo.model.Video;
import com.datastax.training.killrvideo.model.VideoAlreadyExistsException;
import com.datastax.training.killrvideo.model.dao.cassandra.CassandraVideoDAO;
import com.datastax.training.killrvideo.testutilities.AbstractVideoTest;
import com.datastax.training.killrvideo.util.CassandraSession;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class MapperVideoDAOTest extends AbstractVideoTest {

    @Test
    public void testMapperGetVideo() throws VideoAlreadyExistsException {

        Video originalVideo = createVideo();
        CassandraVideoDAO videoDAO = new CassandraVideoDAO();
        videoDAO.addVideo(originalVideo);

        Video retrievedVideo = getVideo(originalVideo.getVideoId());
        assertEquals(originalVideo, retrievedVideo);
    }

}
