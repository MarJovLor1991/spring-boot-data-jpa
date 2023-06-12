package com.proyectoClase.app;

import com.proyectoClase.app.auth.handler.LoginSuccessHandler;
import com.proyectoClase.app.models.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;


@Configuration
public class SpringSecurityConfig {
    @Autowired
    private LoginSuccessHandler successHandler;
    @Autowired
    DataSource dataSource;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JpaUserDetailsService userDetailService;

    @Autowired
    public void userDetailsService(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers( "/css/**", "/js/**", "/images/**").permitAll()
                .antMatchers("/ver/**").hasAnyRole("ADMIN")
                .antMatchers("/uploads/**").hasAnyRole("USER")
                .antMatchers("/form/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/eliminar/**").hasAnyRole("ADMIN")
                .antMatchers("/factura/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/producto/**").hasAnyRole("ADMIN")
                .antMatchers("/usuarios/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .successHandler(successHandler)
                    .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/error_403");
        return http.build();
    }
}
