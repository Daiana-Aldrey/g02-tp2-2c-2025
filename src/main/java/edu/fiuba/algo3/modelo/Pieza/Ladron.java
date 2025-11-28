package edu.fiuba.algo3.modelo.Pieza;
import edu.fiuba.algo3.modelo.Ubicacion.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.Excepciones.*;

public class Ladron {
    private VerticeTerreno ubicacion;
    

    public Ladron() {
        this.ubicacion = new NoVerticeTerreno();
    }

    public void moverA(VerticeTerreno nuevaUbicacion) {
        ubicacion.sacarLadron();  
        ubicacion = nuevaUbicacion;
    }
    
    public void robar(Jugador beneficiario, Jugador victima) {
    	boolean victimaValida = ubicacion.poseePiezaAdyacente(victima);
    	if(!victimaValida) {
    		throw new VictimaInvalida("Victima no valida");
    	}
    	beneficiario.robarCartaAleatoriaA(victima);
    }
}
