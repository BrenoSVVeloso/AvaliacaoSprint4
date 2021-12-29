package com.avaliacaoSprint4.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.avaliacaoSprint4.controller.Associado.DTO.AssociadoDTO;
import com.avaliacaoSprint4.controller.Partido.DTO.PartidoDTO;
import com.avaliacaoSprint4.controller.Partido.form.PartidoFormDTO;
import com.avaliacaoSprint4.entity.Associado;
import com.avaliacaoSprint4.entity.Partido;
import com.avaliacaoSprint4.entity.FolderEnum.Ideologia;
import com.avaliacaoSprint4.repository.AssociadoRepository;
import com.avaliacaoSprint4.repository.PartidoRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class PartidoServiceImpl implements PartidoService{

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<PartidoDTO> savePartido(@Valid PartidoFormDTO form) {

        String ideologiaS = form.getIdeologia().toUpperCase();
        
        if(!(Arrays.stream(Ideologia.values()).anyMatch(t -> t.name().equals(ideologiaS)))){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{

            Ideologia ideologia = Ideologia.valueOf(ideologiaS);
            Partido partido = modelMapper.map(form, Partido.class);
            partido.setIdeologia(ideologia);
            Partido partidoSave = partidoRepository.save(partido);
            return ResponseEntity.ok(modelMapper.map(partidoSave, PartidoDTO.class));
        }
    }

    @Override
    public ResponseEntity<List<PartidoDTO>> getPartidos(String ideologia) {
        List<Partido> partidos = Collections.emptyList();
        if(ideologia != null){
        
            String ideologiaFix = ideologia.toUpperCase();
            
            if((Arrays.stream(Ideologia.values()).anyMatch(t -> t.name().equals(ideologiaFix)))){
                Ideologia ideologiaFiltrada = Ideologia.valueOf(ideologiaFix);
                partidos = partidoRepository.findByIdeologia(ideologiaFiltrada);

            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return ResponseEntity.ok(partidos.stream().map(pa -> modelMapper.map(pa, PartidoDTO.class)).collect(Collectors.toList()));
        
    }

    @Override
    public ResponseEntity<PartidoDTO> listPartido(Long id) {

        Optional<Partido> partido = partidoRepository.findById(id);
        if(partido.isPresent()){
            return ResponseEntity.ok(modelMapper.map(partido.get(), PartidoDTO.class));
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }

    @Override
    public ResponseEntity<List<AssociadoDTO>> listAssociadoFromPartido(Long id) {
        Optional<Partido> pOptional= partidoRepository.findById(id);
        List<Associado> associados = Collections.emptyList();
        if(pOptional.isPresent()){
            Partido partido = partidoRepository.getById(id);
            associados = associadoRepository.findAllByPartido(partido.getNome());
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(associados.stream().map(as -> modelMapper.map(as, AssociadoDTO.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<PartidoDTO> updatePartido(Long id, PartidoFormDTO form) {
        
        Partido partidoSave = null;
        Optional<Partido> opPartido = partidoRepository.findById(id);
        
        if(opPartido.isPresent()){
            String ideologiaS = form.getIdeologia().toUpperCase();
        
            if(!(Arrays.stream(Ideologia.values()).anyMatch(t -> t.name().equals(ideologiaS)))){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                Ideologia ideologia = Ideologia.valueOf(ideologiaS);
                Partido partido = partidoRepository.getById(id);
                partido.setNome(form.getNome());
                partido.setSigla(form.getSigla());
                partido.setDATAFUNDACAO(form.getDATAFUNDACAO());
                partido.setIdeologia(Ideologia.valueOf(form.getIdeologia().toUpperCase()));
                partidoSave = partidoRepository.save(partido);
            }
        }
        return ResponseEntity.ok(modelMapper.map(partidoSave, PartidoDTO.class));
    }

    @Override
    public ResponseEntity<?> deletePartido(Long id) {
        Optional<Partido> pOptional = partidoRepository.findById(id);
        if(pOptional.isPresent()){
            partidoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
