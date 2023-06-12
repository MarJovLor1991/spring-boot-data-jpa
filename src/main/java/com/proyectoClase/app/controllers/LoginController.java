package com.proyectoClase.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model, Principal principal, RedirectAttributes flash){
        if(principal != null){
            flash.addFlashAttribute("info", "La sesión ya está iniciada.");
            return "redirect:/";
        }
        if(error != null){
            model.addAttribute("error", "Datos de log in incorrectos.");
        }
        if(logout != null){
            model.addAttribute("success", "Sesion cerrada.");
        }
    return "login";
    }
}
