package com.ciandt.summit.bootcamp2022.application.adapters.controllers;

import com.ciandt.summit.bootcamp2022.domain.dtos.MusicDTO;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/musicas")
public class MusicController {

    private final MusicServicePort musicServicePort;

    public MusicController(MusicServicePort musicServicePort) {
        this.musicServicePort = musicServicePort;
    }

    @GetMapping
    public ResponseEntity<String> getMusic() {
        return ResponseEntity.ok("67f5976c-eb1e-404e-8220-2c2a8a23be47");
    }

    @GetMapping("/teste")
    public ResponseEntity<List<MusicDTO>> getAllMusics() {
        return ResponseEntity.ok().body(this.musicServicePort.searchMusics());
    }
}
