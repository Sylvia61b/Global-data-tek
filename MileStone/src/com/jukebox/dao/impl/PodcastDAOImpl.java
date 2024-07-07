package com.jukebox.dao.impl;

import com.jukebox.dao.PodcastDAO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.jukebox.exception.JukeboxException;
import com.jukebox.pojo.*;

public class PodcastDAOImpl implements PodcastDAO {
    private List<Podcast> podcasts = new ArrayList<>();
    private static long nextId = 1;


    @Override
    public List<Podcast> getAllPodcasts() {
        return podcasts;
    }

    @Override
    public Podcast getPodcastById(Long id) throws JukeboxException {
        for (Podcast podcast : podcasts) {
            if (podcast.getId().equals(id)) {
                return podcast;
            }
        }
        throw new JukeboxException("Podcast not found with id: " + id);
    }

    @Override
    public void addPodcast(Podcast podcast) throws JukeboxException {
        podcast.setId(nextId++);
        podcasts.add(podcast);
    }

    @Override
    public void updatePodcast(Long id, Podcast updatedPodcast) throws JukeboxException {
        boolean found = false;
        for (int i = 0; i < podcasts.size(); i++) {
            if (podcasts.get(i).getId().equals(id)) {
                updatedPodcast.setId(id);
                podcasts.set(i, updatedPodcast);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new JukeboxException("Podcast not found with id: " + id);
        }
    }

    @Override
    public void deletePodcast(Long id) throws JukeboxException {
        Iterator<Podcast> iterator = podcasts.iterator();
        while (iterator.hasNext()) {
            Podcast podcast = iterator.next();
            if (podcast.getId().equals(id)) {
                iterator.remove();
                return;
            }
        }
        throw new JukeboxException("Podcast not found with id: " + id);
    }

    @Override
    public List<Podcast> searchPodcasts(String criteria, String value) throws JukeboxException {
        List<Podcast> result = new ArrayList<>();
        for (Podcast podcast : podcasts) {
            switch (criteria) {
                case "title":
                    if (podcast.getTitle().equalsIgnoreCase(value)) {
                        result.add(podcast);
                    }
                    break;
                case "celebrity":
                    if (podcast.getCelebrity().equalsIgnoreCase(value)) {
                        result.add(podcast);
                    }
                    break;
                case "date":
                    if (podcast.getDate().equalsIgnoreCase(value)) {
                        result.add(podcast);
                    }
                    break;
                default:
                    throw new JukeboxException("Invalid search criteria: " + criteria);
            }
        }
        return result;
    }
}