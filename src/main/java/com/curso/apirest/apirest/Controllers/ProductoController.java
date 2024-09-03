package com.curso.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.apirest.apirest.Repositories.ProductoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.curso.apirest.apirest.Entities.Producto;

@RequestMapping("/productos")
@RestController
public class ProductoController {


    @Autowired
    private ProductoRepository productoRepository;

    //get ALL
    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
        //Esto es lo mismo que select * from producto
    }

    //get por id
    @GetMapping("/{id}")
    public Producto getProducto(@PathVariable Integer id, @RequestBody Producto producto){
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con id "+ " " + id));
    }

    @PostMapping
    //gracias a requestBody, pasamos como parametro la clase de tipo producto con todos sus atributos al parametro producto
    public Producto createProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }



    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Integer id, @RequestBody Producto detalleProducto){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con id "+ " " + id));

        producto.setNombre(detalleProducto.getNombre());
        producto.setPrecio(detalleProducto.getPrecio());

        return productoRepository.save(producto);

    }

    @DeleteMapping("/{id}")
    public String borrarProducto(@PathVariable Integer id){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con id "+ " " + id));
        productoRepository.delete(producto);
        return "EL producto con el ID: " + id + " fue eliminado correctamente";
    }
    

}
