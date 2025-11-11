package edu.fiuba.algo3.modelo;

import java.util.*;

public abstract class Pieza {
    private Jugador propietario;
    
    
    // ver si es conveniente hacer algo asi y crear clase ubicacion
   /* public static Pieza(TipoPieza tipo, Jugador propietario, Ubicacion ubicacion) {
        return switch (tipo) {
            case POBLADO -> new Poblado(propietario, ubicacion);
            case CAMINO  -> new Camino(propietario, ubicacion);
            case CIUDAD  -> new Ciudad(propietario, ubicacion);
        };
    }*/
    
    public abstract List<Recurso> costoDeConstruccion();
    
}    
    