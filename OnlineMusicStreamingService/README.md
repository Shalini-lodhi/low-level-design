# Designing an Online Music Streaming Service - Spotify

### Requirements
1. The music streaming service should allow users to browse and search for songs, albums, and artists.
2. Users should be able to create and manage playlists.
3. The system should support user authentication and authorization.
4. Users should be able to play, pause, skip, and seek within songs.
5. The system should recommend songs and playlists based on user preferences and listening history.
6. The system should handle concurrent requests and ensure smooth streaming experience for multiple users.
7. The system should be scalable and handle a large volume of songs and users.
8. The system should be extensible to support additional features such as social sharing and offline playback.

### Classes, Interfaces and Enumerations
1. The **Song**, **Album**, and **Artist** classes represent the basic entities in the music streaming service, with properties such as ID, title, artist, album, duration, and relationships between them.
2. The **User** class represents a user of the music streaming service, with properties like ID, username, password, and a list of playlists.
3. The **Playlist** class represents a user-created playlist, containing a list of songs.
4. The **MusicLibrary** class serves as a central repository for storing and managing songs, albums, and artists. It follows the Singleton pattern to ensure a single instance of the music library.
5. The **UserManager** class handles user registration, login, and other user-related operations. It also follows the Singleton pattern.
6. The **MusicPlayer** class represents the music playback functionality, allowing users to play, pause, skip, and seek within songs.
7. The **MusicRecommender** class generates song recommendations based on user preferences and listening history. It follows the Singleton pattern.
8. The **MusicStreamingService** class is the main entry point of the music streaming service. It initializes the necessary components, handles user requests, and manages the overall functionality of the service.

### Class Diagram
```mermaid
classDiagram
    class Song {
        +String id
        +String title
        +String artist
        +String album
        +int duration
        +getId()
        +getTitle()
        +getArtist()
        +getAlbum()
    }

    class Album {
        +String id
        +String title
        +String artist
        +List<Song> songs
        +getId()
        +getTitle()
        +getArtist()
        +getSongs()
    }

    class Artist {
        +String id
        +String name
        +List<Album> albums
        +getId()
        +getAlbums()
    }

    class User {
        +String id
        +String username
        +String password
        +List<Playlist> playlists
        +getId()
        +getUsername()
        +getPassword()
        +getPlaylists()
        +addPlaylist(Playlist playlist)
        +removePlaylist(Playlist playlist)
    }

    class Playlist {
        +String id
        +String name
        +User owner
        +List<Song> songs
        +getName()
        +getSongs()
        +addSong(Song song)
        +removeSong(Song song)
    }

    class MusicLibrary {
        +Map<String, Song> songs
        +Map<String, Album> albums
        +Map<String, Artist> artists
        +static MusicLibrary getInstance()
        +void addSong(Song song)
        +void addAlbum(Album album)
        +void addArtist(Artist artist)
        +Song getSong(String songId)
        +Album getAlbum(String albumId)
        +Artist getArtist(String artistId)
        +List<Song> searchSongs(String query)
    }

    class MusicPlayer {
        -Song currentSong
        -boolean isPlaying
        -int currentTime
        +void playSong(Song song)
        +void pauseSong()
        +void seekTo(int time)
    }

    class MusicRecommender {
        +Map<String, List<Song>> userRecommendations
        +static MusicRecommender getInstance()
        +List<Song> recommendSongs(User user)
    }

    class UserManager {
        +Map<String, User> users
        +static UserManager getInstance()
        +void registerUser(User user)
        +User loginUser(String username, String password)
    }

    class MusicStreamingService {
        +MusicLibrary musicLibrary
        +UserManager userManager
        +MusicRecommender musicRecommender
        +MusicStreamingService()
        +void start()
        +MusicLibrary getMusicLibrary()
        +UserManager getUserManager()
        +MusicRecommender getMusicRecommender()
    }

    class MusicStreamingServiceDemo {
        +static void run()
    }

    %% Relationships %%
    Artist "1" --> "many" Album : has
    Album "1" --> "many" Song : contains
    User "1" --> "many" Playlist : owns
    Playlist "1" --> "many" Song : contains
    MusicLibrary "1" --> "many" Song : manages
    MusicLibrary "1" --> "many" Album : manages
    MusicLibrary "1" --> "many" Artist : manages
    MusicLibrary "1" --> "1" MusicStreamingService : belongsTo
    UserManager "1" --> "many" User : manages
    UserManager "1" --> "1" MusicStreamingService : belongsTo
    MusicRecommender "1" --> "many" Song : recommends
    MusicRecommender "1" --> "1" MusicStreamingService : belongsTo
    MusicStreamingServiceDemo "1" --> "1" MusicStreamingService : uses
    MusicPlayer "1" --> "1"Song : has
```