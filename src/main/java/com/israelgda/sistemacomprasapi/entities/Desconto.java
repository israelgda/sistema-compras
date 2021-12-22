package com.israelgda.sistemacomprasapi.entities;

import com.israelgda.sistemacomprasapi.entities.enums.TipoDesconto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_descontos")
public class Desconto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tipo;
    private Boolean cumulativo;
    private Double valor;

    @OneToOne
    private Categoria categoria;

    public TipoDesconto getTipo(){
        return TipoDesconto.valueOf(tipo);
    }

    public Desconto() {
    }
}
