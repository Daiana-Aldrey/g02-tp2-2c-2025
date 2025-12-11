package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.CartaDeBonificacion.BonificadorRutaMayor;
import edu.fiuba.algo3.modelo.CartaDeDesarrollo.Carta;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.observador.Observable;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Intercambio.*;
import edu.fiuba.algo3.modelo.CartaDeDesarrollo.*;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.VerticeTerreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import edu.fiuba.algo3.Excepciones.NoTieneCarta;
import java.util.*;

public class JuegoObservable extends Observable {
    private final Juego juego;
    private int[] ultimaTirada; 
    private List<Recurso> ofertaActual;
    private List<Recurso> demandaActual;
    private boolean dadosTirados;
    private Jugador jugadorProponente;
    private boolean hayPropuestaPendiente;
    private boolean pobladoInicialColocado;
    private boolean caminoInicialColocado;
    private BonificadorRutaMayor bonificadorRutaMayor;
    private boolean esperandoMovimientoLadron;
    private boolean modoConstruccionCarreteras = false;
    private final List<List<Ubicacion>> caminosCarta = new ArrayList<>();
    private final Set<Jugador> yaMostrados = new HashSet<>();

    public JuegoObservable(Juego juego) {
        this.juego = juego;
        this.dadosTirados = false;
        this.hayPropuestaPendiente = false;
        this.pobladoInicialColocado = false;
        this.caminoInicialColocado = false;
        this.esperandoMovimientoLadron = false;

        this.bonificadorRutaMayor = new BonificadorRutaMayor();
        agregarRutasBonificador();

        if(esFaseInicial()){
            notificarObservadores("PRIMER_TURNO");
        }
    }

    public Juego juego() { 
    	return juego; 
    }


    public void realizarTirada() {
    	if (esFaseInicial()) {
            throw new RuntimeException("No se pueden tirar dados en la fase inicial.");
        }

        this.ultimaTirada = juego.tirarDados();
        this.dadosTirados = true; 
        notificarObservadores("DADOS");

        int suma = getSuma();
        juego.manejarTirada(suma);

        if (suma == 7){
        	this.esperandoMovimientoLadron = true;
            notificarObservadores("LADRON");
        }else {
	        notificarObservadores("RECURSOS");
	    }
    }
    
    public boolean esTurnoLadron() {
        return esperandoMovimientoLadron;
    }
    
    public void moverLadronObservable(UbicacionVertice destino, String nombreVictima) {
        Jugador jugadorActual = juego.jugadorActual();
        Jugador victima = null;
        if (nombreVictima != null && !nombreVictima.isEmpty()) {
            victima = buscarJugadorPorNombre(nombreVictima);
        }
        
        jugadorActual.moverLadron(destino, victima);
        this.esperandoMovimientoLadron = false;
        notificarObservadores("RECURSOS"); 
        notificarObservadores("LADRON_MOVIDO");
    }
    
    public List<Jugador> getJugadores() {
        return juego.jugadores();
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
        if (!esFaseInicial() && !dadosTirados) {
            throw new RuntimeException("Debes tirar los dados antes de pasar el turno."); 
        }
        if (esFaseInicial() && (!pobladoInicialColocado || !caminoInicialColocado)) {
            throw new RuntimeException("Debes colocar 1 poblado y 1 camino.");
        }
        juego.finalizarTurnoActual();
        
        if (juego.verificarVictoria()) {
            notificarObservadores("FIN_JUEGO");
            return; 
        }

        juego.pasarAlSiguienteJugador();

        this.dadosTirados = false;
        this.pobladoInicialColocado = false;
        this.caminoInicialColocado = false;
        
        if (hayPropuestaPendiente && getNombreJugadorActual().equals(getNombreJugadorProponente())) {
            cerrarPropuesta();
        }

        Jugador actual =juego().jugadorActual();
        if (esFaseInicial() && !yaMostrados.contains(actual)) {
            yaMostrados.add(actual);
            notificarObservadores("PRIMER_TURNO");
        }
        
        notificarObservadores("TURNO");
    }
    
    public void colocarPiezaInicialObservable(String tipo, List<Ubicacion> ubicacion) {
        if (!esFaseInicial()) return;

        if (tipo.equalsIgnoreCase("poblado")) {
            if (pobladoInicialColocado) throw new RuntimeException("Ya colocaste tu poblado esta ronda.");
            juego.jugadorActual().colocarPiezaInicial(tipo, ubicacion);
            this.pobladoInicialColocado = true;
        } 
        else if (tipo.equalsIgnoreCase("camino")) {
            if (caminoInicialColocado) throw new RuntimeException("Ya colocaste tu camino esta ronda.");
            juego.jugadorActual().colocarPiezaInicial(tipo, ubicacion);
            this.caminoInicialColocado = true;
        }
        
        notificarObservadores("CONSTRUCCION_INICIAL");
        notificarObservadores("PV");
    }
    
    public boolean yaPusoPobladoInicial() { return pobladoInicialColocado; }
    public boolean yaPusoCaminoInicial() { return caminoInicialColocado; }
    
    public boolean esFaseInicial() {
        return juego.esFaseInicial(); 
    }
   
