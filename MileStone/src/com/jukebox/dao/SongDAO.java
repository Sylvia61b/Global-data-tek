package com.jukebox.dao;

import com.jukebox.exception.JukeboxException;
import com.jukebox.pojo.Song;
import java.util.List;

public interface SongDAO {
    List<Song> getAllSongs();

    Song getSongById(Long id) throws JukeboxException;

    void addSong(Song song) throws JukeboxException;

    void updateSong(Long id, Song song) throws JukeboxException;

    void deleteSong(Long id) throws JukeboxException;

    List<Song> searchSongs(String criteria, String value) throws JukeboxException;

}
