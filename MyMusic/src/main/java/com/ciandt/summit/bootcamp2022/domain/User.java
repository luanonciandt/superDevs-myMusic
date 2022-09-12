package com.ciandt.summit.bootcamp2022.domain;

import com.ciandt.summit.bootcamp2022.domain.dtos.UserDTO;

public class User {
    private String id;
    private String name;
    private Playlist playlist;
    private UserType userType;

    public User(String id, String name, Playlist playlist, UserType userType) {
        this.id = id;
        this.name = name;
        this.playlist = playlist;
        this.userType = userType;
    }

    public User(UserDTO userDTO){
        this.id = userDTO.getId();
        this.name = userDTO.getName();
        this.playlist = new Playlist(this.id);
        this.userType = new UserType(userDTO.getUserType().getId(), userDTO.getUserType().getType());
    }
    public User() {
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

    public UserDTO toUserDTO() {
        return new UserDTO(this.id, this.name, this.playlist.toPlaylistDTO(), this.userType.toUserTypeDTO());
    }
}
