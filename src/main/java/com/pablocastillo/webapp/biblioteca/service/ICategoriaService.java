package com.pablocastillo.webapp.biblioteca.service;

import java.util.List;

import com.pablocastillo.webapp.biblioteca.model.Categoria;

public interface ICategoriaService {

    public List<Categoria> listaCategorias();

    public Categoria guardarCategoria(Categoria categoria);

    public Categoria busCategoriaPorId(Long id);

    public void eliminarCategoria(Categoria categoria);
}
