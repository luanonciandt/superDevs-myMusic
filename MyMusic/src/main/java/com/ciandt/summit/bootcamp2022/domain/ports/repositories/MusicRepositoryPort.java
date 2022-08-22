package com.ciandt.summit.bootcamp2022.domain.ports.repositories;

import com.ciandt.summit.bootcamp2022.domain.Music;

import java.util.List;

public interface MusicRepositoryPort {
    List<Music> getMusicsByFilter(); //-> provisory
}
