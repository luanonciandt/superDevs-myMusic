package com.ciandt.summit.bootcamp2022.domain.adapters.services;

import com.ciandt.summit.bootcamp2022.domain.Artist;
import com.ciandt.summit.bootcamp2022.domain.Music;
import com.ciandt.summit.bootcamp2022.domain.dtos.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.ports.repositories.MusicRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class MusicServiceTest {

    @InjectMocks
    private MusicServiceImpl musicService;

    @Mock
    private MusicRepositoryPort musicRepository;

    @Test
    void searchMusicsByFilterTest() {
        Music music = new Music("1", "Na sua estante", new Artist("1", "Pitty"));
        Set<Music> musicSet = new HashSet<>(Set.of(music));

        when(musicRepository.getMusicsByFilter("na")).thenReturn(musicSet);

        List<MusicDTO> result = musicService.getMusicsByFilter("na");

        assertTrue(result.size() > 0);
    }

    @Test
    void nameNotFoundTest() {

        when(musicRepository.getMusicsByFilter("total")).thenReturn(new HashSet<>());

        List<MusicDTO> empty = musicService.getMusicsByFilter("total");

        assertFalse(empty.size() > 0);
    }

}
