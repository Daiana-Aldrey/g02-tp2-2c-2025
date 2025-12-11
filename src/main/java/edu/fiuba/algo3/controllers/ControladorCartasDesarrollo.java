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
                //VistaCartasDesarrollo.ConfiguracionCaballero config = vista.mostrarDialogoCaballero();
                //modelo.configurarCartaCaballero(config.destino, config.nombreVictima);
                //si cuando entra el ladron tengo MODO LADRON puedo borrar el dialogo del caballero y configcarta
                modelo.usarCartaDesarrollo(nombreCarta);
                modelo.activarTurnoLadronPorCaballero();
                vista.mostrarMensaje("Carta Caballero usada", "Ahora hacé clic en el terreno donde querés mover al ladrón y elegí a la víctima.");
            }

            else if ("Construccion de carreteras".equals(nombreCarta)) {
                modelo.activarModoConstruccionCarreteras();
                vista.mostrarMensaje("Construcción de Carreteras", "Elegí dos caminos en el tablero. Se van a construir gratis."
                );
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
