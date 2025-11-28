package edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Terreno.NoTerreno;
import edu.fiuba.algo3.modelo.Ubicacion.NoUbicacion;

public class NoVerticeTerreno extends VerticeTerreno {
    public NoVerticeTerreno() {
        super(new NoUbicacion(), new NoTerreno(), 0);
    }
   
    @Override
    public void sacarLadron() {
    }
    
    @Override
    public boolean poseePiezaAdyacente(Jugador victima) { 
        return false;
    }
}