package com.datastax.training.killrvideo.dao.cassandra;

import com.datastax.training.killrvideo.model.LatestVideo;
import com.datastax.training.killrvideo.model.Video;
import com.datastax.training.killrvideo.model.VideoAlreadyExistsException;
import com.datastax.training.killrvideo.model.dao.cassandra.CassandraVideoDAO;
import com.datastax.training.killrvideo.testutilities.AbstractVideoTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class QueryBuilderVideoDAOTest  extends AbstractVideoTest {

    @Test
    public void testAddLatestVideo() throws VideoAlreadyExistsException {

        Video originalVideo = createVideo();

        CassandraVideoDAO videoDAO = new CassandraVideoDAO();
        videoDAO.addVideo(originalVideo);

        // getVideos
        LatestVideo latestOriginal = new LatestVideo(originalVideo);
        Iterable<LatestVideo> videos = videoDAO.getLatestVideos();
        boolean found = false;
        for (LatestVideo retrievedVideo: videos) {
            if(retrievedVideo.getVideoId().equals(latestOriginal.getVideoId())) {
                assertEquals(latestOriginal, retrievedVideo);
                found = true;
                break;
            }
        }
        assertTrue(found);
    }
}
