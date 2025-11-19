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
	}
	
	public void incorporarCiudad(Ciudad ciudad) {
		ciudades.add(ciudad);
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

    
    public void pagarRecursos(List<Recurso> precio) {
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
   

    //suma de cantidades en la lista de recursos
    public int totalRecursos() {
        int total = 0;
        for (Recurso r : recursos) {
            total += r.cantidad();
        }
        return total;
    }

    //descarta la mitad empezando por las prime cartas
    public void descartarMitad() {
        int total = totalRecursos();
        if (total <= 7) return;

        int aDescartar = total / 2; // floor

        // Recorremos la lista de recursos descontando cantidades
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
    
    // Para verif en los tests
    public int cantidadDeCartas() {
        return totalRecursos();
    }

    public void removerPoblado(Poblado p) {
        poblados.remove(p);
    }

    public boolean esJugador(Jugador propietario) {
        return this.nombre.equals(propietario.nombre);
    }

    public int puntosDeVictoria(){return puntosDeVictoria;}
}
  
