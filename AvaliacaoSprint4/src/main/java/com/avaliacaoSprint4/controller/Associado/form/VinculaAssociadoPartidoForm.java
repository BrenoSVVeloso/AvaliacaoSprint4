package com.avaliacaoSprint4.controller.Associado.form;

import lombok.Data;
import lombok.NonNull;

@Data
public class VinculaAssociadoPartidoForm {
    
    @NonNull
    private Long idAssociado;
    
    @NonNull
    private Long idPartido;

}
