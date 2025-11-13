package edu.fiuba.algo3.modelo;
import java.util.List;
import java.util.ArrayList;

public class VerticeTerreno extends EnlazadorVertices {
     private char ubicacion;
     private List<Pieza> edificios;
     private Terreno terreno;

     public VerticeTerreno(char ubicacion, Terreno terreno){
        this.ubicacion = ubicacion;
        edificios = new ArrayList<>();
        this.terreno = terreno;
     }

    public boolean tieneUbicacion(char letra) {
         return this.ubicacion == letra;
    }

    public boolean tieneFichaDeNumero(int resultadoDados) {
         return terreno.esMiNumero(resultadoDados);
    }

    public void cosecharPara() {
         terreno.repartirRecurso(edificios);
    }

    public void agregarEdificio(Pieza edificio) {
         edificios.add(edificio);
    }
}
