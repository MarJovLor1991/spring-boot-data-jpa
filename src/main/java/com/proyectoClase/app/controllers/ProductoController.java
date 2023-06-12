package com.proyectoClase.app.controllers;

import com.proyectoClase.app.models.entity.Producto;
import com.proyectoClase.app.models.service.IProductoService;
import com.proyectoClase.app.util.paginator.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/producto")
@SessionAttributes("producto")
public class ProductoController {
    @Autowired
    IProductoService productoService;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageRequest = PageRequest.of(page, 4);
        Page<Producto> productos = productoService.findAll(pageRequest);
        PageRender<Producto> pageRender = new PageRender<Producto>("/producto/listar", productos);
        model.addAttribute("titulo", "Listado de productos");
        model.addAttribute("productos", productos);
        model.addAttribute("page", pageRender);
        return "listarProductos";
    }

    @RequestMapping(value = "/formProducto")
    public String crear(Map<String, Object> model) {
        Producto producto = new Producto();
        model.put("producto", producto);
        model.put("titulo", "Formulario de Producto");
        return "formProducto";
    }

    @RequestMapping(value = "/formProducto", method = RequestMethod.POST)
    public String guardar(@Valid Producto producto, BindingResult result, Model model,
                           RedirectAttributes flash, SessionStatus status) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Producto");
            return "formProducto";
        }
        String mensajeFlash = (producto.getId() != null) ? "Producto editado con éxito!" : "Producto creado con éxito!";
        productoService.save(producto);
        status.setComplete();
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/producto/listar";
    }

    @RequestMapping(value = "/formProducto/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        Producto producto;
        if (id > 0) {
            producto = productoService.findById(id);
            if (producto == null) {
                flash.addFlashAttribute("error", "El ID del producto no existe en la BBDD!");
                return "redirect:/producto/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del producto no puede ser cero!");
            return "redirect:/producto/listar";
        }
        model.put("producto", producto);
        model.put("titulo", "Editar Producto");
        return "formProducto";
    }
//Eliminar es para futuras actualizaciones, está creado pero anulado.
    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            productoService.actualizarItem(id);
            productoService.delete(id);
            flash.addFlashAttribute("success", "Producto eliminado con éxito!");
        }
        return "redirect:/producto/listar";
    }
}
