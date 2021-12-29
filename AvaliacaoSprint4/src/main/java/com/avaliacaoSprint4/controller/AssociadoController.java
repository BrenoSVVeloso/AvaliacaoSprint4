package com.avaliacaoSprint4.controller;

import java.util.List;

import javax.validation.Valid;

import com.avaliacaoSprint4.controller.Associado.DTO.AssociadoDTO;
import com.avaliacaoSprint4.controller.Associado.form.AssociadoFormDTO;
import com.avaliacaoSprint4.controller.Associado.form.VinculaAssociadoPartidoForm;
import com.avaliacaoSprint4.service.AssociadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

    @Autowired
    private AssociadoService service;


    //Cadastra Associados
    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeAssociados", allEntries = true)
    public ResponseEntity<AssociadoDTO> saveAssociados(@RequestBody @Valid AssociadoFormDTO form){
        
        return this.service.saveAssociado(form);
        
    }

    //Vincula um associado a um partido
    @PostMapping("/partidos")
    @Transactional
    @CacheEvict(value = "listaDeAssociados", allEntries = true)
    public ResponseEntity<AssociadoDTO> saveAssociadoToPartido(@RequestBody @Valid VinculaAssociadoPartidoForm form){
        return this.service.saveAssociadoToPartido(form);

    }


    //Lista todos os associados a partir de seu cargo politico ou ordena pelo nome se 'ordenarPorNome=true' e lista normalmente se 'ordenarPorNome=false'
    @GetMapping
    @Cacheable(value = "listaDeAssociados")
    public ResponseEntity<List<AssociadoDTO>> getAssociados(@RequestParam(required = false, name = "cargoPolitico") String cargo, @RequestParam(required = false, name = "ordenarPorNome") Boolean OrdNome){
        return  this.service.getAssociados(cargo, OrdNome);
    }

    //Lista um associado
    @GetMapping("/{id}")
    public ResponseEntity<AssociadoDTO> listAssociado(@PathVariable Long id){
        return this.service.listAssociado(id);
    }


    //atualiza um associado
    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeAssociados", allEntries = true)
    public ResponseEntity<AssociadoDTO> updateAssociado(@PathVariable Long id,@RequestBody @Valid AssociadoFormDTO formDTO){
        return this.service.updateAssociado(id, formDTO);
    }


    //exclui um associado
    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeAssociados", allEntries = true)
    public ResponseEntity<?> deleteAssociado(@PathVariable Long id){
        return this.service.deleteAssociado(id);
    }



    //deleta um associado de um partido
    @DeleteMapping("/{id}/partidos/{id2}")
    @Transactional
    @CacheEvict(value = "listaDeAssociados", allEntries = true)
    public ResponseEntity<?> deleteAssociadoFromPartido(@PathVariable Long id, @PathVariable Long id2){
        return this.service.deleteAssociadoFromPartido(id, id2);
    }

}
