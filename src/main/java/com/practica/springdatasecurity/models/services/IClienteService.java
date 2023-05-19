package com.practica.springdatasecurity.models.services;

import com.practica.springdatasecurity.models.entity.Cliente;
import com.practica.springdatasecurity.models.entity.Factura;
import com.practica.springdatasecurity.models.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {

    public List<Cliente> findAll();

    public Page<Cliente> findAll(Pageable pageable);

    public  void save(Cliente cliente);


    public Cliente findOne(Long id);

    public Cliente fetchByIdWithFacturas(Long id);

    public void delete(Long id);

    public List<Producto> findByNombre(String termino);

    public void saveFactura(Factura factura);

    public Producto findProductoById(Long id);

    public Factura findFacturaById(Long id);

    public void deleteFactura(Long id);

    public Factura fetchFacturaByIdWithClienteWhithItemFacturaWithProducto(Long id);




}
