package edu.fiuba.algo3.modelo;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Jugador {
	private String nombre;
    private int puntosDeVictoria = 0;
	private List<Recurso> recursos;
    private List<Poblado> poblados = new ArrayList<>();
    private List<Ciudad>  ciudades = new ArrayList<>();
    private List<Camino>  caminos  = new ArrayList<>();
    //private List<Recurso> recursos = new ArrayList<>();
    //ENTREGA2
    private List<Carta> cartasDesarrollo = new ArrayList<>();
    private List<Carta> cartasDesarrolloRecienCompradas = new ArrayList<>();


    public Jugador(String nombre) {
		this.nombre = nombre;
		this.recursos = new ArrayList<Recurso>();
		
		inicializarRecursos(List.of(RecursoTipo.MADERA, RecursoTipo.LADRILLO, RecursoTipo.LANA, RecursoTipo.GRANO, RecursoTipo.MINERAL));
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
	
	public void colocarPiezaInicial(String tipo, List<Integer> vertices) {
	    Pieza pieza = Pieza.crear(tipo, this);
	    pieza.colocar(vertices);   
	}    

	private void inicializarRecursos(List<RecursoTipo> tiposRecursos) {
		for(int i = 0; i < tiposRecursos.size(); i ++) {
			Recurso recurso = new Recurso(tiposRecursos.get(i));
			recursos.add(recurso);
		}
	}
	
	public void recibirRecurso(RecursoTipo tipo, int cantidad) {
	    for (Recurso recurso : recursos) {
	        if (recurso.sosTipo(tipo)) {
	            recurso.incrementar(cantidad);
	            return;
	        }
	    }
	    Recurso nuevo = new Recurso(tipo, cantidad);
	    recursos.add(nuevo);
	}

    public Recurso buscarRecurso(RecursoTipo tipo) {
        for (Recurso r : recursos) {
            if (r.sosTipo(tipo)) {
                return r;
            }
        }
        return null;
    }

    public void construirPieza(String tipo, List<Integer> vertices) {
        Pieza pieza = Pieza.crear(tipo, this);
        List<Recurso> precio = pieza.costoDeConstruccion();
        pagarRecursos(precio);
        pieza.colocar(vertices);
    }


    //consultarle a Dai sobre el uso de este
    /*
    public void pagarRecursos(List<Recurso> precio) {
        for (Recurso rPrecio : precio) {
            rPrecio.cobrarDe(this);
        }
    }
    */

    public void pagarRecursos(List<Recurso> precio) {
        //PRIMERO Verifico(solo miro)
        for (Recurso costo : precio) {
            Recurso recursoJugador = buscarRecurso(costo.tipo());
            //tiro ERROR Si no tiene el recurso o la cantidad es menor a la requerida
            if (recursoJugador == null || recursoJugador.cantidad() < costo.cantidad()) {
                throw new IllegalArgumentException("No posees cantidad suficiente de " + costo.tipo());
            }
        }
        for (Recurso rPrecio : precio) {
            rPrecio.cobrarDe(this);
        }
    }

    void descontarRecurso(RecursoTipo tipo, int cantidad) {
        for (Recurso rJugador : recursos) {
            if (rJugador.sosTipo(tipo)) {
                rJugador.decrementar(cantidad);
                return;
            }
        }
        throw new IllegalArgumentException("No posees cantidad suficiente de " + tipo);
    }
    
    public void moverLadron(char idTerreno) {
    	Tablero tablero = Tablero.getInstance();
    	tablero.moverLadronA(idTerreno, this);
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
    
    public void entregar(RecursoTipo tipo, int cantidad, Jugador destino) {
        buscarRecurso(tipo).transferirA(destino, cantidad);
    }

    
    public void removerPoblado(Poblado p) {
        poblados.remove(p);
        puntosDeVictoria -= 1;
    }

    public  void removerPoblado(int ubicacion) {
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

    public boolean tenesPobladoEnUbicacion(int ubicacion) {
        int i = 0;
        boolean encontrado = false;
        Pieza poblado;
        while ( i < poblados.size() && !encontrado) {
            poblado = poblados.get(i);
            if (poblado.tenesUbicacion(ubicacion)) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }


}
  
