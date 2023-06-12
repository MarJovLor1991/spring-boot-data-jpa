package com.proyectoClase.app.models.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.proyectoClase.app.models.entity.Producto;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProductoDao extends PagingAndSortingRepository<Producto, Long> {

	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findByNombre(String term);

	public List<Producto> findByNombreLikeIgnoreCase(String term);

	@Modifying
	@Query(value = "UPDATE facturas_items SET producto_id = NULL WHERE producto_id = :idProducto", nativeQuery = true)
	void actualizarProductoId(Long idProducto);
}