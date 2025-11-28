package edu.fiuba.algo3.modelo.Ubicacion;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.*;

public class NoVerticeTerreno extends VerticeTerreno {
    public NoVerticeTerreno() {
        super(new NoUbicacionVertice(), new NoTerreno(), 0); 
    }
   
    @Override
    public void sacarLadron() {
    }
    
    @Override
    public boolean poseePiezaAdyacente(Jugador victima) { 
        return false;
    }
}