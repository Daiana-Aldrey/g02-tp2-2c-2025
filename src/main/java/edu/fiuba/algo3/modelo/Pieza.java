package edu.fiuba.algo3.modelo;

import java.util.*;

public abstract class Pieza {
    protected Jugador propietario;
    
    public static Pieza crear(String tipo, Jugador propietario) {
        tipo = tipo.toLowerCase(Locale.ROOT).trim();

        switch (tipo) {
            case "poblado":
                return new Poblado(propietario);

            case "camino":
                return new Camino(propietario);

            case "ciudad":
                return new Ciudad(propietario);

            default:
                throw new IllegalArgumentException(
                    "Tipo de pieza no válido: " + tipo + ". Debe ser 'poblado', 'camino' o 'ciudad'.");
        }
    }


    
    public abstract List<Recurso> costoDeConstruccion();

    public void agregarRecursos(String tipo, int cantidad) {
        propietario.recibirRecurso(tipo, cantidad);
        System.out.println("Agregando recursos de " + tipo + ": " + cantidad);
	}
    public abstract void colocar();
    
    public abstract int ubicacion();
    
}
    