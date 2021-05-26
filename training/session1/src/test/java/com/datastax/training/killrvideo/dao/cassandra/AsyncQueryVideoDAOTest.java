package com.datastax.training.killrvideo.dao.cassandra;

import com.datastax.driver.dse.DseSession;
import com.datastax.driver.core.exceptions.InvalidQueryException;
import com.datastax.training.killrvideo.model.Video;
import com.datastax.training.killrvideo.model.VideoAlreadyExistsException;
import com.datastax.training.killrvideo.model.VideoByTag;
import com.datastax.training.killrvideo.model.dao.cassandra.CassandraVideoDAO;
import com.datastax.training.killrvideo.testutilities.AbstractVideoTest;
import com.datastax.training.killrvideo.util.CassandraSession;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;


public class AsyncQueryVideoDAOTest extends AbstractVideoTest {
    @Test
    public void testGetVideoByTags() throws VideoAlreadyExistsException {

        CassandraVideoDAO videoDAO = new CassandraVideoDAO();

        final int N_VIDEOS = 3;
        Video[] videos = new Video[N_VIDEOS];
        for (int i = 0; i < N_VIDEOS; i++) {
            Video video = createVideo();
            video.setTitle(video.getTitle()+i);

            HashSet<String> tags = new HashSet<>();
            for (int j = 0; j <= i; j++) {
                tags.add("tag"+j);
            }
            video.setTags(tags);

            videoDAO.addVideo(video);

            videos[i] = video;
        }

        for (int i = 0; i < N_VIDEOS; i++) {
            HashSet<String> tags = new HashSet();
            tags.add("tag"+(N_VIDEOS-i-1));
            Collection<VideoByTag> results = videoDAO.getVideosByTags(tags);
            assertEquals(i+1, results.size());
            for (int j = N_VIDEOS - 1; j >= N_VIDEOS - i - 1; j--) {
                Video current = videos[j];
                assert(results.stream().anyMatch(videoByTag -> videoByTag.getVideoId().equals(current.getVideoId())));
            }
        }
    }
}
