package com.ciandt.summit.bootcamp2022.application.adapters.controllers;

import com.ciandt.summit.bootcamp2022.application.dtos.MusicDataDTO;
import com.ciandt.summit.bootcamp2022.domain.dtos.UserDTO;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.PlaylistServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {

    private final PlaylistServicePort playlistServicePort;

    public PlaylistController(PlaylistServicePort playlistServicePort) {
        this.playlistServicePort = playlistServicePort;
    }

    @PutMapping("/{playlistId}/{user}/musicas")
    public ResponseEntity<MusicDataDTO> addMusicToPlaylist(@PathVariable String playlistId, @RequestBody MusicDataDTO data, @PathVariable String user) {
        this.playlistServicePort.addMusicToPlaylist(playlistId, data.getData().get(0), user);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @PutMapping("/{playlistId}/musicas/{musicId}")
    public ResponseEntity<Void> removeMusicFromPlaylist(@PathVariable String playlistId, @PathVariable String musicId) {
        this.playlistServicePort.removeMusicFromPlaylist(playlistId, musicId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
