package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Recurso.*;

public class PuertoEspecifico implements Puerto {
    private final Recurso recurso;

    public PuertoEspecifico(Recurso recurso){
        this.recurso = recurso;
    }

    @Override
    public int tasaPara(Recurso recurso){
        return 2;
    }
}