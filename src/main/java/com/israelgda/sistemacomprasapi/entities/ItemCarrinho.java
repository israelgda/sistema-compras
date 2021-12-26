package com.israelgda.sistemacomprasapi.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_itens_carrinhos")
public class ItemCarrinho {

    @EmbeddedId
    private ItemCarrinhoPk id = new ItemCarrinhoPk();

    private Double valor;

    private Integer quantidade;


    public ItemCarrinho(Carrinho carrinho, Produto produto, Double valor, Integer quantidade) {
        id.setProduto(produto);
        id.setCarrinho(carrinho);
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Produto getProduto(){
        return id.getProduto();
    }

    public void setProduto(Produto produto){
         id.setProduto(produto);
    }

    public Carrinho getCarrinho(){
        return id.getCarrinho();
    }

    public void setCarrinho(Carrinho carrinho){
        id.setCarrinho(carrinho);
    }
}
