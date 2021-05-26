package com.datastax.training.killrvideo.model.dao.cassandra;

import com.datastax.driver.core.*;
import com.datastax.driver.dse.DseSession;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.utils.UUIDs;

import com.datastax.training.killrvideo.model.Video;
import com.datastax.training.killrvideo.model.LatestVideo;
import com.datastax.training.killrvideo.model.VideoAlreadyExistsException;
import com.datastax.training.killrvideo.model.VideoByTag;
import com.datastax.training.killrvideo.model.VideoByUser;
import com.datastax.training.killrvideo.model.VideoByGenre;
import com.datastax.training.killrvideo.model.dao.VideoDAO;

import java.util.*;

/**
 *  Copyright 2019 DataStax
 */
public class CassandraVideoDAO extends AbstractMapperDAO<Video> implements VideoDAO {

   // private final PreparedStatement insertStatement;
   // private final PreparedStatement deleteStatement;
   // private final PreparedStatement deleteVideosByTagStatement;
   // private final PreparedStatement selectByTag;
   // private final PreparedStatement updateAvgRatingStatement;
   // private final PreparedStatement addTagStatement;
   // private final PreparedStatement removeTagStatement;
   // private final PreparedStatement updateVideoStatement;
   private final PreparedStatement selectByGenre;

    private VideoByUserAccessor accessor;

    // Used to set the bucket value for the latest_videos table
    private int currentDate;

    public CassandraVideoDAO() {
        super();
        DseSession session = getCassandraSession();

        // TODO: Prepare your insert statement here


        // TODO: Your code ends here

        selectByGenre = session.prepare("SELECT * FROM top_videos WHERE genre = ? AND release_year >= ? AND release_year < ? LIMIT ? ALLOW FILTERING");

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        currentDate = cal.get(Calendar.YEAR)*10000 + (cal.get(Calendar.MONTH)+1)*100 + cal.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public void addVideo(Video newVideo) throws VideoAlreadyExistsException {
        DseSession session = getCassandraSession();

        // Bucket value extracted from videoId, used for the latest_videos table
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(UUIDs.unixTimestamp(newVideo.getVideoId())));
        int bucket = cal.get(Calendar.YEAR)*10000 + (cal.get(Calendar.MONTH)+1)*100 + cal.get(Calendar.DAY_OF_MONTH);

        // TODO: Insert your code here


        // TODO: Your added code ends here

    }

    @Override
    public UUID deleteVideo(UUID videoId) {
        DseSession session = getCassandraSession();

        return null;
    }

    @Override
    public Video getVideo(UUID video_id) {
        return null;
    }

    @Override
    public Iterable<LatestVideo> getLatestVideos() {
        ArrayList<LatestVideo> latestVideos = new ArrayList<LatestVideo>();

        // TODO: Insert your code here


        //TODO: Your added code ends here

        return latestVideos;
    }

    @Override
    public Iterable<VideoByUser> getUserVideos(UUID userId) {
        final int rowLimit = 12;
        return null;
    }

    @Override
    public Iterable<VideoByTag> getVideosByTag(String tag) {
        DseSession session = getCassandraSession();
        return null;
    }

    @Override
    public Collection<VideoByTag> getVideosByTags(Collection<String> tags) {
        List<VideoByTag> videos = new ArrayList<>();

        return videos;
    }

    @Override
    public boolean addTagToVideo(UUID videoId, String tag) {
        DseSession session = getCassandraSession();

        return true;
    }

    @Override
    public boolean removeTagFromVideo(UUID videoId, String tag) {
        Session session = getCassandraSession();

        return true;
    }

    @Override
    public boolean updateAvgRating(UUID videoId, double avgRating) {
        DseSession session = getCassandraSession();

        return true;
    }

    @Override
    public Iterable<VideoByGenre> getVideosByGenre(String genre, Integer mindate, Integer maxdate) {
        DseSession session = getCassandraSession();
        ArrayList<VideoByGenre> videos = new ArrayList<VideoByGenre>();
        final int rowLimit = 12;

        ResultSet rs = session.execute(selectByGenre.bind(genre, mindate, maxdate, rowLimit));
        for (Row row : rs) {
            VideoByGenre video = new VideoByGenre();

            video.setGenre(row.getString("genre"));
            video.setReleaseYear(row.getInt("release_year"));
            video.setTitle(row.getString("title"));
            videos.add(video);
        }
        return videos;
    }

}
