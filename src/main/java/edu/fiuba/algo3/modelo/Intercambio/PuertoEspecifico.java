package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Intercambio.*;
import edu.fiuba.algo3.modelo.Ubicacion.*;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Jugador.*;
import edu.fiuba.algo3.Excepciones.*;

public class PuertoEspecifico extends Puerto {

    private final int tasa = 2;
    private final Recurso oferta;

    public PuertoEspecifico(Recurso oferta, UbicacionVertice u1, UbicacionVertice u2) {
        super(u1, u2);
        this.oferta = oferta;
    }

    private void verificarOferta(Recurso ofertaJugador) {
        try {
            this.oferta.podesIncrementar(ofertaJugador, 0);
            
        } catch (RuntimeException e) {
            throw new RecursoOfertaIncorrecto("Este puerto solo acepta: " + oferta.getClass().getSimpleName());
        }
    }

    @Override
    public  void realizarComercio(Jugador jugador,
            Recurso oferta,
            Recurso pedido,
            int cantPedida) {

        verificarOferta(oferta);
        int costo = tasa * cantPedida;
        Banco.getInstance().intercambiar(jugador, oferta, pedido, cantPedida, costo);
    }
}
