package com.avaliacaoSprint4.service;

import java.util.List;

import javax.validation.Valid;

import com.avaliacaoSprint4.controller.Associado.DTO.AssociadoDTO;
import com.avaliacaoSprint4.controller.Partido.DTO.PartidoDTO;
import com.avaliacaoSprint4.controller.Partido.form.PartidoFormDTO;

import org.springframework.http.ResponseEntity;



public interface PartidoService {

    ResponseEntity<PartidoDTO> savePartido(@Valid PartidoFormDTO form);

    ResponseEntity<List<PartidoDTO>> getPartidos(String ideologia);

    ResponseEntity<PartidoDTO> listPartido(Long id);

    ResponseEntity<List<AssociadoDTO>> listAssociadoFromPartido(Long id);

    ResponseEntity<PartidoDTO> updatePartido(Long id, PartidoFormDTO form);

    ResponseEntity<?> deletePartido(Long id);
    
}
