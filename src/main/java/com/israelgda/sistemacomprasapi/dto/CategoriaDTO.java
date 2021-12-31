package com.israelgda.sistemacomprasapi.dto;

import com.israelgda.sistemacomprasapi.entities.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CategoriaDTO {

    private Long id;
    private String nome;

    public CategoriaDTO(){
    }

    public CategoriaDTO(Categoria categoria){
        id = categoria.getId();
        nome = categoria.getNome();
    }

}
