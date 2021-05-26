package com.datastax.training.killrvideo.model.dao;

import com.datastax.training.killrvideo.model.*;

import java.util.Collection;
import java.util.UUID;

/**
 * Created on 17/10/2015.
 */
public interface VideoDAO {

    void addVideo(Video newVideo) throws VideoAlreadyExistsException;

    UUID deleteVideo(UUID videoId);

    Video getVideo(UUID videoId);

    Iterable<LatestVideo> getLatestVideos();

    Iterable<VideoByUser> getUserVideos(UUID userId);

    Iterable<VideoByTag> getVideosByTag(String tag);

    Collection<VideoByTag> getVideosByTags(Collection<String> tags);

    boolean addTagToVideo(UUID videoID, String tag);

    boolean removeTagFromVideo(UUID videoId, String tag);

    boolean updateAvgRating(UUID videoID, double avgRating);

    Iterable<VideoByGenre> getVideosByGenre(String genre, Integer mindate, Integer maxdate);

}
