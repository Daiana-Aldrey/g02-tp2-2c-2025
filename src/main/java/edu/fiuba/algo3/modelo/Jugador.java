package edu.fiuba.algo3.modelo;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Jugador {
	private String nombre;
	private List<Poblado> poblados;
	private List<Ciudad> ciudades;
	private List<Camino> caminos;
    private List<Recurso> recursos;

	
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.poblados = new ArrayList<Poblado>();
		this.ciudades = new ArrayList<Ciudad>();
		this.caminos = new ArrayList<Camino>();
		this.recursos = new ArrayList<Recurso>();
	}
	
	public Poblado elegirPrimerPoblado() {
		Scanner scanner = new Scanner(System.in);

        System.out.println(nombre + ", elige el número del vértice donde quieres colocar tu primer poblado:");
        int numVertice = scanner.nextInt();
		Poblado nuevoPoblado = new Poblado(this);
		poblados.add(nuevoPoblado);
		
		return nuevoPoblado;
	}

    public void recibirRecurso(String tipo, int cantidad) {
        for (Recurso recurso : recursos) {
            if (recurso.sosTipo(tipo)) {
                recurso.incrementar(cantidad);
                return;
            }
        }

        Recurso nuevo = new Recurso(tipo);
        nuevo.incrementar(cantidad);
        recursos.add(nuevo);
    }
}
