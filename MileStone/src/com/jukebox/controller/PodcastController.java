package com.jukebox.controller;

import com.jukebox.pojo.Podcast;
import com.jukebox.service.PodcastService;
import com.jukebox.exception.JukeboxException;

import java.util.List;

public class PodcastController {
    private final PodcastService podcastService;

    public PodcastController() {
        this.podcastService = new PodcastService();
    }

    public List<Podcast> getAllPodcasts() {
        return podcastService.getAllPodcasts();
    }

    public Podcast getPodcastById(Long id) throws JukeboxException {
        return podcastService.getPodcastById(id);
    }

    public void addPodcast(Podcast podcast) throws JukeboxException {
        podcastService.addPodcast(podcast);
    }

    public void updatePodcast(Long id, Podcast podcast) throws JukeboxException {
        podcastService.updatePodcast(id, podcast);
    }

    public void deletePodcast(Long id) throws JukeboxException {
        podcastService.deletePodcast(id);
    }

    public List<Podcast> searchPodcasts(String criteria, String value) throws JukeboxException {
        return podcastService.searchPodcasts(criteria, value);
    }
}

