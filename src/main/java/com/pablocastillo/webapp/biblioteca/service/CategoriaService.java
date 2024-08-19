package com.pablocastillo.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pablocastillo.webapp.biblioteca.model.Categoria;
import com.pablocastillo.webapp.biblioteca.repository.CategoriaRepository;

@Service
public class CategoriaService implements ICategoriaService {


    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listaCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
         return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria busCategoriaPorId(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarCategoria(Categoria categoria) {
        categoriaRepository.delete(categoria);
    }


    
    @Override
    public Boolean verificarCategoriaDuplicada(Categoria categoria) {
        Boolean flag = Boolean.FALSE;
        List<Categoria> categorias = listaCategorias();

        for (Categoria c : categorias) {
            if(c.getNombreCategoria().equals(categoria.getNombreCategoria()) && !c.getId().equals(categoria.getId())){
                flag = Boolean.TRUE;
            }
        }
        return flag;
    }
}
