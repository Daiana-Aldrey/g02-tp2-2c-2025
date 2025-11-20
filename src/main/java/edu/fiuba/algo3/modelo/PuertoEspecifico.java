package edu.fiuba.algo3.modelo;

public class PuertoEspecifico implements Puerto{
    private final RecursoTipo tipo;

    public PuertoEspecifico(RecursoTipo tipo){
        this.tipo = tipo;
    }

    @Override
    public int tasaPara(RecursoTipo recurso){
        if(recurso == tipo){
            return 2;
        }

        return Integer.MAX_VALUE;
    }
}