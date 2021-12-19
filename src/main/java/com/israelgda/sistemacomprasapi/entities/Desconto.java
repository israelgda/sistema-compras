package com.israelgda.sistemacomprasapi.entities;

import com.israelgda.sistemacomprasapi.entities.enums.TipoDesconto;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "tb_descontos")
public class Desconto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tipo;
    private Double valor;

    @OneToOne
    private Produto produto;

    public TipoDesconto getTipo(){
        return TipoDesconto.valueOf(tipo);
    }

}
