package com.proyectoClase.app.controllers;

import com.proyectoClase.app.models.entity.Role;
import com.proyectoClase.app.models.entity.Usuario;
import com.proyectoClase.app.models.service.JpaUserDetailsService;
import com.proyectoClase.app.util.paginator.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/usuarios")
@SessionAttributes("usuario")
public class UsuarioController {
    Role rol;
    @Autowired
    private JpaUserDetailsService usuarioService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(@RequestParam(name = "page", defaultValue = "0") int page,
                         Model model) {
        Pageable pageRequest = PageRequest.of(page, 4);
        Page<Usuario> usuarios = usuarioService.listAllUsuario(pageRequest);
        PageRender<Usuario> pageRender = new PageRender<>("/listar", usuarios);
        model.addAttribute("titulo", "Listado de usuarios");
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("page", pageRender);
        return "listarUsuarios";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            for (Role rol : usuarioService.findById(id).getRoles()) {
                usuarioService.deleteRol(rol.getId());
            }
            usuarioService.delete(id);
            flash.addFlashAttribute("success", "Usuario eliminado con éxito!");
        }
        return "redirect:/usuarios/listar";
    }
    @RequestMapping(value = "/formUsuario/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
        Usuario usuario;
        if (id > 0) {
            usuario = usuarioService.findById(id);
            if (usuario == null) {
                flash.addFlashAttribute("error", "El ID del usuario no existe en la BBDD!");
                return "redirect:/producto/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID del usuario no puede ser cero!");
            return "redirect:/usuarios/listar";
        }
        model.put("usuario", usuario);
        model.put("titulo", "Editar Usuario");
        return "formUsuario";
    }
    @RequestMapping(value = "/formUsuario", method = RequestMethod.POST)
    public String guardar(@Valid Usuario usuario, BindingResult result, Model model,
                          RedirectAttributes flash, SessionStatus status) {
    try {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Usuario");
            return "formUsuario";
        }
        String mensajeFlash = (usuario.getId() != null) ? "Usuario editado con éxito!" : "Usuario creado con éxito!";
        String contraseñaEncod = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(contraseñaEncod);
        Usuario existe = usuarioService.findByNombre(usuario.getNombre());
        if (existe != null && existe.getPassword() == usuario.getPassword()) {
            flash.addFlashAttribute("error", "El nombre de usuario ya existe");
            return "redirect:/usuarios/formUsuario";
        } else if(usuario.getId()==null){
            usuarioService.saveUsuario(usuario);
            Usuario guardado=usuarioService.findByNombre(usuario.getNombre());
            if(usuario.getEnabled()){
                rol = new Role(guardado.getId(),"ROLE_ADMIN");
            }else{
                rol = new Role(guardado.getId(),"ROLE_USER");
            }
            usuarioService.saveRole(rol);
            status.setComplete();
            flash.addFlashAttribute("success", mensajeFlash);
            return "redirect:/usuarios/listar";
        }else{
            if (usuario.getId() > 0) {
                for (Role rol : usuarioService.findById(usuario.getId()).getRoles()) {
                    usuarioService.deleteRol(rol.getId());
                }
                if(usuario.getEnabled()){
                    rol = new Role(usuario.getId(),"ROLE_ADMIN");
                    usuarioService.saveUsuario(usuario);
                }else{
                    rol = new Role(usuario.getId(),"ROLE_USER");
                    usuarioService.saveUsuario(usuario);
                }
                usuarioService.saveRole(rol);
            }
            status.setComplete();
            flash.addFlashAttribute("success", mensajeFlash);
            return "redirect:/usuarios/listar";
        }
    }catch(Exception e){
        flash.addFlashAttribute("error", "El nombre de usuario ya existe");
        return "redirect:/usuarios/formUsuario";
    }

    }

    @RequestMapping(value = "/formUsuario")
    public String crear(Map<String, Object> model) {
        Usuario usuario = new Usuario();
        model.put("usuario", usuario);
        model.put("titulo", "Formulario de Usuario");
        return "formUsuario";
    }

}
