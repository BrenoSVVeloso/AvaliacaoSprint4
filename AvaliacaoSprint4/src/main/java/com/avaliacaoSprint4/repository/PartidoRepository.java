package com.avaliacaoSprint4.repository;

import java.util.List;

import com.avaliacaoSprint4.entity.Partido;
import com.avaliacaoSprint4.entity.FolderEnum.Ideologia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long>{

    List<Partido> findByIdeologia(Ideologia ideologiaFiltrada);

    Partido findByNome(String nome);

    void deleteByNome(String string);

    void deleteByIdeologia(Ideologia esquerda);
    
}
