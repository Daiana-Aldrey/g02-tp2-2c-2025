package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.CartaDeDesarrollo.Carta;
import edu.fiuba.algo3.modelo.Intercambio.Puerto;
import edu.fiuba.algo3.modelo.Pieza.*;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;

import java.util.List;
import java.util.ArrayList;
import java.util.*;

public class Jugador {
	private String nombre;
    private int puntosDeVictoria = 0;
	private List<Recurso> recursos;
    private List<Poblado> poblados = new ArrayList<>();
    private List<Ciudad>  ciudades = new ArrayList<>();
    private List<Camino>  caminos  = new ArrayList<>();
    private List<Puerto> puertos = new ArrayList<>();
    //private List<Recurso> recursos = new ArrayList<>();
    //ENTREGA2
    private List<Carta> cartasDesarrollo = new ArrayList<>();
    private List<Carta> cartasDesarrolloRecienCompradas = new ArrayList<>();


    public Jugador(String nombre) {
		this.nombre = nombre;
		this.recursos = new ArrayList<Recurso>();
		
		inicializarRecursos();
	}


	public void incorporarCamino(Camino camino) {
		caminos.add(camino);
	}
    
	public void incorporarPoblado(Poblado poblado) {
		poblados.add(poblado);
        puntosDeVictoria += 1;
	}
	
	public void incorporarCiudad(Ciudad ciudad) {
        ciudades.add(ciudad);
        puntosDeVictoria += 2;
	}
	
	public void colocarPiezaInicial(String tipo, List<UbicacionVertice> ubicacion) {
	    Pieza pieza = Pieza.crear(tipo, this);
	    pieza.colocarPrimera(ubicacion);
	}    

	private void inicializarRecursos() {
		recursos.add(new Madera());
        recursos.add(new Mineral());
        recursos.add(new Ladrillo());
        recursos.add(new Lana());
        recursos.add(new Grano());
	}
	
	public void recibirRecurso(Recurso recursoARecibir, int cantidad) {
	    for (Recurso miRecurso : recursos) {
	        if (recursoARecibir.podesIncrementar(miRecurso, cantidad)) {
	            return;
	        }
	    }
	}

    //metodo solo para tests
    public Recurso buscarRecurso(Recurso recursoBuscado) {
        for (Recurso r : recursos) {
            if (r.getClass() == recursoBuscado.getClass()) {
                return r;
            }
        }
        return null;
    }

    public void construirPieza(String tipo, List<UbicacionVertice> ubicacion) {
        Pieza pieza = Pieza.crear(tipo, this);
        List<Recurso> precio = pieza.costoDeConstruccion();
        pagarRecursos(precio);
        pieza.colocar(ubicacion);
    }


    public void pagarRecursos(List<Recurso> precio) {
        for (Recurso rPrecio : precio) {
            rPrecio.cobrarDe(this);
        }
    }

    public void descontarRecurso(Recurso recursoADecrementar, int cantidad) {
        for (Recurso miRecurso : recursos) {
            if(recursoADecrementar.podesDecrementar(miRecurso, cantidad)){
                return;
            }
        }
        throw new IllegalArgumentException("No posees cantidad suficiente");
    }
    
    public void moverLadron(UbicacionVertice ubicacion, Jugador victima) {
    	Tablero tablero = Tablero.getInstance();
    	tablero.moverLadronA(ubicacion, this,victima);
    }
    
	public void turno() { 	
		System.out.print("acciones");
	}
   
	
    public int totalRecursos() {
        int total = 0;
        for (Recurso r : recursos) {
            total += r.cantidad();
        }
        return total;
    }

 
    public void descartarMitad() {
        int total = totalRecursos();
        if (total <= 7) return;

        int aDescartar = total / 2;

        int i = 0;
        while (aDescartar > 0 && i < recursos.size()) {
            Recurso r = recursos.get(i);
            int disponible = r.cantidad();
            int tomar = Math.min(disponible, aDescartar);

            if (tomar > 0) {
                r.decrementar(tomar);
                aDescartar -= tomar;
            }

            if (r.cantidad() == 0) {
                recursos.remove(i);
            } else {
                i++;
            }
        }
    }

