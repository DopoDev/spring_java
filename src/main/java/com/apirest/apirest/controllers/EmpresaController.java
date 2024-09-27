package com.apirest.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.entities.Empresas;
import com.apirest.apirest.repositories.EmpresaRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresarRepository; 

    @GetMapping("")
    public List<Empresas> getEmpresas() {
        return empresarRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Empresas getEmpresaById(@PathVariable Integer id) {
        return empresarRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro la empresa con ID: " + id));
    }
    
    @PostMapping("")
    public Empresas postEmpresa(@RequestBody Empresas e) {
        return empresarRepository.save(e);
    }
    
    @PutMapping("/{id}")
    public Empresas putEmpresa(@PathVariable Integer id, @RequestBody Empresas e) {
        Empresas empresaEditar = empresarRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró la empresa con id: " + id));

        empresaEditar.setNombreEmpresa(e.getNombreEmpresa());
        empresaEditar.setPais(e.getPais());

        return empresarRepository.save(empresaEditar);
    }

    @DeleteMapping("/{id}")
    public Empresas deleteEmpresa(@PathVariable Integer id){
        Empresas empresaEliminar = empresarRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro la empresa a eliminar con el ID: " + id));
        
        empresarRepository.delete(empresaEliminar);
        return empresaEliminar; 
    }
}
