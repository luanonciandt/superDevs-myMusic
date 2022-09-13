package com.ciandt.summit.bootcamp2022.domain.ports.interfaces;

import com.ciandt.summit.bootcamp2022.domain.dtos.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.dtos.PlaylistDTO;
import com.ciandt.summit.bootcamp2022.domain.dtos.UserDTO;

public interface PlaylistServicePort {

    void addMusicToPlaylist(String playlistId, MusicDTO musicDTO, String user);
    void removeMusicFromPlaylist(String playlistId, String musicId);


}
