package com.datastax.training.killrvideo.model;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.nio.ByteBuffer;
import java.util.*;

/**
 * Created on 17/10/2015.
 */

@Table(name = "videos_by_user", readConsistency = "LOCAL_ONE", writeConsistency = "LOCAL_QUORUM")
public class VideoByUser {

    @PartitionKey
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "video_id")
    @ClusteringColumn
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

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public ByteBuffer getPreviewThumbnail() {
        return previewThumbnail;
    }

    public void setPreviewThumbnail(ByteBuffer previewThumbnail) {
        this.previewThumbnail = previewThumbnail;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getVideoId() {
        return videoId;
    }

    public void setVideoId(UUID videoId) {
        this.videoId = videoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoByUser that = (VideoByUser) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (videoId != null ? !videoId.equals(that.videoId) : that.videoId != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (tags != null ? !tags.equals(that.tags) : that.tags != null) return false;
        return previewThumbnail != null ? previewThumbnail.equals(that.previewThumbnail) : that.previewThumbnail == null;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (videoId != null ? videoId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (previewThumbnail != null ? previewThumbnail.hashCode() : 0);
        return result;
    }


//    @Override
//    public int hashCode() {
//        return Objects.hash(videoId);
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        boolean eq = false;
//        if (other instanceof VideoByUser) {
//            VideoByUser otherAccount = (VideoByUser) other;
//            eq = (otherAccount.videoId.equals(this.videoId));
//        }
//        return eq;
//    }

}