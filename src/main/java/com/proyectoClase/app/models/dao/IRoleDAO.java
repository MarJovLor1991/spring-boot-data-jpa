package com.proyectoClase.app.models.dao;

import com.proyectoClase.app.models.entity.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IRoleDAO extends PagingAndSortingRepository<Role, Long> {

    @Modifying
    @Query(value = "DELETE FROM authorities WHERE id = :id", nativeQuery = true)
    void borrarRoles(Long id);
}
