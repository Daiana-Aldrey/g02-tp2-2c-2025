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
        int numeroHexadono = 0;
        for (char i = 'A' ; i <= 'S'; i++) {
            VerticeTerreno terreno = (VerticeTerreno) modelo.getTerreno(new UbicacionVertice(i));
            colocarTerrenoCorrespondiente(terreno);
            colocarFichaCorrespondiente(terreno, numeroHexadono);
            numeroHexadono++;
        }
    }

    public void colocarFichaCorrespondiente(VerticeTerreno terreno, int numeroHexadono) {
        if (terreno.tieneFichaDeNumero(2)) {
            vista.ponerFicha2(numeroHexadono);
        } else if (terreno.tieneFichaDeNumero(3)) {
            vista.ponerFicha3(numeroHexadono);
        } else if (terreno.tieneFichaDeNumero(4)) {
            vista.ponerFicha4(numeroHexadono);
        } else if (terreno.tieneFichaDeNumero(5)) {
            vista.ponerFicha5(numeroHexadono);
        } else if (terreno.tieneFichaDeNumero(6)) {
            vista.ponerFicha6(numeroHexadono);
        } else if (terreno.tieneFichaDeNumero(8)) {
            vista.ponerFicha8(numeroHexadono);
        } else if (terreno.tieneFichaDeNumero(9)) {
            vista.ponerFicha9(numeroHexadono);
        } else if (terreno.tieneFichaDeNumero(10)) {
            vista.ponerFicha10(numeroHexadono);
        } else if (terreno.tieneFichaDeNumero(11)) {
            vista.ponerFicha11(numeroHexadono);
        } else if (terreno.tieneFichaDeNumero(12)) {
            vista.ponerFicha12(numeroHexadono);
        }
    }

    public void colocarTerrenoCorrespondiente(VerticeTerreno terreno) {
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
