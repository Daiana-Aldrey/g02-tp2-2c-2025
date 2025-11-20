package edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Pieza.Ladron;
import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Terreno.Terreno;

import java.util.List;
import java.util.ArrayList;

public class VerticeTerreno extends EnlazadorVertices {
     private char ubicacion;
     private int fichaDeNumero;
     private List<Pieza> edificios;
     private Terreno terreno;
     private boolean ladron;

     public VerticeTerreno(char ubicacion, Terreno terreno, int fichaDeNumero) {
        this.ubicacion = ubicacion;
        edificios = new ArrayList<>();
        this.terreno = terreno;
        this.fichaDeNumero = fichaDeNumero;
         ladron = false;
     }

    public boolean tieneUbicacion(char letra) {
         return this.ubicacion == letra;
    }

    public void colocarLadron(Ladron ladron) {
         if (this.ladron) {
             throw new IllegalStateException("Ya esta colocando ladron, intente en otro terreno");
         }
         this.ladron = true;
         ladron.posicion(ubicacion);
    }

    public boolean tieneFichaDeNumero(int resultadoDados) {
         return fichaDeNumero == resultadoDados;
    }

    public void cosecharTerreno() {
         if (!hayPiezasAdyacentes()) {
             throw new IllegalStateException("No hay piezas adyacente para dar recursos.");
         }
         if (ladron) {
             return;
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
        if (!hayPiezasAdyacentes()) return;

        GeneradorNumerosAleatorios aleatorio= GeneradorNumerosAleatorios.getInstance();
        Pieza piezaVictima = edificios.get(aleatorio.generarEnRangoDesdeCero(edificios.size()));

        piezaVictima.afectarPorLadron(jugadorQueMueve);    
    }


    public void removerEdificio(Pieza piezaActual) {
        int i = 0;
        boolean encontrado = false;
        while(i < edificios.size() && !encontrado) {
            if (edificios.get(i) == piezaActual) {
                encontrado = true;
                edificios.remove(i);
            }
            i++;
        }
    }
    //metodo usado unicamente para test integral
    public boolean tieneTerreno(String pastizal) {
         return terreno.sosEsteTerreno(pastizal);
    }
}
