package com.ciandt.summit.bootcamp2022.infra.adapters.repositories;

import com.ciandt.summit.bootcamp2022.domain.Music;
import com.ciandt.summit.bootcamp2022.domain.ports.repositories.PlaylistRepositoryPort;
import com.ciandt.summit.bootcamp2022.exceptions.InvalidParameterException;
import com.ciandt.summit.bootcamp2022.infra.adapters.entities.MusicEntity;
import com.ciandt.summit.bootcamp2022.infra.adapters.entities.PlaylistEntity;
import com.ciandt.summit.bootcamp2022.infra.adapters.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PlaylistRepository implements PlaylistRepositoryPort {

    private final SpringPlaylistRepository springPlaylistRepository;
    private final SpringMusicRepository springMusicRepository;

    private  final SpringUserRepository springUserRepository;

    public PlaylistRepository(SpringPlaylistRepository springPlaylistRepository, SpringMusicRepository springMusicRepository, SpringUserRepository springUserRepository) {
        this.springPlaylistRepository = springPlaylistRepository;
        this.springMusicRepository = springMusicRepository;
        this.springUserRepository = springUserRepository;
    }

    @Override
    public void addMusicToPlaylist(String playlistId, Music music, String user)  {
        if(!springPlaylistRepository.existsById(playlistId) || !springMusicRepository.existsById(music.getId()))
            throw new InvalidParameterException("Playlist ou música inexistentes.");
        UserEntity userEntity = springUserRepository.getUserById(user);
        PlaylistEntity playlistEntity = this.springPlaylistRepository.getById(playlistId);

        playlistEntity.getMusics().add(new MusicEntity(music));
        this.springPlaylistRepository.save(playlistEntity);

        if(userEntity.getUserType().getType().equals("Comum") && playlistEntity.getMusics().size() > 5){
            throw new InvalidParameterException("Você atingiu o número máximo de músicas em sua playlist. Para adicionar mais músicas contrate o plano premium.");
        }

    }

    @Override
    public void removeMusicFromPlaylist(String playlistId, String musicId) {
        if(!springPlaylistRepository.existsById(playlistId) || !springMusicRepository.existsById(musicId))
            throw new InvalidParameterException("Playlist ou música inexistentes.");
        PlaylistEntity playlistEntity = this.springPlaylistRepository.getById(playlistId);
        playlistEntity.setMusics(playlistEntity.getMusics().stream().filter(musicEntity -> !musicEntity.getId().equals(musicId)).collect(Collectors.toList()));
        this.springPlaylistRepository.save(playlistEntity);
    }
}
