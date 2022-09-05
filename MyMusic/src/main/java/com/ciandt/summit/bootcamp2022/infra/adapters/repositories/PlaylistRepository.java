package com.ciandt.summit.bootcamp2022.infra.adapters.repositories;

import com.ciandt.summit.bootcamp2022.domain.Music;
import com.ciandt.summit.bootcamp2022.domain.ports.repositories.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.exceptions.InvalidParameterException;
import com.ciandt.summit.bootcamp2022.infra.adapters.entities.MusicEntity;
import com.ciandt.summit.bootcamp2022.infra.adapters.entities.PlaylistEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@Component
public class PlaylistRepository implements PlaylistRepositoryPort {

    private final SpringPlaylistRepository springPlaylistRepository;

    public PlaylistRepository(SpringPlaylistRepository springPlaylistRepository) {
        this.springPlaylistRepository = springPlaylistRepository;
    }

    @Override
    public void addMusicToPlaylist(String playlistId, Music music) throws EntityNotFoundException {
        try {
        PlaylistEntity playlistEntity = this.springPlaylistRepository.getById(playlistId);
        playlistEntity.getMusics().add(new MusicEntity(music));
        this.springPlaylistRepository.save(playlistEntity);
        } catch(EntityNotFoundException e) {
            throw new EntityNotFoundException("Playlist ou música inexistentes.");
        }

    }

    @Override
    public void removeMusicFromPlaylist(String playlistId, String musicId) throws EntityNotFoundException {
        try {
        PlaylistEntity playlistEntity = this.springPlaylistRepository.getById(playlistId);
        playlistEntity.setMusics(playlistEntity.getMusics().stream().filter(musicEntity -> !musicEntity.getId().equals(musicId)).collect(Collectors.toList()));
        this.springPlaylistRepository.save(playlistEntity);
        } catch(EntityNotFoundException e) {
            throw new EntityNotFoundException("Playlist ou música inexistentes.");
        }
    }
}
