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

    public void colocarPiezaFija(String tipo, int ubicacion) {
        Pieza pieza = Pieza.crear(tipo, this);
        pieza.colocarPiezaFija(ubicacion);
        switch (tipo) {
            case "poblado":
                this.poblados.add((Poblado) pieza);
            case "ciudad":
                this.ciudades.add((Ciudad) pieza);
            default:
                throw new IllegalArgumentException(
                        "Tipo de pieza no válido: " + tipo + ". Debe ser 'poblado' o 'ciudad'.");
        }
    }

    public String obtenerNombre() {
        return nombre;
    }

    public void colocarPiezaCamino(String tipo, int ubicacion1, int ubicacion2) {
        Pieza pieza = Pieza.crear(tipo, this);
        pieza.colocarPiezaCamino(ubicacion1, ubicacion2);
        switch (tipo) {
            case "Camino":
                this.caminos.add((Camino) pieza);
            default:
                throw new IllegalArgumentException(
                        "Tipo de pieza no válido: " + tipo + ". Debe ser 'camino'.");
        }
    }
	
	public void elegirColocazionPieza(String tipo) {
        Pieza pieza = Pieza.crear(tipo, this);
		pieza.colocar();
	}
	
	public void turno() {
		 System.out.println("opciones de jugador en su turno");
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
