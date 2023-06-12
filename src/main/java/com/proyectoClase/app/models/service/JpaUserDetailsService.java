package com.proyectoClase.app.models.service;

import com.proyectoClase.app.models.dao.IRoleDAO;
import com.proyectoClase.app.models.dao.IUsuarioDAO;
import com.proyectoClase.app.models.entity.Role;
import com.proyectoClase.app.models.entity.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    IUsuarioDAO usuarioDAO;
    @Autowired
    IRoleDAO roleDAO;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario=usuarioDAO.findByNombre(username);
        List<GrantedAuthority> autorityes = new ArrayList<>();
        for(Role rol : usuario.getRoles()){
            autorityes.add(new SimpleGrantedAuthority(rol.getAutority()));
        }
        return new User(usuario.getNombre(), usuario.getPassword(), autorityes);
    }
    public Page<Usuario> listAllUsuario(Pageable pageable){
        return usuarioDAO.findAll(pageable);
    }
    @Transactional
    public void delete(Long id) {
        usuarioDAO.borrarUsuario(id);
    }
    @Transactional
    public void saveUsuario(Usuario usuario) {
        usuarioDAO.save(usuario);
    }
    @Transactional
    public Usuario findById(Long id) {
        return usuarioDAO.findById(id).orElse(null);
    }
    @Transactional
    public Usuario findByNombre(String nombre) {
        return usuarioDAO.findByNombre(nombre);
    }
    @Transactional
    public void saveRole(Role role) {roleDAO.save(role);}
    @Transactional
    public void deleteRol(Long id) {roleDAO.borrarRoles(id);}
}
