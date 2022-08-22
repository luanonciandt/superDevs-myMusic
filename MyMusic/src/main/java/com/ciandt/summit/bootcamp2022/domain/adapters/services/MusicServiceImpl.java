package com.ciandt.summit.bootcamp2022.domain.adapters.services;

import com.ciandt.summit.bootcamp2022.domain.Music;
import com.ciandt.summit.bootcamp2022.domain.dtos.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicServicePort;
import com.ciandt.summit.bootcamp2022.domain.ports.repositories.MusicRepositoryPort;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MusicServiceImpl implements MusicServicePort {


    private final MusicRepositoryPort musicRepository;

    public MusicServiceImpl(MusicRepositoryPort musicRepository) {
        this.musicRepository = musicRepository;
    }
    @Override
    public List<MusicDTO> searchMusics() {
        List<Music> musics = this.musicRepository.getMusicsByFilter();
        // business logic
        return musics.stream().map(Music::toMusicDTO).collect(Collectors.toList());
    }
}
