package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.vistas.VistaJuego;

public class ControladorJuego {

    private final JuegoObservable modelo;
    private final VistaJuego vista;

    public ControladorJuego(JuegoObservable modelo, VistaJuego vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void manejarTirarDados() {
        try {
            int[] dados = modelo.juego().tirarDados();
            int d1 = dados[0];
            int d2 = dados[1];
            int suma = modelo.juego().sumarTirada();

            vista.actualizarDados(d1, d2);
            vista.mostrarEstado("Se tiraron: " + d1 + " + " + d2 + " = " + suma);

        } catch (Exception e) {
            e.printStackTrace();
            vista.mostrarEstado("Ocurrió un error al tirar los dados.");
        }
    }
}
