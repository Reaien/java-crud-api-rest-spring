package com.curso.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.apirest.apirest.Entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    

}
