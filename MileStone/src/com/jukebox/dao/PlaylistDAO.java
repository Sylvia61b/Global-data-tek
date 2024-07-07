package com.jukebox.dao;

import com.jukebox.exception.JukeboxException;
import com.jukebox.pojo.Playlist;

import java.util.List;

public interface PlaylistDAO {
    List<Playlist> getAllPlaylists();
    Playlist getPlaylistById(Long id);
    void createPlaylist(Playlist playlist);
    void addSongToPlaylist(Long playlistId, Long songId) throws JukeboxException;
    void addPodcastToPlaylist(Long playlistId, Long podcastId) throws JukeboxException;
    void removeSongFromPlaylist(Long playlistId, Long songId) throws JukeboxException;
    void removePodcastFromPlaylist(Long playlistId, Long podcastId) throws JukeboxException;
}

