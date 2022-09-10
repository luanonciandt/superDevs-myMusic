package com.ciandt.summit.bootcamp2022.domain;

import com.ciandt.summit.bootcamp2022.domain.dtos.UserTypeDTO;

import java.util.HashSet;
import java.util.Set;

public class UserType {
    private String id;
    private String type;
    private Set<User> users = new HashSet<>();

    public UserType() {
    }

    public UserType(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public UserTypeDTO toUserTypeDTO(){
        return new UserTypeDTO(this.id, this.type);
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
