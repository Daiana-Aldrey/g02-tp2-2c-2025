package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.Excepciones.ErrorNoUsoDeCartaInvalido;
import edu.fiuba.algo3.Excepciones.NoTieneCarta;
import edu.fiuba.algo3.modelo.JuegoObservable;
import edu.fiuba.algo3.vistas.VistaCartasDesarrollo;

import java.util.List;

public class ControladorCartasDesarrollo {

    private final JuegoObservable modelo;
    private final VistaCartasDesarrollo vista;

    public ControladorCartasDesarrollo(JuegoObservable modelo, VistaCartasDesarrollo vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void manejarComprarCarta() {
        try {
            modelo.comprarCartaDesarrollo();
            vista.actualizarCartas();

        } catch (Exception e) {
            vista.mostrarError("No se pudo comprar la carta: " + e.getMessage());
        }
    }

    public void manejarUsarCarta(String nombreCarta) {
        try {
            if ("Monopolio".equals(nombreCarta)) {
                String recursoElegido = vista.mostrarDialogoMonopolio();
                modelo.configurarCartaMonopolio(recursoElegido);
                modelo.usarCartaDesarrollo(nombreCarta);
                vista.mostrarMensaje("Carta usada", "Aplicaste Monopolio sobre " + recursoElegido + ".");
            }
            else if ("Descubrimiento".equals(nombreCarta)) {
                List<String> recursosElegidos = vista.mostrarDialogoSeleccionRecursosDescubrimiento();

                String recurso1 = recursosElegidos.get(0);
                String recurso2 = recursosElegidos.get(1);
                modelo.configurarCartaDescubrimiento(recurso1, recurso2);
                modelo.usarCartaDesarrollo(nombreCarta);
                vista.mostrarMensaje("Carta usada", "Recibiste 1 " + recurso1 + " y 1 " + recurso2 + ".");

            }
            else if ("Caballero".equals(nombreCarta)) {
                VistaCartasDesarrollo.ConfiguracionCaballero config = vista.mostrarDialogoCaballero();
                modelo.configurarCartaCaballero(config.destino, config.nombreVictima);
                modelo.usarCartaDesarrollo(nombreCarta);
                vista.mostrarMensaje("Carta usada", "Moviste el ladrón y robaste a " + config.nombreVictima + ".");
            }

            else if ("Construccion de Carreteras".equals(nombreCarta)) {
                VistaCartasDesarrollo.ConfiguracionCarreteras config = vista.mostrarDialogoConstruccionCarreteras();
                modelo.configurarCartaConstruccionCarreteras(
                        config.camino1, config.camino2);
                modelo.usarCartaDesarrollo(nombreCarta);
                vista.mostrarMensaje("Carta usada", "Construiste dos carreteras gratis.");
            }
            else {
                modelo.usarCartaDesarrollo(nombreCarta);
                vista.mostrarMensaje("Carta usada", "Usaste la carta " + nombreCarta + ".");
            }
            vista.actualizarCartas();


        } catch (ErrorNoUsoDeCartaInvalido e) {
            vista.mostrarError("No podés usar una carta comprada en este turno.");
        } catch (NoTieneCarta e) {
            vista.mostrarError("No tenés una carta de tipo " + nombreCarta + ".");
        } catch (Exception e) {
            vista.mostrarError("Error al usar carta: " + e.getMessage());
        }
    }

}
