package com.ciandt.summit.bootcamp2022.application.adapters.controllers;


import com.ciandt.summit.bootcamp2022.application.dtos.MusicDataDTO;
import com.ciandt.summit.bootcamp2022.domain.dtos.ArtistDTO;
import com.ciandt.summit.bootcamp2022.domain.dtos.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.PlaylistServicePort;
import com.ciandt.summit.bootcamp2022.exceptions.InvalidParameterException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@WebMvcTest(PlaylistController.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebAppConfiguration
@AutoConfigureMockMvc(addFilters = false)
public class PlaylistControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PlaylistServicePort playlistService;

    public static String parseJson(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addMusicToPlaylistTest() throws Exception {
        MusicDTO musicDTO = new MusicDTO("1", "Malandragem", new ArtistDTO("1", "Cassia Eller"));
        MusicDataDTO requestData = new MusicDataDTO();
        requestData.setData(List.of(musicDTO));
        String playlistId = "abc";

        Mockito.doNothing().when(playlistService).addMusicToPlaylist(playlistId, musicDTO);

        mvc.perform(put("/api/playlists/" + playlistId +"/musicas")
                .content(parseJson(requestData))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @Disabled
    void musicOrPlaylistDontExistsTest() throws Exception {
        MusicDTO musicDTO = new MusicDTO("0", "Because I Got High", new ArtistDTO("1", "Afroman"));
        MusicDataDTO requestData = new MusicDataDTO();
        requestData.setData(List.of(musicDTO));

        String playlistId = "7490";

        Mockito.doThrow(new InvalidParameterException("Playlist ou música inexistentes."))
                .when(playlistService).addMusicToPlaylist(playlistId, musicDTO);

        mvc.perform(put("/api/playlists/" + playlistId +"/musicas")
                .content(parseJson(requestData))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void removeMusicFromPlaylistTest() throws Exception {
        String playlistId = "123";
        String musicId = "456";

        Mockito.doNothing().when(playlistService).removeMusicFromPlaylist(playlistId, musicId);

        mvc.perform(put("/api/playlists/" + playlistId + "/musicas/" + musicId))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    void musicDontExistsTest() throws Exception {
        String playlistId = "0x001";
        String musicId = "0x0002";

        Mockito.doThrow(new InvalidParameterException("Playlist ou música inexistentes."))
                .when(playlistService).removeMusicFromPlaylist(playlistId, musicId);

        mvc.perform(put("/api/playlists/" + playlistId + "/musicas/" + musicId))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

}
