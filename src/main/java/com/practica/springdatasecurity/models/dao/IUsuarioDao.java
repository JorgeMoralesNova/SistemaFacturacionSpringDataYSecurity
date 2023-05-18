package com.practica.springdatasecurity.models.dao;

import com.practica.springdatasecurity.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {

    public Usuario findByUsername(String username);

}
