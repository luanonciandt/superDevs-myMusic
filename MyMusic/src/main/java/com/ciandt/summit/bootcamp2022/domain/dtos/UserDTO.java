package com.ciandt.summit.bootcamp2022.domain.dtos;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private PlaylistDTO playlist;
    private UserTypeDTO userType;
    public UserDTO() {

    }

    public UserDTO(String id, String name, PlaylistDTO playlist, UserTypeDTO userType) {
        this.id = id;
        this.name = name;
        this.playlist = playlist;
        this.userType = userType;
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

    public UserTypeDTO getUserType() {
        return userType;
    }

    public void setUserType(UserTypeDTO userType) {
        this.userType = userType;
    }
}
