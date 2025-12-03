package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.observador.Observable;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Intercambio.*;

public class JuegoObservable extends Observable {

    private final Juego juego;
    private int[] ultimaTirada; 

    public JuegoObservable(Juego juego) {
        this.juego = juego;
    }

    public Juego juego() {
        return juego;
    }


    public void realizarTirada() {
        this.ultimaTirada = juego.tirarDados();
        notificarObservadores("DADOS"); 
    }

    public int getDado1() {
        if (ultimaTirada == null) return 1; 
        return ultimaTirada[0];
    }

    public int getDado2() {
        if (ultimaTirada == null) return 1;
        return ultimaTirada[1];
    }

    public int getSuma() {
        return juego.sumarTirada();
    }
    
    
    public void realizarIntercambio(String nombreRecursoOferta, String nombreRecursoPedido) {
        Banco banco = Banco.getInstance();
        Jugador jugadorActual = juego.jugadorActual();
        Recurso recursoOferta = mapearRecurso(nombreRecursoOferta);
        Recurso recursoPedido = mapearRecurso(nombreRecursoPedido);
        banco.comercializar(jugadorActual, recursoOferta, recursoPedido, 1);

        notificarObservadores("RECURSOS"); 
    }


    private Recurso mapearRecurso(String nombre) {
        switch (nombre.toLowerCase()) {
            case "madera": return new Madera(0);
            case "ladrillo": return new Ladrillo(0);
            case "oveja": case "lana": return new Lana(0); 
            case "trigo": case "grano": return new Grano(0);
            case "piedra": case "mineral": return new Mineral(0);
            default: throw new RuntimeException("Recurso desconocido");
        }
    }
    
    public void siguienteTurno() {
        juego.pasarAlSiguienteJugador();
        notificarObservadores("TURNO");
    }
    
    public String getNombreJugadorActual() {
        return juego.jugadorActual().nombre();
    }
}