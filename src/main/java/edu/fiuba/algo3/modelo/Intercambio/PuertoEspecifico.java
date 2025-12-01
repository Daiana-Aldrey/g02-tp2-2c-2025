package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Intercambio.*;
import edu.fiuba.algo3.modelo.Ubicacion.*;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Jugador.*;

public class PuertoEspecifico extends Puerto {

    private final int tasa = 2;
    private final Class<? extends Recurso> tipoOferta;

    public PuertoEspecifico(Class<? extends Recurso> tipoOferta,
                            UbicacionVertice u1,
                            UbicacionVertice u2) {
        super(u1, u2);
        this.tipoOferta = tipoOferta;
    }

    private void verificarOferta(Recurso oferta) {
        if (!tipoOferta.isInstance(oferta)) {
            throw new IllegalArgumentException("Este puerto solo acepta: " + tipoOferta.getSimpleName());
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
