package com.ciandt.summit.bootcamp2022.domain.dtos;

import com.ciandt.summit.bootcamp2022.domain.Playlist;

public class UserDTO {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private PlaylistDTO playlist;

    public UserDTO() {

    }

    public UserDTO(String id, String name, PlaylistDTO playlist) {
        this.id = id;
        this.name = name;
        this.playlist = playlist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlaylistDTO getPlayListId() {
        return playlist;
    }

    public void setPlayListId(PlaylistDTO playlist) {
        this.playlist = playlist;
    }
}
