package com.ciandt.summit.bootcamp2022.application.adapters.controllers;


import com.ciandt.summit.bootcamp2022.application.dtos.MusicDataDTO;
import com.ciandt.summit.bootcamp2022.domain.ports.interfaces.MusicServicePort;
import com.ciandt.summit.bootcamp2022.exceptions.InvalidParameterException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Musicas buscadas com sucesso"),
        @ApiResponse(responseCode = "400", description = "Parametro de busca invalido"),
        @ApiResponse(responseCode = "204", description = "Nenhuma musica encontrada")
})
@RestController
@RequestMapping("/api/musicas")
public class MusicController {

    final MusicServicePort musicServicePort;

    public MusicController(MusicServicePort musicServicePort) {
        this.musicServicePort = musicServicePort;
    }

    @Operation(description = "Buscar novas musicas")
    @GetMapping
    public ResponseEntity<MusicDataDTO> findMusicByName(@RequestParam("name") String name) {
        if(name == null || name.length() < 2)
            throw new InvalidParameterException("O termo buscado deve ter pelo menos 2 caracteres.");
        if(musicServicePort.getMusicsByFilter(name).isEmpty())
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(new MusicDataDTO(musicServicePort.getMusicsByFilter(name)));
    }

}
