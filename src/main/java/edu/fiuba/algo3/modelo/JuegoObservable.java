package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.observador.Observable;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Intercambio.*;
import java.util.List;

public class JuegoObservable extends Observable {

    private final Juego juego;
    private int[] ultimaTirada; 
    private List<Recurso> ofertaActual;
    private List<Recurso> demandaActual;
    
    private Jugador jugadorProponente;
    private boolean hayPropuestaPendiente = false;

    public JuegoObservable(Juego juego) {
        this.juego = juego;
    }

    public Juego juego() { 
    	return juego; 
    }

 
    public void realizarTirada() {
        this.ultimaTirada = juego.tirarDados();
        notificarObservadores("DADOS");

        int suma = getSuma();
        juego.manejarTirada(suma);
        notificarObservadores("RECURSOS");

        if (suma == 7){
            notificarObservadores("LADRON");
        }
    }

    public int getDado1() { 
        return (ultimaTirada == null) ? 1 : ultimaTirada[0]; 
    }
    public int getDado2() { 
        return (ultimaTirada == null) ? 1 : ultimaTirada[1];
    }
    public int getSuma() { 
        return juego.sumarTirada(); 
    }
    
    public void siguienteTurno() {
        juego.pasarAlSiguienteJugador();
        notificarObservadores("TURNO");
    }
    public String getNombreJugadorActual() {
        return juego.jugadorActual().nombre();
    }
 
    public void realizarIntercambio(String nombreRecursoOferta, String nombreRecursoPedido) {
        Banco banco = Banco.getInstance();
        Jugador jugadorActual = juego.jugadorActual();
        Recurso recursoOferta = crearRecursoPorNombre(nombreRecursoOferta);
        Recurso recursoPedido = crearRecursoPorNombre(nombreRecursoPedido);
        banco.comercializar(jugadorActual, recursoOferta, recursoPedido, 1);
        notificarObservadores("RECURSOS");
    }
    
    public boolean jugadorTieneRecurso(String nombreRecurso, int cantidadRequerida) {
        Jugador jugador = juego.jugadorActual();
        Recurso buscado = crearRecursoPorNombre(nombreRecurso);
        Recurso recursoReal = jugador.buscarRecurso(buscado);
        
        if (recursoReal == null)
        	return false;
        return recursoReal.cantidad() >= cantidadRequerida;
    }
  
    public void crearPropuesta(List<Recurso> oferta, List<Recurso> demanda) {
        this.ofertaActual = oferta;
        this.demandaActual = demanda;
        this.jugadorProponente = juego.jugadorActual();
        this.hayPropuestaPendiente = true;
        
        notificarObservadores("NUEVA_PROPUESTA");
    }

    public void aceptarPropuesta() {
        if (!hayPropuestaPendiente) return;

        Jugador aceptante = buscarJugadorAceptante();
        if (aceptante != null) {
            try {
                aceptante.intercambiar(demandaActual, ofertaActual, jugadorProponente);
                notificarObservadores("RECURSOS");
            } catch (Exception e) {
                System.out.println("Error al aceptar: " + e.getMessage());
            }
        }
        cerrarPropuesta();
    }

    public void rechazarPropuesta() {
        cerrarPropuesta();
    }

    private void cerrarPropuesta() {
        this.hayPropuestaPendiente = false;
        this.ofertaActual = null;
        this.demandaActual = null;
        this.jugadorProponente = null;
        notificarObservadores("PROPUESTA_CERRADA");
    }

    private Jugador buscarJugadorAceptante() {
        for (Jugador j : juego.jugadores()) {
            if (!j.equals(jugadorProponente)) return j;
        }
        return null;
    }

    public boolean hayPropuesta() { 
	  return hayPropuestaPendiente; 
	 }
    public String getNombreJugadorProponente() { 
    	return (jugadorProponente != null) ? jugadorProponente.nombre() : ""; 
    }
  
    public List<Recurso> getOferta() { 
    	return ofertaActual;
    }
    public List<Recurso> getDemanda() { 
    	return demandaActual; 
    }

    private Recurso crearRecursoPorNombre(String nombre) {
        switch (nombre.toLowerCase()) {
            case "madera": return new Madera(0);
            case "ladrillo": return new Ladrillo(0);
            case "oveja": case "lana": return new Lana(0); 
            case "trigo": case "grano": return new Grano(0);
            case "piedra": case "mineral": return new Mineral(0);
            default: throw new RuntimeException("Recurso desconocido: " + nombre);
        }
    }

    public void construirPiezaObervable(String tipo, List<Ubicacion> ubicacion){
        juego.jugadorActual().construirPieza(tipo, ubicacion);
        notificarObservadores("RECURSOS");
        notificarObservadores("CONSTRUCCION");
    }

}