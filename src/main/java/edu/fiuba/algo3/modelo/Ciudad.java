package edu.fiuba.algo3.modelo;

import java.util.List;

public class Ciudad extends Pieza{
	private int ubicacion;
	
	public Ciudad(Jugador propietario) {
	    this.propietario = propietario; 
	    this.ubicacion = -1;
	}


    @Override
    public List<Recurso> costoDeConstruccion() {
        return List.of();
    }

    @Override
    public void colocar(List<Integer> vertice) {
        if (vertice.size() != 1) {
            throw new IllegalArgumentException("Una ciudad necesita exactamente 1 vértice");
        }

        int v = vertice.get(0);
        this.ubicacion = v;
        Tablero tablero = Tablero.getInstance();
        VerticeEdificio verticeEdificio = tablero.buscarVerticeEdificio(v);

        Pieza piezaActual = verticeEdificio.obtenerPieza();

        if(!piezaActual.esPoblado()){
            throw new IllegalArgumentException("Debe existir un poblado para construir una ciudad");
        }

        if(!(piezaActual.propietario.esJugador(this.propietario))){
            throw new IllegalArgumentException("El poblado no pertenece al jugador");
        }

        for(VerticeTerreno terreno : verticeEdificio.obtenerTerrenos()){
            terreno.removerEdificio(piezaActual);
        }
    
        this.propietario.removerPoblado((Poblado) piezaActual);
        verticeEdificio.colocarPieza(this);

        propietario.incorporarCiudad(this);
    }

    @Override
    public int ubicacion() {
        return 0; //modificar metodo, implementado unicamente para que proyecto pueda ser ejecutado
    }

    @Override
    public int produccion(){return 2;}
    public boolean esCiudad(){return true;}
}
