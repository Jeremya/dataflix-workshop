package com.datastax.training.killrvideo.model;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Created on 17/10/2015.
 */

@Table(name = "top_videos", readConsistency = "LOCAL_ONE", writeConsistency = "LOCAL_QUORUM")
public class VideoByGenre {

    @PartitionKey
    @Column(name = "genre")
    private String genre;

    @Column(name = "release_year")
    @ClusteringColumn
    private Integer release_year;
    @Column(name = "title")
    @ClusteringColumn
    private String title;

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; };

    public Integer getReleaseYear() {
        return release_year;
    }

    public void setReleaseYear(Integer release_year) {
        this.release_year = release_year;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

}
