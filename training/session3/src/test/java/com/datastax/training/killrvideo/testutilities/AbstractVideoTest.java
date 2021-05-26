package com.datastax.training.killrvideo.testutilities;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.utils.UUIDs;
import com.datastax.training.killrvideo.model.LatestVideo;
import com.datastax.training.killrvideo.model.Video;
import com.datastax.training.killrvideo.model.VideoAlreadyExistsException;
import com.datastax.training.killrvideo.model.dao.cassandra.AbstractMapperDAO;
import com.datastax.training.killrvideo.model.dao.cassandra.CassandraVideoDAO;
import com.datastax.training.killrvideo.testutilities.AbstractDSETest;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class AbstractVideoTest extends AbstractDSETest {

    private class Retriever extends AbstractMapperDAO<Video> {
        public Video getVideo(UUID video_id) {
            return mapper.get(video_id);
        }

        public Iterable<LatestVideo> getLatestVideos() {
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            ArrayList<LatestVideo> latestVideos = new ArrayList<LatestVideo>();
            int rowLimit = 12;

            // Gets the most latest week's videos, or the first 12, whichever comes first
            int currentDate = cal.get(Calendar.YEAR)*10000 + (cal.get(Calendar.MONTH)+1)*100 + cal.get(Calendar.DAY_OF_MONTH);
            int currentBucket = currentDate;
            int lastBucket = currentDate - 7;

            while (latestVideos.size() < rowLimit && currentBucket >= lastBucket) {
                Statement selectLatestVideos = QueryBuilder
                        .select()
                        .from("latest_videos")
                        .where(QueryBuilder.eq("video_bucket", currentBucket))
                        .limit(rowLimit);

                ResultSet rs = getCassandraSession().execute(selectLatestVideos);
                for (Row row : rs) {
                    if (latestVideos.size() >= 12)
                        break;

                    LatestVideo newVideo = new LatestVideo();
                    newVideo.setVideoId(row.getUUID("video_id"));
                    newVideo.setTitle(row.getString("title"));
                    newVideo.setType(row.getString("type"));
                    newVideo.setTags(row.getSet("tags", String.class));
                    newVideo.setPreviewThumbnail(row.getBytes("preview_thumbnail"));

                    latestVideos.add(newVideo);
                }
                currentBucket = currentBucket - 1;
            }

            return latestVideos;
        }
    }

    protected Video createVideo() {

        HashSet<String> strings = new HashSet<String>();
        strings.add("epic");
        strings.add("awesome");

        // Add a single video
        Video video = new Video();
        video.setUserId(UUIDs.timeBased());
        video.setVideoId(UUIDs.timeBased());
        video.setTitle("Pirates of the Caribbean");
        video.setAvgRating(4.9f);
        video.setDescription("Epic movie!");
        video.setMpaaRating("PG");

        Date date = new Date();
        video.setReleaseDate(date);
        //video.setReleaseDate(new Date(1056780000000L)); // 6/28/2003

        video.setPreviewThumbnail(ByteBuffer.wrap(new byte[] { 0, 1, 2 }));
        video.setType("Movie");
        video.setGenres(strings);
        video.setTags(strings);
        video.setUrl("https://www.youtube.com/watch?v=naQr0uTrH_s");

        return video;

    }

    protected Video getVideo(UUID video_id) {
        Retriever retriever = new Retriever();
        return retriever.getVideo(video_id);
    }

    protected Iterable<LatestVideo> getLatestVideos() {
        Retriever retriever = new Retriever();
        return retriever.getLatestVideos();
    }
}
