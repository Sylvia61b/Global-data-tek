package com.jukebox.dao.impl;

import com.jukebox.dao.PlaylistDAO;
import com.jukebox.dao.PodcastDAO;
import com.jukebox.dao.SongDAO;
import com.jukebox.exception.JukeboxException;
import com.jukebox.pojo.Playlist;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAOImpl implements PlaylistDAO {
    private static long nextId = 1;
    private List<Playlist> playlists;
    private PodcastDAO podcastDAO;
    private SongDAO songDAO;

    public PlaylistDAOImpl(SongDAO songDAO, PodcastDAO podcastDAO) {
        this.playlists = new ArrayList<>();
        this.podcastDAO = podcastDAO;
        this.songDAO = songDAO;
    }


    @Override
    public List<Playlist> getAllPlaylists() {
        return playlists;
    }

    @Override
    public Playlist getPlaylistById(Long id) {
        return playlists.stream()
                .filter(playlist -> playlist.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void createPlaylist(Playlist playlist) {
        playlist.setId(nextId++);
        playlists.add(playlist);
    }

    @Override
    public void addSongToPlaylist(Long playlistId, Long songId) throws JukeboxException {
        Playlist playlist = getPlaylistById(playlistId);
        if (playlist != null) {
            if (songDAO.getSongById(songId) != null) {
                playlist.addSong(songId);
            } else {
                throw new JukeboxException("Song not found with id: " + songId);
            }
        } else {
            throw new JukeboxException("Playlist not found with id: " + playlistId);
        }

    }

    @Override
    public void addPodcastToPlaylist(Long playlistId, Long podcastId) throws JukeboxException {
        Playlist playlist = getPlaylistById(playlistId);

        if (playlist != null ) {
            if (podcastDAO.getPodcastById(podcastId) != null) {
                playlist.addPodcast(podcastId);
            } else {
                throw new JukeboxException("Podcast not found with id: " + podcastId);
            }
        } else {
            throw new JukeboxException("Playlist not found with id: " + playlistId);
        }
    }

    @Override
    public void removeSongFromPlaylist(Long playlistId, Long songId) throws JukeboxException {
        Playlist playlist = getPlaylistById(playlistId);
        if (playlist != null) {
            if (playlist.getSongIds().contains(songId)) {
                playlist.removeSong(songId);
            } else {
                throw new JukeboxException("Song not found in playlist with id: " + songId);
            }
        } else {
            throw new JukeboxException("Playlist not found with id: " + playlistId);
        }
    }

    @Override
    public void removePodcastFromPlaylist(Long playlistId, Long podcastId) throws JukeboxException {
        Playlist playlist = getPlaylistById(playlistId);
        if (playlist != null) {
            if (playlist.getPodcastIds().contains(podcastId)) {
                playlist.removePodcast(podcastId);
            } else {
                throw new JukeboxException("Podcast not found in playlist with id: " + podcastId);
            }
        } else {
            throw new JukeboxException("Playlist not found with id: " + playlistId);
        }
    }
}

