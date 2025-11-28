package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Recurso.*;

public class PuertoGenerico implements Puerto {
    @Override
    public int tasaPara(Recurso recurso){
        return 3;
    }
}