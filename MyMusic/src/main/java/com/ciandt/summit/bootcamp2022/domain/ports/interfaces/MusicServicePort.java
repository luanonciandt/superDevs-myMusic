package com.ciandt.summit.bootcamp2022.domain.ports.interfaces;

import com.ciandt.summit.bootcamp2022.domain.dtos.MusicDTO;

import java.util.List;
import java.util.Set;

public interface MusicServicePort {
    List<MusicDTO> searchMusics();
}
