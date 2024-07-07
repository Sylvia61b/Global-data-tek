package com.jukebox.dao.impl;

import com.jukebox.dao.SongDAO;
import com.jukebox.pojo.Song;
import java.util.ArrayList;
import java.util.List;
import com.jukebox.exception.JukeboxException;
import java.util.Iterator;

public class SongDAOImpl implements SongDAO {
    private List<Song> songs = new ArrayList<>();
    private static long nextId = 1;

    @Override
    public List<Song> getAllSongs() {
        return songs;
    }

    @Override
    public Song getSongById(Long id) throws JukeboxException {
        for (Song song : songs) {
            if (song.getId().equals(id)) {
                return song;
            }
        }
        throw new JukeboxException("Song not found with id: " + id);
    }

    @Override
    public void addSong(Song song) throws JukeboxException {
        song.setId(nextId++);
        songs.add(song);
    }

    @Override
    public void updateSong(Long id, Song updatedSong) throws JukeboxException {
        boolean found = false;
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getId().equals(id)) {
                updatedSong.setId(id);
                songs.set(i, updatedSong);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new JukeboxException("Song not found with id: " + id);
        }
    }

    @Override
    public void deleteSong(Long id) throws JukeboxException {
        Iterator<Song> iterator = songs.iterator();
        while (iterator.hasNext()) {
            Song song = iterator.next();
            if (song.getId().equals(id)) {
                iterator.remove();
                return;
            }
        }
        throw new JukeboxException("Song not found with id: " + id);
    }

    @Override
    public List<Song> searchSongs(String criteria, String value) throws JukeboxException {
        List<Song> result = new ArrayList<>();
        for (Song song : songs) {
            switch (criteria) {
                case "title":
                    if (song.getTitle().equalsIgnoreCase(value)) {
                        result.add(song);
                    }
                    break;
                case "artist":
                    if (song.getArtist().equalsIgnoreCase(value)) {
                        result.add(song);
                    }
                    break;
                case "genre":
                    if (song.getGenre().equalsIgnoreCase(value)) {
                        result.add(song);
                    }
                    break;
                case "album":
                    if (song.getAlbum().equalsIgnoreCase(value)) {
                        result.add(song);
                    }
                    break;
                default:
                    throw new JukeboxException("Invalid search criteria: " + criteria);
            }
        }
        return result;
    }
}


