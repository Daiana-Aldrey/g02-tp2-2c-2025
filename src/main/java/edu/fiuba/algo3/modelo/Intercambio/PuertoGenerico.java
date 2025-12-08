package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Intercambio.*;
import edu.fiuba.algo3.modelo.Ubicacion.*;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Jugador.*;

public class PuertoGenerico extends Puerto {
    private final int tasa = 3;

    public PuertoGenerico(UbicacionVertice u1, UbicacionVertice u2) {
        super(u1, u2);
    }

    @Override
    public void realizarComercio(Jugador jugador,Recurso oferta,Recurso pedido,int cantPedida) {
        int costo = tasa * cantPedida;
        Banco.getInstance().intercambiar(jugador, oferta, pedido,cantPedida,costo);
    }
    
    @Override
    public int tasaDeCambioPara(Recurso recurso) {
        return 3; 
    }
}
