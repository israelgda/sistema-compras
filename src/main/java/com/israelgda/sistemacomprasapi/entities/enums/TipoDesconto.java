package com.israelgda.sistemacomprasapi.entities.enums;

public enum TipoDesconto {

    VALOR_FIXO(1),
    PORCENTAGEM(2);

    private int codigo;

    TipoDesconto(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public static TipoDesconto valueOf(int codigo) {
        for(TipoDesconto tipo: TipoDesconto.values()) {
            if(tipo.getCodigo() == codigo) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Este código de tipo é inválido!");
    }
}
