package com.jukebox.service;

import com.jukebox.dao.PlaylistDAO;
import com.jukebox.dao.impl.PlaylistDAOImpl;
import com.jukebox.dao.impl.PodcastDAOImpl;
import com.jukebox.dao.impl.SongDAOImpl;
import com.jukebox.exception.JukeboxException;
import com.jukebox.pojo.Playlist;

import java.util.List;

public class PlaylistService {
    private PlaylistDAO playlistDAO;

    public PlaylistService() {
        playlistDAO = new PlaylistDAOImpl(new SongDAOImpl(), new PodcastDAOImpl());
    }

    public List<Playlist> getAllPlaylists() {
        return playlistDAO.getAllPlaylists();
    }

    public Playlist getPlaylistById(Long id) {
        return playlistDAO.getPlaylistById(id);
    }

    public void createPlaylist(Playlist playlist) {
        playlistDAO.createPlaylist(playlist);
    }

    public void addSongToPlaylist(Long playlistId, Long songId) throws JukeboxException {
        playlistDAO.addSongToPlaylist(playlistId, songId);
    }

    public void addPodcastToPlaylist(Long playlistId, Long podcastId) throws JukeboxException {
        playlistDAO.addPodcastToPlaylist(playlistId, podcastId);
    }

    public void removeSongFromPlaylist(Long playlistId, Long songId) throws JukeboxException {
        playlistDAO.removeSongFromPlaylist(playlistId, songId);
    }

    public void removePodcastFromPlaylist(Long playlistId, Long podcastId) throws JukeboxException {
        playlistDAO.removePodcastFromPlaylist(playlistId, podcastId);
    }
}