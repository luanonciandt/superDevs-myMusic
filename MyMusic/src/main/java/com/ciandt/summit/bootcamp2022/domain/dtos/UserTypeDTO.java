package com.ciandt.summit.bootcamp2022.domain.dtos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserTypeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String type;
    private Set<UserDTO> users = new HashSet<>();

    public UserTypeDTO() {
    }

    public UserTypeDTO(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }
}
