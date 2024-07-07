package com.jukebox;

import com.jukebox.controller.PlaylistController;
import com.jukebox.controller.PodcastController;
import com.jukebox.controller.SongController;
import com.jukebox.pojo.*;

import com.jukebox.exception.JukeboxException;

import java.util.List;
import java.util.Scanner;


/**
 * Main class for the Jukebox application, handling user interactions and
 * presenting a menu-driven interface for managing songs, podcasts, and playlists.
 */
public class JukeboxApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final SongController songController = new SongController();
    private static final PodcastController podcastController = new PodcastController();
    private static final PlaylistController playlistController = new PlaylistController();

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            printMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayAllSongs();
                    break;
                case 2:
                    searchSongs();
                    break;
                case 3:
                    displayAllPodcasts();
                    break;
                case 4:
                    searchPodcasts();
                    break;
                case 5:
                    createPlaylist();
                    break;
                case 6:
                    addContentToPlaylist();
                    break;
                case 7:
                    viewPlaylists();
                    break;
                case 8:
                    exit = true;
                    System.out.println("Exiting JukeboxApp. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 8.");
            }
        }

        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n=== Jukebox Main Menu ===");
        System.out.println("1. Display All Songs");
        System.out.println("2. Search Songs");
        System.out.println("3. Display All Podcasts");
        System.out.println("4. Search Podcasts");
        System.out.println("5. Create Playlist");
        System.out.println("6. Add Content to Playlist");
        System.out.println("7. View Playlists");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void displayAllSongs() {
        List<Song> songs = songController.getAllSongs();
        System.out.println("\n=== All Songs ===");
        for (Song song : songs) {
            System.out.println(song);
        }
    }

    /**
     * Searches for songs based on user-specified criteria (artist, genre, album).
     */
    private static void searchSongs() {
        System.out.println("\n=== Search Songs ===");
        System.out.println("Search by: ");
        System.out.println("1. Artist");
        System.out.println("2. Genre");
        System.out.println("3. Album");
        System.out.print("Enter your choice: ");
        int searchChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        String searchCriteria;
        switch (searchChoice) {
            case 1:
                System.out.print("Enter artist name: ");
                searchCriteria = scanner.nextLine();
                try {
                    List<Song> songsByArtist = songController.searchSongs("artist", searchCriteria);
                    displaySearchResults(songsByArtist);
                } catch (JukeboxException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 2:
                System.out.print("Enter genre: ");
                searchCriteria = scanner.nextLine();
                try {
                    List<Song> songsByGenre = songController.searchSongs("genre", searchCriteria);
                    displaySearchResults(songsByGenre);
                } catch (JukeboxException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 3:
                System.out.print("Enter album name: ");
                searchCriteria = scanner.nextLine();
                try {
                    List<Song> songsByAlbum = songController.searchSongs("album", searchCriteria);
                    displaySearchResults(songsByAlbum);
                } catch (JukeboxException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * Displays the results of a song search.
     *
     * @param songs List of songs matching the search criteria.
     */
    private static void displaySearchResults(List<Song> songs) {
        System.out.println("\n=== Search Results ===");
        if (songs.isEmpty()) {
            System.out.println("No songs found.");
        } else {
            for (Song song : songs) {
                System.out.println(song);
            }
        }
    }

    private static void displayAllPodcasts() {
        List<Podcast> podcasts = podcastController.getAllPodcasts();
        System.out.println("\n=== All Podcasts ===");
        for (Podcast podcast : podcasts) {
            System.out.println(podcast);
        }
    }

    private static void searchPodcasts() {
        System.out.println("\n=== Search Podcasts ===");
        System.out.println("Search by: ");
        System.out.println("1. Celebrity");
        System.out.println("2. Date");
        System.out.print("Enter your choice: ");
        int searchChoice = scanner.nextInt();
        scanner.nextLine();

        String searchCriteria;
        switch (searchChoice) {
            case 1:
                System.out.print("Enter celebrity name: ");
                searchCriteria = scanner.nextLine();
                try {
                    List<Podcast> podcastsByCelebrity = podcastController.searchPodcasts("celebrity", searchCriteria);
                    displayPodcastSearchResults(podcastsByCelebrity);
                } catch (JukeboxException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 2:
                System.out.print("Enter date (YYYY-MM-DD): ");
                searchCriteria = scanner.nextLine();
                try {
                    List<Podcast> podcastsByDate = podcastController.searchPodcasts("date", searchCriteria);
                    displayPodcastSearchResults(podcastsByDate);
                } catch (JukeboxException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void displayPodcastSearchResults(List<Podcast> podcasts) {
        System.out.println("\n=== Podcast Search Results ===");
        if (podcasts.isEmpty()) {
            System.out.println("No podcasts found.");
        } else {
            for (Podcast podcast : podcasts) {
                System.out.println(podcast);
            }
        }
    }

    private static void createPlaylist() {
        System.out.print("Enter playlist name: ");
        String name = scanner.nextLine();
        Playlist playlist = new Playlist(name);
        playlistController.createPlaylist(playlist);
        System.out.println("Playlist created successfully.");
    }

    private static void addContentToPlaylist() {
        System.out.print("Enter playlist ID: ");
        long playlistId = scanner.nextLong();
        scanner.nextLine(); // Consume newline left-over

        System.out.println("Add to Playlist:");
        System.out.println("1. Song");
        System.out.println("2. Podcast");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        switch (choice) {
            case 1:
                System.out.print("Enter song ID: ");
                long songId = scanner.nextLong();
                try {
                    playlistController.addSongToPlaylist(playlistId, songId);
                    System.out.println("Song added to playlist successfully.");
                } catch (JukeboxException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 2:
                System.out.print("Enter podcast ID: ");
                long podcastId = scanner.nextLong();
                try {
                    playlistController.addPodcastToPlaylist(playlistId, podcastId);
                    System.out.println("Podcast added to playlist successfully.");
                } catch (JukeboxException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void viewPlaylists() {
        List<Playlist> playlists = playlistController.getAllPlaylists();
        System.out.println("\n=== All Playlists ===");
        for (Playlist playlist : playlists) {
            System.out.println(playlist);
        }
    }
}
