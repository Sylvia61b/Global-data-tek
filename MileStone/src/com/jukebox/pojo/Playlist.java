package com.jukebox.pojo;

import java.util.List;

public class Playlist {
    private Long id;
    private String name;
    private List<Long> songIds;
    private List<Long> podcastIds;

    public Playlist(String playlistName) {
        this.name = playlistName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getPodcastIds() {
        return podcastIds;
    }

    public void setPodcastIds(List<Long> podcastIds) {
        this.podcastIds = podcastIds;
    }

    public List<Long> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<Long> songIds) {
        this.songIds = songIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addSong(Long songId) {
        songIds.add(songId);
    }


    public void addPodcast(Long podcastId) {
        podcastIds.add(podcastId);
    }

    public void removeSong(Long songId) {
        songIds.remove(songId);
    }

    public void removePodcast(Long podcastId) {
        podcastIds.remove(podcastId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Playlist{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", songs=").append(songIds);
        sb.append(", podcasts=").append(podcastIds);
        sb.append('}');
        return sb.toString();
    }
}
