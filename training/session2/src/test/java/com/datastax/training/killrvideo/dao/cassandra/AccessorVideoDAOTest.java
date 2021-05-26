package com.datastax.training.killrvideo.dao.cassandra;

import com.datastax.driver.core.utils.UUIDs;
import com.datastax.training.killrvideo.model.Video;
import com.datastax.training.killrvideo.model.VideoAlreadyExistsException;
import com.datastax.training.killrvideo.model.dao.cassandra.CassandraVideoDAO;
import com.datastax.training.killrvideo.model.VideoByUser;

import com.datastax.training.killrvideo.testutilities.AbstractVideoTest;
import com.datastax.training.killrvideo.testutilities.TestData;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;


public class AccessorVideoDAOTest extends AbstractVideoTest {

    @Test
    public void testAccessorForNoVideos() throws VideoAlreadyExistsException {

        CassandraVideoDAO videoDAO = new CassandraVideoDAO();
        Iterable<VideoByUser> videosByUser = videoDAO.getUserVideos(TestData.TEST_USER1.getUserId());
        assertFalse(videosByUser.iterator().hasNext());
    }

    @Test
    public void testAccessorForSingleVideo() throws VideoAlreadyExistsException {

        Video originalVideo = createVideo();
        CassandraVideoDAO videoDAO = new CassandraVideoDAO();
        videoDAO.addVideo(originalVideo);

        Iterable<VideoByUser> videosByUser = videoDAO.getUserVideos(originalVideo.getUserId());
        assert(videosByUser.iterator().hasNext());
        assertEquals(videosByUser.iterator().next(), getVideoByUserFromVideo(originalVideo));
        assertFalse(videosByUser.iterator().hasNext());
    }

    @Test
    public void testAccessorForMultiVideos() throws VideoAlreadyExistsException {

        Video originalVideo = createVideo();
        CassandraVideoDAO videoDAO = new CassandraVideoDAO();
        videoDAO.addVideo(originalVideo);
        Video secondVideo = createVideo();
        secondVideo.setVideoId(UUIDs.timeBased());
        secondVideo.setTitle(secondVideo.getTitle()+"2");
        secondVideo.setUserId(originalVideo.getUserId());
        videoDAO.addVideo(secondVideo);


        Iterable<VideoByUser> videosByUser = videoDAO.getUserVideos(originalVideo.getUserId());
        int origCount = 0;
        int secondCount = 0;
        for (VideoByUser v : videosByUser) {

            if (v.getVideoId().equals(originalVideo.getVideoId())) {
                assertEquals(getVideoByUserFromVideo(originalVideo), v);
                origCount++;
            } else if (v.getVideoId().equals(secondVideo.getVideoId())) {
                assertEquals(getVideoByUserFromVideo(secondVideo), v);
                secondCount++;
            } else {
                fail();
            }
        }
        assertEquals(1, origCount);
        assertEquals(1, secondCount);
    }

    private VideoByUser getVideoByUserFromVideo(Video video) {
        VideoByUser videoByUser = new VideoByUser();
        videoByUser.setUserId(video.getUserId());
        videoByUser.setVideoId(video.getVideoId());
        videoByUser.setTitle(video.getTitle());
        videoByUser.setType(video.getType());
        if (video.getTags() != null) {
            videoByUser.setTags(new HashSet<>(video.getTags()));
        }
        videoByUser.setPreviewThumbnail(video.getPreviewThumbnail());
        return videoByUser;
    }

}
