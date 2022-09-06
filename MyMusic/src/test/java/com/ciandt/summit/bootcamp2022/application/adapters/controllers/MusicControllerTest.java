package com.ciandt.summit.bootcamp2022.application.adapters.controllers;

import com.ciandt.summit.bootcamp2022.domain.dtos.ArtistDTO;
import com.ciandt.summit.bootcamp2022.domain.dtos.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicServicePort;
import com.ciandt.summit.bootcamp2022.exceptions.InvalidParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(MusicController.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebAppConfiguration
@AutoConfigureMockMvc(addFilters = false)
public class MusicControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MusicServicePort musicService;

    List<MusicDTO> musicDTOS;

    @BeforeEach
    void setup() {
        musicDTOS = new ArrayList<>();
        musicDTOS.add(new MusicDTO("abcd-123", "Maracatu Atomico", new ArtistDTO("efgh-456", "Chico Science")));
    }

    @Test
    void findMusicByNameTest() throws Exception {
        Mockito.when(musicService.getMusicsByFilter("maracatu")).thenReturn(musicDTOS);
        mvc.perform(get("/api/musicas").param("name", "maracatu")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void findMusicByArtistNameTest() throws Exception {
        Mockito.when(musicService.getMusicsByFilter("chi")).thenReturn(musicDTOS);
        mvc.perform(get("/api/musicas").param("name", "chi")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void nameNotFoundTest() throws Exception {
        Mockito.when(musicService.getMusicsByFilter("abracadabra")).thenReturn(new ArrayList<>());
        mvc.perform(get("/api/musicas").param("name", "abracadabra")).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void nameLessThan2CharTest() throws Exception {
        Mockito.when(musicService.getMusicsByFilter("c")).thenThrow(new InvalidParameterException("O termo buscado deve ter pelo menos 2 caracteres."));
        mvc.perform(get("/api/musicas").param("name", "c")).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
