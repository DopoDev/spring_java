package com.apirest.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.entities.Producto;
import com.apirest.apirest.repositories.ProductoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Todas las direcciones para poder consumir la información. 
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository; 

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));
    }
    
    
    @PostMapping
    public Producto createProducto(@RequestBody Producto p) {
        return productoRepository.save(p);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto p) {
        Producto productoCambio = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

        productoCambio.setNombre(p.getNombre());
        productoCambio.setPrecio(p.getPrecio());

        return productoRepository.save(productoCambio);
    }
    
    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id){
        Producto productoEliminar = productoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("No se encontró el producto con ID: " + id));

        productoRepository.delete(productoEliminar);
        return "Se ha eliminado con exito el producto";
    }

}
