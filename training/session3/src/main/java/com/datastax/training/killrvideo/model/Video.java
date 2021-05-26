package com.datastax.training.killrvideo.model;

import com.datastax.driver.mapping.annotations.*;

import java.nio.ByteBuffer;
import java.util.*;

/**
 * Created on 17/10/2015.
 */

public class Video {

    private UUID videoId;

    private String description;
    private String title;
    private String type;
    private String url;
    private Date releaseDate;
    private int releaseYear;
    private float avgRating;
    private String mpaaRating;
    private Set<String> tags;
    private ByteBuffer previewThumbnail;
    private Set<String> genres;
    private UUID userId;

    public String getDescription() {
        return description;
    }

    public boolean hasDescription() { return (description != null); }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public String getUrl() {
        return url;
    }

    public boolean hasUrl() { return (url != null); }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public boolean hasReleaseDate() { return (releaseDate != null); }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public boolean hasReleaseYear() { return (releaseYear > 0); }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public boolean hasMpaaRating() { return (mpaaRating != null); }

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

    public Set<String> getGenres() {
        return genres;
    }

    public boolean hasGenres() { return (genres != null); }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public UUID getUserId() {
        return userId;
    }

    public boolean hasUserId() { return (userId != null); }

    public void setUserId(UUID userId) {
        this.userId = userId;
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

        Video video = (Video) o;

        if (releaseYear != video.releaseYear) return false;
        if (Float.compare(video.avgRating, avgRating) != 0) return false;
        if (videoId != null ? !videoId.equals(video.videoId) : video.videoId != null) return false;
        if (description != null ? !description.equals(video.description) : video.description != null) return false;
        if (title != null ? !title.equals(video.title) : video.title != null) return false;
        if (type != null ? !type.equals(video.type) : video.type != null) return false;
        if (url != null ? !url.equals(video.url) : video.url != null) return false;
        if (releaseDate != null ? !releaseDate.equals(video.releaseDate) : video.releaseDate != null) return false;
        if (mpaaRating != null ? !mpaaRating.equals(video.mpaaRating) : video.mpaaRating != null) return false;
        if (tags != null ? !tags.equals(video.tags) : video.tags != null) return false;
        if (previewThumbnail != null ? !previewThumbnail.equals(video.previewThumbnail) : video.previewThumbnail != null)
            return false;
        if (genres != null ? !genres.equals(video.genres) : video.genres != null) return false;
        return userId != null ? userId.equals(video.userId) : video.userId == null;

    }

    @Override
    public int hashCode() {
        int result = videoId != null ? videoId.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + releaseYear;
        result = 31 * result + (avgRating != +0.0f ? Float.floatToIntBits(avgRating) : 0);
        result = 31 * result + (mpaaRating != null ? mpaaRating.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (previewThumbnail != null ? previewThumbnail.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
