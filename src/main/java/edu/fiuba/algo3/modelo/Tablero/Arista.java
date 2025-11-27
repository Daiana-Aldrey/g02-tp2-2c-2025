package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Pieza.Camino;
import edu.fiuba.algo3.modelo.Pieza.NoPieza;
import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;

public class Arista {
    private boolean disponible;
    private Pieza camino;
    private UbicacionVertice ubicacion1;
    private UbicacionVertice ubicacion2;

    public Arista(UbicacionVertice ubicacion1, UbicacionVertice ubicacion2) {
        disponible = true;
        this.ubicacion1 = ubicacion1;
        this.ubicacion2 = ubicacion2;

        this.camino = new NoPieza(ubicacion2);
    }

    public boolean tieneUbicacion(UbicacionVertice ubicacion1, UbicacionVertice ubicacion2) {
        return ((this.ubicacion1.equals(ubicacion1) && this.ubicacion2.equals(ubicacion2)) || (this.ubicacion1.equals(ubicacion2) && this.ubicacion2.equals(ubicacion1)));

    }

    public void colocarCamino(Camino camino) {
        if (disponible) {
            noDisponible();
            this.camino = camino;
            camino.setearUbicacion(ubicacion1);
            camino.setearSegundaUbicacion(ubicacion2);
        } else {
            throw new IllegalArgumentException("Esta arista ya esta ocupada");
        }
    }

    public boolean estaDisponible() {
        return disponible;
    }

    private void noDisponible() {
        disponible = false;
    }

    public boolean hayCamino() {
        boolean hayCamino = false;
        if (camino.usable()) {
            hayCamino = true;
        }
        return hayCamino;
    }
}
