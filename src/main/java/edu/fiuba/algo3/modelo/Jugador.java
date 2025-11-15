package edu.fiuba.algo3.modelo;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Jugador {
	private String nombre;

	private List<Recurso> recursos;
    //ELIMINAR DESPUES las dejo par que compile PREGUNTAR si no las vamos a usar mas
    private List<Poblado> poblados = new ArrayList<>();
    private List<Ciudad>  ciudades = new ArrayList<>();
    private List<Camino>  caminos  = new ArrayList<>();
    //private List<Recurso> recursos = new ArrayList<>();


    public Jugador(String nombre) {
		this.nombre = nombre;
		this.recursos = new ArrayList<Recurso>();
		
		inicializarRecursos(List.of(" MADERA", "LADRILLO", "LANA", "GRANO", "MINERAL"));
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
	
	 
	private void inicializarRecursos(List<String> tiposRecursos) {
		for(int i = 0; i < tiposRecursos.size(); i ++) {
			Recurso recurso = new Recurso(tiposRecursos.get(i));
			recursos.add(recurso);
		}
	}
	
    public void recibirRecurso(String tipo, int cantidad) {
        String t = tipo.trim().toUpperCase();
        for (Recurso recurso : recursos) {
            if (recurso.sosTipo(t)) {
                recurso.incrementar(cantidad);
                return;
            }
        }

        Recurso nuevo = new Recurso(t);
        nuevo.incrementar(cantidad);
        recursos.add(nuevo);
    }
    
    public Recurso buscarRecurso(String tipo) {
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

    void descontarRecurso(String tipo, int cantidad) {
        for (Recurso rJugador : recursos) {
            if (rJugador.sosTipo(tipo)) {
                rJugador.decrementar(cantidad);
                return;
            }
        }
    }
    
    public void moverLadron() {
    	
    }
    
	public void turno() { 	
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

    // Para verif en los tests
    public int cantidadDeCartas() {
        return totalRecursos();
    }
}
  
