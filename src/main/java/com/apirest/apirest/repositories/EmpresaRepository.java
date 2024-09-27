package com.apirest.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.apirest.entities.Empresas;

public interface EmpresaRepository extends JpaRepository<Empresas, Integer>{

}
