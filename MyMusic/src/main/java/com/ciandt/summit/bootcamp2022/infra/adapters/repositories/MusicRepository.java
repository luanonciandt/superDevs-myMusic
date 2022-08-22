package com.ciandt.summit.bootcamp2022.infra.adapters.repositories;

import com.ciandt.summit.bootcamp2022.domain.Music;
import com.ciandt.summit.bootcamp2022.domain.ports.repositories.MusicRepositoryPort;
import com.ciandt.summit.bootcamp2022.infra.adapters.entities.MusicEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MusicRepository implements MusicRepositoryPort {

    private final SpringMusicRepository springMusicRepository;

    public MusicRepository(SpringMusicRepository springMusicRepository) {
        this.springMusicRepository = springMusicRepository;
    }

    @Override
    public List<Music> getMusicsByFilter() {
        List<MusicEntity> musicEntities = this.springMusicRepository.findAll();

        return musicEntities.stream().map(MusicEntity::toMusic).collect(Collectors.toList());
    }
}
