package com.jukebox.controller;

import com.jukebox.pojo.Song;
import com.jukebox.service.SongService;
import com.jukebox.exception.JukeboxException;

import java.util.List;

public class SongController {
    private final SongService songService;

    public SongController() {
        this.songService = new SongService();
    }

    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    public Song getSongById(Long id) throws JukeboxException {
        return songService.getSongById(id);
    }

    public void addSong(Song song) throws JukeboxException {
        songService.addSong(song);
    }

    public void updateSong(Long id, Song song) throws JukeboxException {
        songService.updateSong(id, song);
    }

    public void deleteSong(Long id) throws JukeboxException {
        songService.deleteSong(id);
    }

    public List<Song> searchSongs(String criteria, String value) throws JukeboxException {
        return songService.searchSongs(criteria, value);
    }
}

