package com.ciandt.summit.bootcamp2022.infra.adapters.entities;

import com.ciandt.summit.bootcamp2022.domain.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TipoUsuario")
public class UserTypeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="Id")
    private String id;

    @Column(name="Descricao")
    private String type;

    @JsonIgnore
    @OneToMany(mappedBy = "userType")
    private Set<UserEntity> users = new HashSet<>();

    public UserTypeEntity() {

    }

    public UserTypeEntity(String id, String type) {
        this.id = id;
        this.type = type;
    }
    public UserType toUserType(){
        return new UserType(this.id, this.type);
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

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}

