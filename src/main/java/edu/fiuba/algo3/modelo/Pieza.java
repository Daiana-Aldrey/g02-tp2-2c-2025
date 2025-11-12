package edu.fiuba.algo3.modelo;

import java.util.*;

public abstract class Pieza {
    private Jugador propietario;
    
    
    // ver si es conveniente hacer algo asi y crear clase ubicacion
   /* public static Pieza(TipoPieza tipo, Jugador propietario) {
        return switch (tipo) {
            case POBLADO -> new Poblado(propietario);
            case CAMINO  -> new Camino(propietario);
            case CIUDAD  -> new Ciudad(propietario);
        };
    }*/
    
    public abstract List<Recurso> costoDeConstruccion();

    public void agregarRecursos(String tipo, int cantidad) {
        propietario.recibirRecurso(tipo, cantidad);
        System.out.println("Agregando recursos de " + tipo + ": " + cantidad);
    }
}
    