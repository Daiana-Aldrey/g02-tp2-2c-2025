package edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Pieza.Ladron;
import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;

import java.util.List;
import java.util.ArrayList;

public class VerticeTerreno extends Vertice {
     private int fichaDeNumero;
     private List<Pieza> edificios;
     private Terreno terreno;
     private boolean ladron;

     public VerticeTerreno(UbicacionVertice ubicacion, Terreno terreno, int fichaDeNumero) {
        this.ubicacion = ubicacion;
        edificios = new ArrayList<>();
        this.terreno = terreno;
        this.fichaDeNumero = fichaDeNumero;
         ladron = false;
     }

    public void colocarLadron(Ladron ladron) {
         if (this.ladron) {
             throw new IllegalStateException("Ya esta colocando ladron, intente en otro terreno");
         }
         this.ladron = true;
         ladron.moverA(ubicacion);
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
    

    public void robarPara(Jugador jugadorQueMueve) {
        if (!hayPiezasAdyacentes()) return;

        GeneradorNumerosAleatorios aleatorio= GeneradorNumerosAleatorios.getInstance();
        Pieza piezaVictima = edificios.get(aleatorio.generarEnRangoDesdeCero(edificios.size()));

        piezaVictima.afectarPorLadron(jugadorQueMueve);    
    }

    public void removerPieza(Pieza piezaActual) {
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
    public boolean tieneTerreno(String terreno) {
         return this.terreno.sosEsteTerreno(terreno);
    }

    @Override
    public void agregarVerticeAdyacente(Vertice vertice) {
        adyacentes.add((VerticeEdificio) vertice);
    }

    @Override
    protected boolean contieneTerreno() {
        boolean contiene = true;
        return contiene;
    }
}
