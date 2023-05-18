package com.practica.springdatasecurity.models.dao;

import com.practica.springdatasecurity.models.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IClienteDao extends JpaRepository<Cliente, Long> {

    //buscar cliente relacionado a facturas

    @Query("select c from Cliente c left join fetch c.facturas f where c.id=?1")
    public Cliente fechtByIdWithFacturas(Long id);
}
