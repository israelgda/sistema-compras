package com.israelgda.sistemacomprasapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@Embeddable
public class ItemCarrinhoPk implements Serializable {

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private Carrinho carrinho;

    public ItemCarrinhoPk() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemCarrinhoPk)) return false;
        ItemCarrinhoPk that = (ItemCarrinhoPk) o;
        return produto.equals(that.produto) && carrinho.equals(that.carrinho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, carrinho);
    }
}