    public void robarCartaAleatoriaA(Jugador victima) {
        List<Recurso> robables = new ArrayList<>();
        for (Recurso r : victima.recursos) {
            if (r.cantidad() > 0) {   
                robables.add(r);
            }
        }

        if (robables.isEmpty()) {
            return;
        }

        Random random = new Random();
        Recurso elegido = robables.get(random.nextInt(robables.size()));
        elegido.transferirA(this, 1);
    }
    
    
    public void intercambiar(List<Recurso> pedidos, List<Recurso> ofertas, Jugador ofertante) {
        if (pedidos == null || pedidos.isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos un recurso pedido");
        }
        if (ofertas == null || ofertas.isEmpty()) {
            throw new IllegalArgumentException("Debe haber al menos un recurso ofertado");
        }

        for (Recurso pedido : pedidos) {
            pedido.cobrarDe(this, ofertante);
        }

        for (Recurso oferta : ofertas) {
            oferta.cobrarDe(ofertante, this);
        }
    }
    
    public void entregar(Recurso recursoAEntregar, int cantidad, Jugador destino) {
        recursoAEntregar.transferirA(destino, cantidad);
    }

    public  void removerPoblado(UbicacionVertice ubicacion) {
        int i = 0;
        boolean encontrado = false;
        Pieza poblado;
        while ( i < poblados.size() && !encontrado) {
            poblado = poblados.get(i);
            if (poblado.tenesUbicacion(ubicacion)) {
                encontrado = true;
                poblados.remove(i);
                puntosDeVictoria -= 1;
            }
            i++;
        }
    }
    //ENTREGA2
    public void recibirCartaDesarrollo(Carta carta) {
        cartasDesarrolloRecienCompradas.add(carta);
    }
    //al terminar el turno en el cual compro cartas mi lista de cartasDesarrolloRecienCompradasdebe estar vacia nuevante
    public void prepararCartasDesarrolloParaNuevoTurno() {
        cartasDesarrollo.addAll(cartasDesarrolloRecienCompradas);
        cartasDesarrolloRecienCompradas.clear();
    }
    public void jugarCartaDesarrollo(Carta carta) {
        if (cartasDesarrolloRecienCompradas.contains(carta)) {
            throw new IllegalStateException("ERROR carta recein comprada no la podes usar en este turno.");
        }
        if (!cartasDesarrollo.contains(carta)) {
            throw new IllegalArgumentException("ERROR no tenes esta carta");
        }
        carta.usar(this);
        cartasDesarrollo.remove(carta);
    }



    //como el jugador debe poder ver que cartar de desarrollotiene puedo hacer:
    public List<Carta> obtenerCartasDesarrollo() {
        List<Carta> todas = new ArrayList<>();
        todas.addAll(cartasDesarrollo);
        todas.addAll(cartasDesarrolloRecienCompradas);
        return Collections.unmodifiableList(todas);
        //uso: unmodifiableList para respetar encapsulamiento y single responsability
    }

    public boolean esJugador(Jugador propietario) {
        return this.nombre.equals(propietario.nombre);
    }
    
    public int cantidadDeCartas() {
        return totalRecursos();
    }

    public int puntosDeVictoria(){return puntosDeVictoria;}

    public boolean tenesPobladoEnUbicacion(UbicacionVertice ubicacion) {
        int i = 0;
        boolean encontrado = false;
        Poblado poblado;
        while ( i < poblados.size() && !encontrado) {
            poblado = poblados.get(i);
            if (poblado.tenesUbicacion(ubicacion)) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }

    public boolean tenesPiezaEnUbicacion(UbicacionVertice ubicacion) {
        int i = 0;
        boolean encontrado = false;
        Camino camino;
        while ( i < caminos.size() && !encontrado) {
            camino = caminos.get(i);
            if (camino.tenesUbicacion(ubicacion)) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }

    public int seleccionarTasaPara(Recurso recurso){
        int mejor = 4;

        for(Puerto puerto : puertos){
            int posible = puerto.tasaPara(recurso);
            if(posible < mejor){
                mejor = posible;
            }
        }

        return mejor;
    }

    public void agregarPuerto(Puerto puerto){
        puertos.add(puerto);
    }
}
  