    public boolean seTiraronDados() {
        return dadosTirados;
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
    public Map<String, Integer> obtenerConteoCartasDesarrolloJugadorActual() {
        Jugador jugador = juego.jugadorActual();
        Map<String, Integer> conteo = new HashMap<>();

        for (Carta carta : jugador.obtenerCartasDesarrollo()) {
            String nombre = carta.getNombre();
            conteo.put(nombre, conteo.getOrDefault(nombre, 0) + 1);
        }

        return conteo;
    }

    public void usarCartaDesarrollo(String nombreCarta) {
        Jugador jugador = juego.jugadorActual();
        List<Carta> cartas = jugador.obtenerCartasDesarrollo();
       // Jugador actual = juego.obtenerCartaGranCaballeria().obtenerBonificado();

        for (Carta carta : cartas) {
            if (carta.getNombre().equals(nombreCarta)) {
                jugador.jugarCartaDesarrollo(carta);
                if(carta.modificaRecursos()){
                    notificarObservadores("RECURSOS");
                }
                notificarObservadores("CARTAS");

                if (jugador.gano()) {
                    notificarObservadores("FIN_JUEGO");
                }
                return;
            }
        }
        throw new NoTieneCarta("El jugador no tiene una carta de tipo " + nombreCarta);
    }
    public void comprarCartaDesarrollo() {
        juego.comprarCartaDesarrollo();
        notificarObservadores("RECURSOS");
        notificarObservadores("CARTAS");
    }
    public void configurarCartaMonopolio(String nombreRecurso) {

        Jugador jugadorActual = juego.jugadorActual();
        Recurso recurso = crearRecursoPorNombre(nombreRecurso);

        for (Carta carta : jugadorActual.obtenerCartasDesarrollo()) {
            if (carta.getNombre().equals("Monopolio")) {
                carta.configurarRecurso(recurso);
                List<Jugador> victimas = new ArrayList<>();
                for (Jugador j : juego.jugadores()) {
                    if (!j.equals(jugadorActual)) {
                        victimas.add(j);
                    }
                }
                carta.configurarVictimas(victimas);
                return;
            }
        }
        throw new NoTieneCarta("No tenés una carta Monopolio.");
    }

    public void configurarCartaDescubrimiento(String nombreRecurso1, String nombreRecurso2) {
        Jugador jugadorActual = juego.jugadorActual();
        Recurso recurso1 = crearRecursoPorNombre(nombreRecurso1);
        Recurso recurso2 = crearRecursoPorNombre(nombreRecurso2);

        List<Recurso> recursos = new ArrayList<>();
        recursos.add(recurso1);
        recursos.add(recurso2);

        for (Carta carta : jugadorActual.obtenerCartasDesarrollo()) {
            if (carta.getNombre().equals("Descubrimiento")) {
                carta.configurarRecursos(recursos);
                return;
            }
        }

        throw new NoTieneCarta("No tenés una carta Descubrimiento.");
    }
    /*public void configurarCartaCaballero(UbicacionVertice destino, String nombreVictima) {
        Jugador jugadorActual = juego.jugadorActual();
        Jugador victima = buscarJugadorPorNombre(nombreVictima);
        for (Carta carta : jugadorActual.obtenerCartasDesarrollo()) {
            if (carta.getNombre().equals("Caballero")) {
                carta.configurarLadron(destino, victima);
                return;
            }
        }
        throw new NoTieneCarta("No tenés una carta Caballero.");
    }*/
    public void configurarCartaConstruccionCarreteras(List<Ubicacion> camino1, List<Ubicacion> camino2) {
        Jugador jugadorActual = juego.jugadorActual();

        for (Carta carta : jugadorActual.obtenerCartasDesarrollo()) {
            if (carta.getNombre().equals("Construccion de carreteras")) {
                carta.configurarCaminos(camino1, camino2);
                return;
            }
        }
        throw new NoTieneCarta("No tenés carta Construccion de carreteras.");
    }

    private void agregarRutasBonificador(){
        for(Jugador jugador : juego.jugadores()){
            bonificadorRutaMayor.agregarRuta(jugador.obtenerRuta());
        }
    }
    public void activarModoConstruccionCarreteras() {
        modoConstruccionCarreteras = true;
        caminosCarta.clear();
    }

    public boolean estaEnModoConstruccionCarreteras() {
        return modoConstruccionCarreteras;
    }

    public void registrarCaminoParaCarta(List<Ubicacion> camino) {
        if (!modoConstruccionCarreteras) return;
        caminosCarta.add(camino);
        if (caminosCarta.size() == 2) {
            Jugador jugador = juego.jugadorActual();
            for (Carta c : jugador.obtenerCartasDesarrollo()) {
                if (c.getNombre().equals("Construccion de carreteras")) {
                    c.configurarCaminos(caminosCarta.get(0), caminosCarta.get(1));
                    jugador.jugarCartaDesarrollo(c);
                    break;
                }
            }
            modoConstruccionCarreteras = false;
            caminosCarta.clear();
            notificarObservadores("CONSTRUCCION");
            notificarObservadores("CARTAS");
        }
    }

    public Jugador obtenerJugadorConRutaMayor(){
        return bonificadorRutaMayor.getRutaMayor().obtenerPropietario();
    }

    public Jugador obtenerJugadorConGranCaballeria(){
        return juego.obtenerCartaGranCaballeria().obtenerBonificado();
    }
    
    public List<String> obtenerVictimasPosibles(UbicacionVertice ubicacionHexagono) {
        List<String> victimas = new ArrayList<>();
        Vertice vertice = Tablero.getInstance().getTerreno(ubicacionHexagono);
        
        if (vertice instanceof VerticeTerreno) {
            VerticeTerreno terreno = (VerticeTerreno) vertice;
            for (Jugador j : juego.jugadores()) {
                if (!j.equals(juego.jugadorActual())) {
                    if (terreno.poseePiezaAdyacente(j)) {
                        victimas.add(j.nombre());
                    }
                }
            }
        }
        return victimas;
    }
    public void activarTurnoLadronPorCaballero() {
        if (esperandoMovimientoLadron) return;
        this.esperandoMovimientoLadron = true;
        notificarObservadores("LADRON");
    }
}
