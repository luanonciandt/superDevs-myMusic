package com.ciandt.summit.bootcamp2022.infra.adapters.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TipoUsuario")
public class UserTypeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="Id")
    private String id;

    @Column(name="Descricao")
    @OneToMany(mappedBy = "userTypeEntity")
    private List<UserEntity> type;

    public UserTypeEntity() {

    }

    public UserTypeEntity(String id, List<UserEntity> type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<UserEntity> getType() {
        return type;
    }

    public void setType(List<UserEntity> type) {
        this.type = type;
    }
}

