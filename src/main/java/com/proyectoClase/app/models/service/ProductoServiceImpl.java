package com.proyectoClase.app.models.service;

import com.proyectoClase.app.models.dao.IProductoDao;
import com.proyectoClase.app.models.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService{
    @Autowired
    IProductoDao productoDao;
    @Override
    public List<Producto> findAll() {
        return (List<Producto>) productoDao.findAll();
    }

    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Long id) {
       Producto producto = productoDao.findById(id).orElse(null);
        if(producto != null) {
            productoDao.delete(producto);
        }
    }

    @Override
    public Page<Producto> findAll(Pageable pageable) {
        return productoDao.findAll(pageable);
    }

    @Override
    public Producto findById(Long id) {
        return productoDao.findById(id).orElse(null);
    }
    @Transactional
    public void actualizarItem(Long id){
        productoDao.actualizarProductoId(id);
    }


}
