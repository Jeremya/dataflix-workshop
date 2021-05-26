package com.datastax.training.killrvideo.model;

import com.datastax.driver.core.utils.UUIDs;
import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;

import java.nio.ByteBuffer;
import java.util.*;

public class LatestVideo {

    public LatestVideo() {}

    // Create a LatestVideo from the contents of a full-blown video
    public LatestVideo(Video video) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(UUIDs.unixTimestamp(video.getVideoId())));
        int bucket = cal.get(Calendar.YEAR)*10000 + (cal.get(Calendar.MONTH)+1)*100 + cal.get(Calendar.DAY_OF_MONTH);
        videoBucket = bucket;

        videoId = video.getVideoId();
        title = video.getTitle();
        type = video.getType();

        tags = new HashSet<>(video.getTags());

        // it may be dangerous to share the byte buffer
        previewThumbnail = video.getPreviewThumbnail();
    }

    public LatestVideo(int videoBucket, UUID videoId, String title, String type, Set<String> tags, ByteBuffer previewThumbnail) {
        this.videoBucket = videoBucket;
        this.videoId = videoId;
        this.title = title;
        this.type = type;
        this.tags = tags;
        this.previewThumbnail = previewThumbnail;
    }

    @PartitionKey
    @Column(name = "video_bucket")
    private int videoBucket;
    @ClusteringColumn
    @Column(name = "video_id")
    private UUID videoId;

    @Column(name = "title")
    private String title;
    @Column(name = "type")
    private String type;
    @Column(name = "tags")
    private Set<String> tags;
    @Column(name = "preview_thumbnail")
    private ByteBuffer previewThumbnail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean hasTitle() { return (title != null); }

    public String getType() { return type; }

    public boolean hasType() { return (type != null); }

    public void setType(String type) { this.type = type; }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public boolean hasTags() { return (tags != null); }

    public ByteBuffer getPreviewThumbnail() {
        return previewThumbnail;
    }

    public boolean hasPreviewThumbnail() { return (previewThumbnail != null); }

    public void setPreviewThumbnail(ByteBuffer previewThumbnail) {
        this.previewThumbnail = previewThumbnail;
    }

    public UUID getVideoId() {
        return videoId;
    }

    public boolean hasVideoId() { return (videoId != null); }

    public void setVideoId(UUID videoId) {
        this.videoId = videoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LatestVideo video = (LatestVideo) o;

        if (videoId != null ? !videoId.equals(video.videoId) : video.videoId != null) return false;
        if (title != null ? !title.equals(video.title) : video.title != null) return false;
        if (type != null ? !type.equals(video.type) : video.type != null) return false;
        if (tags != null ? !tags.equals(video.tags) : video.tags != null) return false;
        if (previewThumbnail != null ? !previewThumbnail.equals(video.previewThumbnail) : video.previewThumbnail != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = videoId != null ? videoId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (previewThumbnail != null ? previewThumbnail.hashCode() : 0);
        return result;
    }

}
