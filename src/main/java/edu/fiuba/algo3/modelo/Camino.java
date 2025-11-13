package edu.fiuba.algo3.modelo;

import java.util.*;

public class Camino extends Pieza {
	private List<Integer> ubicacion;
	
	public Camino(Jugador propietario) {
	    this.propietario = propietario; 
	    this.ubicacion = new ArrayList<>();
	}

    public void colocarPiezaCamino(int ubicacion1, int ubicacion2) {
        this.ubicacion.add(ubicacion1);
        this.ubicacion.add(ubicacion2);
        Tablero tablero = Tablero.getInstance();
        tablero.colocarPiezaCamino(ubicacion, this);
    }

    public String obtenerNombreJugador() {
        return propietario.obtenerNombre();
    }

    @Override
    public List<Recurso> costoDeConstruccion() {
        return List.of();
    }
    
    private void preguntarPosicion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Elegí los dos vértices del camino separándolos por coma (ej: 5,6):");

        String linea = scanner.nextLine();       
        String[] partes = linea.split(",");       

        if (partes.length != 2) {
            throw new IllegalArgumentException("Debes ingresar exactamente dos vértices sepasrados por coma.");
        }

        int v1 = Integer.parseInt(partes[0].trim()); 
        int v2 = Integer.parseInt(partes[1].trim());

 
       ubicacion.add(v1);
       ubicacion.add(v2);
       scanner.close();
    }

    @Override
    public void colocar() {
        Tablero tablero = Tablero.getInstance();
        tablero.colocarCamino(ubicacion, this); 
    }

    @Override 
    public int ubicacion() {
    	int unVertice = ubicacion.get(0);
        return unVertice;
    }
}