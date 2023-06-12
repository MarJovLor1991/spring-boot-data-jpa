package com.proyectoClase.app.models.service;

import com.proyectoClase.app.models.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductoService {

    List<Producto> findAll();
    void save(Producto producto);
    void delete(Long id);
    Page<Producto> findAll(Pageable pageable);
    Producto findById(Long id);
    public void actualizarItem(Long id);
}
