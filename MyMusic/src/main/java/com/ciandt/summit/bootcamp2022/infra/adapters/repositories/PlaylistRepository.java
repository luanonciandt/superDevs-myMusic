package com.ciandt.summit.bootcamp2022.infra.adapters.repositories;

import com.ciandt.summit.bootcamp2022.domain.Music;
import com.ciandt.summit.bootcamp2022.domain.ports.repositories.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.exceptions.InvalidParameterException;
import com.ciandt.summit.bootcamp2022.infra.adapters.entities.MusicEntity;
import com.ciandt.summit.bootcamp2022.infra.adapters.entities.PlaylistEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PlaylistRepository implements PlaylistRepositoryPort {

    private final SpringPlaylistRepository springPlaylistRepository;

    public PlaylistRepository(SpringPlaylistRepository springPlaylistRepository) {
        this.springPlaylistRepository = springPlaylistRepository;
    }

    @Override
    public void addMusicToPlaylist(String playlistId, Music music)  {
        if(!springPlaylistRepository.existsById(playlistId) || !springPlaylistRepository.existsById(music.getId()))
            throw new InvalidParameterException("Playlist ou música inexistentes.");
        PlaylistEntity playlistEntity = this.springPlaylistRepository.getById(playlistId);
        playlistEntity.getMusics().add(new MusicEntity(music));
        this.springPlaylistRepository.save(playlistEntity);
    }

    @Override
    public void removeMusicFromPlaylist(String playlistId, String musicId) {
        if(!springPlaylistRepository.existsById(playlistId) || !springPlaylistRepository.existsById(musicId))
            throw new InvalidParameterException("Playlist ou música inexistentes.");
        PlaylistEntity playlistEntity = this.springPlaylistRepository.getById(playlistId);
        playlistEntity.setMusics(playlistEntity.getMusics().stream().filter(musicEntity -> !musicEntity.getId().equals(musicId)).collect(Collectors.toList()));
        this.springPlaylistRepository.save(playlistEntity);
    }
}
