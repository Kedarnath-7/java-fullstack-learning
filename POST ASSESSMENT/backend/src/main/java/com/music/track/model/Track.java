package com.music.track.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "track")

public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "album_name")
    private String albumName;
    @Column(name = "release_date")
    private Date releaseDate;
    @Column(name = "playCount")
    private Integer playCount;

    public Track() {
    }
    public Track(Long id, String title, String albumName, Date releaseDate) {
        this.id = id;
        this.title = title;
        this.albumName = albumName;
        this.releaseDate = releaseDate;
    }
    public Track(String title, String s, Date date, Integer integer) {
        this.title = title;
        this.albumName = s;
        this.releaseDate = date;
        this.playCount = integer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }
}
