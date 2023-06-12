package com.proyectoClase.app.models.dao;

import com.proyectoClase.app.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioDAO extends JpaRepository<Usuario, Long> {

    public Usuario findByNombre(String username);

    @Modifying
    @Query(value = "DELETE FROM usuarios WHERE id = :id", nativeQuery = true)
    void borrarUsuario(Long id);
}
