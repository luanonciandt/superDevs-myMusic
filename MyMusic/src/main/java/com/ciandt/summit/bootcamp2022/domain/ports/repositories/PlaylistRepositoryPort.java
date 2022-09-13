package com.ciandt.summit.bootcamp2022.domain.ports.repositories;

import com.ciandt.summit.bootcamp2022.domain.Music;
import com.ciandt.summit.bootcamp2022.domain.Playlist;
import com.ciandt.summit.bootcamp2022.domain.User;

public interface PlaylistRepositoryPort {
    void addMusicToPlaylist(String playlistId, Music music, String user);
    void removeMusicFromPlaylist(String playlistId, String musicId);

}
