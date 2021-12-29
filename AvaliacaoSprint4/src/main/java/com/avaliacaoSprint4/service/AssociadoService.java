package com.avaliacaoSprint4.service;

import java.util.List;

import javax.validation.Valid;

import com.avaliacaoSprint4.controller.Associado.DTO.AssociadoDTO;
import com.avaliacaoSprint4.controller.Associado.form.AssociadoFormDTO;
import com.avaliacaoSprint4.controller.Associado.form.VinculaAssociadoPartidoForm;

import org.springframework.http.ResponseEntity;



public interface AssociadoService {
    
    ResponseEntity<AssociadoDTO> saveAssociado(AssociadoFormDTO body);

    ResponseEntity<List<AssociadoDTO>> getAssociados(String cargo, Boolean OrdNome);

    ResponseEntity<AssociadoDTO> saveAssociadoToPartido(@Valid VinculaAssociadoPartidoForm form);

    ResponseEntity<AssociadoDTO> listAssociado(Long id);

    ResponseEntity<AssociadoDTO> updateAssociado(Long id, AssociadoFormDTO formDTO);

    ResponseEntity<?> deleteAssociado(Long id);

    ResponseEntity<?> deleteAssociadoFromPartido(Long id, Long id2); 
}
