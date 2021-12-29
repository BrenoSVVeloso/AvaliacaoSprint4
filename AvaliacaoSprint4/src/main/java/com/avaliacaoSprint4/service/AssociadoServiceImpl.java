package com.avaliacaoSprint4.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.avaliacaoSprint4.controller.Associado.DTO.AssociadoDTO;
import com.avaliacaoSprint4.controller.Associado.form.AssociadoFormDTO;
import com.avaliacaoSprint4.controller.Associado.form.VinculaAssociadoPartidoForm;
import com.avaliacaoSprint4.entity.Associado;
import com.avaliacaoSprint4.entity.Partido;
import com.avaliacaoSprint4.entity.FolderEnum.CargoPolitico;
import com.avaliacaoSprint4.entity.FolderEnum.Sexo;
import com.avaliacaoSprint4.repository.AssociadoRepository;
import com.avaliacaoSprint4.repository.PartidoRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class AssociadoServiceImpl implements AssociadoService{

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private ModelMapper modelMapper;


    
    @Override
    public ResponseEntity<AssociadoDTO> saveAssociado(AssociadoFormDTO formDTO) {  
        
        String cargoS = formDTO.getCargo().toUpperCase();
        String sexoS =formDTO.getSexo().toUpperCase();

        if((Arrays.stream(CargoPolitico.values()).anyMatch(t -> t.name().equals(cargoS)))){

            if((Arrays.stream(Sexo.values()).anyMatch(t -> t.name().equals(sexoS)))){
                
                CargoPolitico cargo = CargoPolitico.valueOf(cargoS);
                Sexo sexo = Sexo.valueOf(sexoS);
                Associado associado = modelMapper.map(formDTO, Associado.class);
                associado.setCargo(cargo);
                associado.setSexo(sexo);
                Associado associadoSave = associadoRepository.save(associado);
                return ResponseEntity.ok(modelMapper.map(associadoSave, AssociadoDTO.class));

            }else{

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }else{

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }   
    }

    @Override
    public ResponseEntity<List<AssociadoDTO>> getAssociados(String cargo, Boolean OrdNome) {
        List<Associado> associados = Collections.emptyList();
        
        if(OrdNome == null){

            if(cargo!= null ){
                String cargoFix = cargo.toUpperCase();

                if((Arrays.stream(CargoPolitico.values()).anyMatch(t -> t.name().equals(cargoFix)))){

                    CargoPolitico cargoFiltrado = CargoPolitico.valueOf(cargoFix);
                    associados = associadoRepository.findByCargo(cargoFiltrado);
                    
                }else{

                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }

            }else{

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
       
        }else if(OrdNome){

                associados = associadoRepository.findAllByOrderByNomeAsc();

            }else if(!OrdNome){

                associados = associadoRepository.findAll();
            }

        return ResponseEntity.ok(associados.stream().map(as -> modelMapper.map(as, AssociadoDTO.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<AssociadoDTO> saveAssociadoToPartido(@Valid VinculaAssociadoPartidoForm form) {
        
        Associado associadoSave = null;

        Partido partidoSave = null;

        Optional<Associado> associado = associadoRepository.findById(form.getIdAssociado());
        Optional<Partido> partido = partidoRepository.findById(form.getIdPartido());

        if(!partido.isEmpty() && !associado.isEmpty()){

            associadoSave = associadoRepository.getById(form.getIdAssociado());

            partidoSave = partidoRepository.getById(form.getIdPartido());

            associadoSave.setPartido(partidoSave.getNome());

            associadoRepository.save(associadoSave);

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       
        return ResponseEntity.ok(modelMapper.map(associadoSave, AssociadoDTO.class));
    }

    @Override
    public ResponseEntity<AssociadoDTO> listAssociado(Long id) {
        
        Optional<Associado> associado = associadoRepository.findById(id);
        if(associado.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.ok(modelMapper.map(associado.get(), AssociadoDTO.class));
        }
    }

    @Override
    public ResponseEntity<AssociadoDTO> updateAssociado(Long id, AssociadoFormDTO form) {
        Associado associado = null;
        Optional<Associado> opAssociado = associadoRepository.findById(id);

        if(opAssociado.isPresent()){

            String cargoS = form.getCargo().toUpperCase();
            String sexoS = form.getSexo().toUpperCase();

            if((Arrays.stream(CargoPolitico.values()).anyMatch(t -> t.name().equals(cargoS)))){

                if((Arrays.stream(Sexo.values()).anyMatch(t -> t.name().equals(sexoS)))){

                    CargoPolitico cargo = CargoPolitico.valueOf(cargoS);
                    Sexo sexo = Sexo.valueOf(sexoS);
                    associado = associadoRepository.getById(id);
                    associado.setNome(form.getNome());
                    associado.setCargo(cargo);
                    associado.setDATADENASCIMENTO(form.getDATADENASCIMENTO());
                    associado.setSexo(sexo);
                    associadoRepository.save(associado);

                }else{

                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }

            }else{

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }else{

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(modelMapper.map(associado, AssociadoDTO.class));
    }

    @Override
    public ResponseEntity<?> deleteAssociado(Long id) {

        Optional<Associado> aOptional = associadoRepository.findById(id);
        
        if(aOptional.isPresent()){

            associadoRepository.deleteById(id);

        }else{

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().build();
        
    }

    @Override
    public ResponseEntity<?> deleteAssociadoFromPartido(Long idAssociado, Long idPartido) {

        Associado associado = null;
        Optional<Associado> aOptional = associadoRepository.findById(idAssociado);
        Optional<Partido> pOptional = partidoRepository.findById(idPartido);

        if(aOptional.isPresent() && pOptional.isPresent()){

            associado = associadoRepository.getById(idAssociado);
            associado.setPartido("");
            associadoRepository.save(associado);

        }else{

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return ResponseEntity.ok().build();
    }
}
