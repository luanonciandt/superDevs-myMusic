package com.ciandt.summit.bootcamp2022.domain.adapters.services;

import com.ciandt.summit.bootcamp2022.domain.Artist;
import com.ciandt.summit.bootcamp2022.domain.Music;
import com.ciandt.summit.bootcamp2022.domain.ports.repositories.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.exceptions.InvalidParameterException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class PlaylistServiceTest {

    @InjectMocks
    private PlaylistServiceImpl playlistService;

    @Mock
    private PlaylistRepositoryPort playlistRepository;

    @Test
    void addMusicToPlaylistTest() {
        Music music = new Music("1", "Quero sentir de novo", new Artist("13", "Exaltasamba"));
        String playlistId = "2";

        doNothing().when(playlistRepository).addMusicToPlaylist(playlistId, music);

        assertDoesNotThrow(() -> playlistService.addMusicToPlaylist(playlistId, music.toMusicDTO()));
    }

    @Test
    @Disabled
    void musicOrPlaylistDoesNotExistsTest() {
        Music music = new Music("1", "Quero sentir de novo", new Artist("13", "Exaltasamba"));
        String playlistId = "2";

        doThrow(new InvalidParameterException("Playlist ou música inexistentes."))
                .when(playlistRepository).addMusicToPlaylist(playlistId, music);

        assertThrows(InvalidParameterException.class, () -> playlistService.addMusicToPlaylist(playlistId, music.toMusicDTO()));
    }

    @Test
    void removeMusicFromPlaylistTest() {
        String playlistId = "0x001";
        String musicId = "0x0002";

        doNothing().when(playlistRepository).removeMusicFromPlaylist(playlistId, musicId);

        assertDoesNotThrow(() -> playlistService.removeMusicFromPlaylist(playlistId, musicId));
    }

    @Test
    void musicDoesNotExistTest() {
        String playlistId = "0x001";
        String musicId = "0x0002";

        doThrow(new InvalidParameterException("Playlist ou música inexistentes."))
                .when(playlistRepository).removeMusicFromPlaylist(playlistId, musicId);

        assertThrows(InvalidParameterException.class, () -> playlistService.removeMusicFromPlaylist(playlistId, musicId));
    }

}
