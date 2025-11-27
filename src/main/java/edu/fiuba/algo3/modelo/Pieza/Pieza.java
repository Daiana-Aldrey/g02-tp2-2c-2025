package edu.fiuba.algo3.modelo.Pieza;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Recurso.RecursoTipo;

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

    public void agregarRecursos(RecursoTipo tipo, int cantidad) {
        propietario.recibirRecurso(tipo, cantidad);
    }

    public abstract void colocar(List<Integer> vertices);

    public boolean esDe(Jugador jugador) {
        return this.propietario == jugador;
    }

    public Jugador obtenerJugador(){
        return propietario;
    }

    public void afectarPorLadron(Jugador jugadorQueMueve) {
        jugadorQueMueve.robarCartaAleatoriaA(propietario);
    }

    public int produccion(){return 0;}
    public boolean esPoblado(){return false;}

    public abstract boolean tenesUbicacion(int ubicacion);

    protected void removerDelJugador() {

    }
}
    