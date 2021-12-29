package com.avaliacaoSprint4.repository;

import java.util.List;

import com.avaliacaoSprint4.entity.Associado;
import com.avaliacaoSprint4.entity.FolderEnum.CargoPolitico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long>{

    Associado findByNome(String nome);

    List<Associado> findAllByOrderByNomeAsc();

    List<Associado> findAllByPartido(String partido);

    List<Associado> findByCargo(CargoPolitico cargo);

    void deleteByNome(String nome);

    void deleteByCargo(CargoPolitico prefeito);    
}
