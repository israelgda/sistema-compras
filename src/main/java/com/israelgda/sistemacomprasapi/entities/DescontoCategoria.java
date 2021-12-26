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
@Table(name = "tb_descontos_categorias")
public class DescontoCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tipo;
    private Double valor;

    @OneToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public DescontoCategoria() {
    }

    public TipoDesconto getTipo(){
        return TipoDesconto.valueOf(tipo);
    }


}
