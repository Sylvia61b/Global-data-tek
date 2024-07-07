package com.jukebox.service;

import com.jukebox.dao.PodcastDAO;
import com.jukebox.dao.impl.PodcastDAOImpl;
import com.jukebox.pojo.Podcast;
import com.jukebox.exception.JukeboxException;
import java.util.List;

public class PodcastService {
    private PodcastDAO podcastDAO;

    public PodcastService() {
        this.podcastDAO = new PodcastDAOImpl();
    }

    public List<Podcast> searchPodcasts(String celebrity, String date) throws JukeboxException {
        return podcastDAO.searchPodcasts(celebrity, date);
    }


    public Podcast getPodcastById(Long id) throws JukeboxException {
        return podcastDAO.getPodcastById(id);
    }

    public List<Podcast> getAllPodcasts() {
        return podcastDAO.getAllPodcasts();
    }

    public void addPodcast(Podcast podcast) throws JukeboxException {
        podcastDAO.addPodcast(podcast);
    }

    public void updatePodcast(Long id, Podcast podcast) throws JukeboxException {
        podcastDAO.updatePodcast(id, podcast);
    }

    public void deletePodcast(Long id) throws JukeboxException {
        podcastDAO.deletePodcast(id);
    }


}
