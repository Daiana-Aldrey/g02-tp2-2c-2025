package edu.fiuba.algo3.modelo.Ubicacion;

public class UbicacionVertice extends Ubicacion {
    private char ubicacion;

    public UbicacionVertice(char ubicacion) {
        this.ubicacion = ubicacion;
    }

    public UbicacionVertice(int ubicacion) {
        char ubicacionChar = (char) ubicacion;
        this.ubicacion = ubicacionChar;
    }

    @Override
    public boolean tieneUbicacion(char ubicacion) {
        return this.ubicacion == ubicacion;
    }

    @Override
    public boolean equals(Object obj){
        UbicacionVertice ubicacion = (UbicacionVertice) obj;
        return ubicacion.tieneUbicacion(this.ubicacion);
    }
}
