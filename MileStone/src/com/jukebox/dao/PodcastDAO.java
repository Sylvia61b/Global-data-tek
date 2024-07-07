package com.jukebox.dao;

import com.jukebox.exception.JukeboxException;
import com.jukebox.pojo.Podcast;

import java.util.List;

public interface PodcastDAO {
    List<Podcast> getAllPodcasts();
    Podcast getPodcastById(Long id) throws JukeboxException;
    void addPodcast(Podcast podcast) throws JukeboxException;
    void updatePodcast(Long id, Podcast podcast) throws JukeboxException;
    void deletePodcast(Long id) throws JukeboxException;
    List<Podcast> searchPodcasts(String criteria, String value) throws JukeboxException;
}
