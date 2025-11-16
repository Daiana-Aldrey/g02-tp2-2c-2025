package edu.fiuba.algo3.modelo;
import java.util.List;
import java.util.ArrayList;
import java.util.*;
public class VerticeTerreno extends EnlazadorVertices {
     private char ubicacion;
     private List<Pieza> edificios;
     private Terreno terreno;

     public VerticeTerreno(char ubicacion, Terreno terreno){
        this.ubicacion = ubicacion;
        edificios = new ArrayList<>();
        this.terreno = terreno;
     }

    public boolean tieneUbicacion(char letra) {
         return this.ubicacion == letra;
    }

    public boolean tieneFichaDeNumero(int resultadoDados) {
         return terreno.esMiNumero(resultadoDados);
    }

    public void cosecharTerreno() {
         if (!hayPiezasAdyacentes()) {
             throw new IllegalStateException("No hay piezas adyacente para dar recursos.");
         }
        terreno.repartirRecurso(edificios);
    }

    public void agregarEdificio(Pieza edificio) {
         edificios.add(edificio);
    }

    public boolean hayPiezasAdyacentes() {
         return !edificios.isEmpty();
    }
    

    public void recibirLadron(Jugador jugadorQueMueve) {
        if (edificios.isEmpty()) return;

        Random random = new Random();
        Pieza piezaVictima = edificios.get(random.nextInt(edificios.size()));

        piezaVictima.afectarPorLadron(jugadorQueMueve);    
    }
    
    public void removerEdificio(Pieza aRemover){
         edificios.remove(aRemover);
    }
}
