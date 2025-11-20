package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Recurso.RecursoTipo;

public class PuertoGenerico implements Puerto {
    @Override
    public int tasaPara(RecursoTipo recurso){
        return 3;
    }
}