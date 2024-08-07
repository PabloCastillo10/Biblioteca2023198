package com.pablocastillo.webapp.biblioteca.service;

import java.util.List;

import com.pablocastillo.webapp.biblioteca.model.Cliente;

public interface IClienteService {

    public List<Cliente> listaClientes();

    public Cliente guardarCliente(Cliente cliente);

    public Cliente buscarClientePorId(Long dpi);

    public void eliminarCliente(Cliente cliente); 
}
