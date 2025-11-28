package edu.fiuba.algo3.modelo.Ubicacion;

public class NoUbicacionVertice extends UbicacionVertice {
    public NoUbicacionVertice() {
        super('N'); 
    }
 
    @Override
    public boolean tieneUbicacion(char ubicacion) {
        return false; 
    }
    
    @Override
    public boolean equals(Object obj){
        return false; 
    }
}
