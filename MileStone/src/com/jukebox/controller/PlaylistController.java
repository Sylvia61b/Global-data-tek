package com.jukebox.controller;

import com.jukebox.exception.JukeboxException;
import com.jukebox.pojo.Playlist;
import com.jukebox.service.PlaylistService;

import java.util.List;

public class PlaylistController {
    private final PlaylistService playlistService;

    public PlaylistController() {
        this.playlistService = new PlaylistService();
    }

    public List<Playlist> getAllPlaylists() {
        return playlistService.getAllPlaylists();
    }

    public Playlist getPlaylistById(Long id) {
        return playlistService.getPlaylistById(id);
    }

    public void createPlaylist(Playlist playlist) {
        playlistService.createPlaylist(playlist);
    }

    public void addSongToPlaylist(Long playlistId, Long songId) throws JukeboxException {
        playlistService.addSongToPlaylist(playlistId, songId);
    }

    public void addPodcastToPlaylist(Long playlistId, Long podcastId) throws JukeboxException {
        playlistService.addPodcastToPlaylist(playlistId, podcastId);
    }

    public void removeSongFromPlaylist(Long playlistId, Long songId) throws JukeboxException {
        playlistService.removeSongFromPlaylist(playlistId, songId);
    }

    public void removePodcastFromPlaylist(Long playlistId, Long podcastId) throws JukeboxException {
        playlistService.removePodcastFromPlaylist(playlistId, podcastId);
    }
}
