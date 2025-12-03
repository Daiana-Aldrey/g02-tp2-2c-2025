package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.VerticeTerreno;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import edu.fiuba.algo3.vistas.VistaTablero;

public class ControladorTablero {
    private Tablero modelo;
    private VistaTablero vista;


    public ControladorTablero(Tablero modelo, VistaTablero vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void colocarTerrenos() {
        for (char i = 'A' ; i <= 'S'; i++) {
            VerticeTerreno terreno = (VerticeTerreno) modelo.getTerreno(new UbicacionVertice(i));
            if (terreno.tieneTerreno("Pastizal")) {
                vista.ponerPastizal();
            } else if (terreno.tieneTerreno("Bosque")) {
                vista.ponerBosque();
            } else if (terreno.tieneTerreno("Colina")) {
                vista.ponerColina();
            } else if (terreno.tieneTerreno("Desierto")) {
                vista.ponerDesierto();
            } else if (terreno.tieneTerreno("Campo")) {
                vista.ponerCampo();
            } else if (terreno.tieneTerreno("Montania")) {
                vista.ponerMotania();
            }

        }
    }

    public void colocarFicha() {
        for (char i = 'A'; i <= 'S'; i++) {
            VerticeTerreno terreno = (VerticeTerreno) modelo.getTerreno(new UbicacionVertice(i));
            if (terreno.tieneFichaDeNumero(1)) {
                vista.ponerPastizal();
            } else if (terreno.tieneFichaDeNumero(2)) {
                vista.ponerBosque();
            } else if (terreno.tieneFichaDeNumero(3)) {
                vista.ponerColina();
            } else if (terreno.tieneFichaDeNumero(4)) {
                vista.ponerDesierto();
            } else if (terreno.tieneFichaDeNumero(5)) {
                vista.ponerCampo();
            } else if (terreno.tieneFichaDeNumero(6)) {
                vista.ponerMotania();
            } else if (terreno.tieneFichaDeNumero(8)) {
                vista.ponerMotania();
            } else if (terreno.tieneFichaDeNumero(9)) {
                vista.ponerMotania();
            } else if (terreno.tieneFichaDeNumero(10)) {
                vista.ponerMotania();
            } else if (terreno.tieneFichaDeNumero(11)) {
                vista.ponerMotania();
            } else if (terreno.tieneFichaDeNumero(12)) {
                vista.ponerMotania();
            }
        }
    }
}
