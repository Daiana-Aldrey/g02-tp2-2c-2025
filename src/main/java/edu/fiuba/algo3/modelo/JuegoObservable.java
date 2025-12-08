package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.CartaDeDesarrollo.Carta;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.observador.Observable;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Intercambio.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (hayPropuestaPendiente && getNombreJugadorActual().equals(getNombreJugadorProponente())) {
            cerrarPropuesta();
        }

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
    
    public int consultarCostoIntercambio(String nombreRecurso) {
        Jugador jugadorActual = juego.jugadorActual();
        Recurso recurso = crearRecursoPorNombre(nombreRecurso);
        return Banco.getInstance().calcularTasaOptima(jugadorActual, recurso);
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
        Jugador aceptante = juego.jugadorActual(); 
        
        if (aceptante.nombre().equals(getNombreJugadorProponente())) return;

        if (aceptante != null) {
            aceptante.intercambiar(demandaActual, ofertaActual, jugadorProponente);
            notificarObservadores("RECURSOS");
            cerrarPropuesta();
        }
    }

    private Jugador buscarJugadorPorNombre(String nombre) {
        for (Jugador j : juego.jugadores()) {
            if (j.nombre().equals(nombre)) {
                return j;
            }
        }
        return null;
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

    public List<Map<String, String>> obtenerCartasDesarrolloJugadorActual() {
        Jugador jugador = juego.jugadorActual();
        List<Map<String, String>> lista = new ArrayList<>();

        for (Carta carta : jugador.getCartasDesarrollo()) {
            Map<String, String> datos = new HashMap<>();

            datos.put("nombre", carta.getNombre());
            datos.put("descripcion", carta.getDescripcion());

            String nombreClase = carta.getClass().getSimpleName();

            String imagen = nombreClase + ".png";
            datos.put("imagen", imagen);

            lista.add(datos);
        }

        return lista;
    }

    public void usarCarta(String nombreCarta) {
        Jugador jugador = juego.jugadorActual();
        Carta carta = jugador.obtenerCartaPorNombre(nombreCarta);

        if (carta == null) {
            throw new RuntimeException("El jugador no tiene la carta: " + nombreCarta);
        }

        jugador.jugarCartaDesarrollo(carta);
        notificarObservadores("RECURSOS");
        notificarObservadores("CARTAS");
    }
    
    

}