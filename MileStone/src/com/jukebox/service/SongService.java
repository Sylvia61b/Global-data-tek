package com.jukebox.service;

import com.jukebox.dao.SongDAO;
import com.jukebox.dao.impl.SongDAOImpl;
import com.jukebox.pojo.Song;
import com.jukebox.exception.JukeboxException;

import java.util.List;

public class SongService {
    private SongDAO songDAO;

    public SongService() {
        this.songDAO = new SongDAOImpl();
    }

    public List<Song> getAllSongs() {
        return songDAO.getAllSongs();
    }

    public List<Song> searchSongs(String criteria, String value) throws JukeboxException {
        if (criteria == null || value == null) {
            throw new JukeboxException("Criteria or value cannot be null");
        }
        return songDAO.searchSongs(criteria, value);
    }


    public Song getSongById(Long id) throws JukeboxException {
        return songDAO.getSongById(id);
    }

    public void addSong(Song song) throws JukeboxException {
        songDAO.addSong(song);
    }

    public void updateSong(Long id, Song song) throws JukeboxException {
        songDAO.updateSong(id, song);
    }

    public void deleteSong(Long id) throws JukeboxException {
        songDAO.deleteSong(id);
    }


}

