package com.proyectoClase.app.models.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "authorities", uniqueConstraints = {@UniqueConstraint(columnNames = {"autority"})})
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String autority;
    @Column(name = "usuario_id")
    private Long usuarioId;

    public Role() {
    }
    public Role(Long usuarioId, String autority) {
        this.autority = autority;
        this.usuarioId = usuarioId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutority() {
        return autority;
    }

    public void setAutority(String autority) {
        this.autority = autority;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
