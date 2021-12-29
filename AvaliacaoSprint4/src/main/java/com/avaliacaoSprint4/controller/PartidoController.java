package com.avaliacaoSprint4.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.avaliacaoSprint4.controller.Associado.DTO.AssociadoDTO;
import com.avaliacaoSprint4.controller.Partido.DTO.PartidoDTO;
import com.avaliacaoSprint4.controller.Partido.form.PartidoFormDTO;
import com.avaliacaoSprint4.service.PartidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/partidos")
public class PartidoController {
    

    @Autowired
    private PartidoService service;


    //Cadastra um partido
    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDePartidos", allEntries = true)
    public ResponseEntity<PartidoDTO> savePartidos(@RequestBody @Valid PartidoFormDTO form){
        
        return this.service.savePartido(form);
    }

    //Lista partidos de acordo com determinada ideologia
    @GetMapping
    @Cacheable(value = "listaDePartidos")
    public ResponseEntity<List<PartidoDTO>> getPartidos(@RequestParam(required = false, name = "ideologia") String ideologia){
        return this.service.getPartidos(ideologia);
    }


    //Lista um partido
    @GetMapping("/{id}")
    public ResponseEntity<PartidoDTO> listPartido(@PathVariable Long id){
        return this.service.listPartido(id);
    }



    //Lista todos os associados daquele partido
    @GetMapping("/{id}/associados")
    public ResponseEntity<List<AssociadoDTO>> listAssociadosFromPartido(@PathVariable Long id){
       return this.service.listAssociadoFromPartido(id);
  
    }


    //atualiza um associado
    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDePartidos", allEntries = true)
    public ResponseEntity<PartidoDTO> updatePartido(@PathVariable Long id, @RequestBody @Valid PartidoFormDTO form){
        return this.service.updatePartido(id, form);
    }


    //deleta um associado
    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDePartidos", allEntries = true)
    public ResponseEntity<?> deletePartido(@PathVariable Long id){
        return this.service.deletePartido(id);
    }
}
