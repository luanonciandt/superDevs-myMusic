package com.ciandt.summit.bootcamp2022.infra.adapters.entities;

import javax.persistence.*;

@Entity
@Table(name = "TipoUsuario")
public class UserTypeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="Id")
    private String id;

    @Column(name="Descricao")
    @OneToOne(mappedBy = "user")
    private String type;

    public UserTypeEntity() {

    }
    public UserTypeEntity(String id, String type) {
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
}

