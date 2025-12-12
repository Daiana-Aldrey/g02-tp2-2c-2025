package edu.fiuba.algo3.modelo.Intercambio;

import java.util.List;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Ubicacion.*;
import edu.fiuba.algo3.modelo.Pieza.*;
import edu.fiuba.algo3.Excepciones.*;

public abstract class Puerto implements Comercializar {
    protected Muelle muelle1;
    protected Muelle muelle2;

    protected Puerto(UbicacionVertice muelle1, UbicacionVertice muelle2) {
        this.muelle1 = new Muelle(muelle1);
        this.muelle2 = new Muelle(muelle2);
    }

    public final void notificarConstruccion(UbicacionVertice ubicacion, Pieza pieza) {
        muelle1.notificarConstruccion(ubicacion, pieza);
        muelle2.notificarConstruccion(ubicacion, pieza);
    }

    public boolean jugadorTieneAcceso(Jugador jugador) {
        return muelle1.perteneceAJugador(jugador) ||
               muelle2.perteneceAJugador(jugador);
    }
    
    public final void comercializar(Jugador jugador,Recurso oferta,Recurso pedido,int cantPedida) {

        if (!jugadorTieneAcceso(jugador)) {
            throw new PuertoNoAccesible("El jugador no tiene acceso a este puerto");
        }

        realizarComercio(jugador, oferta, pedido, cantPedida);
    }

    protected abstract void realizarComercio(Jugador jugador, Recurso oferta, Recurso pedido, int cantPedida);
    public abstract int tasaDeCambioPara(Recurso recurso);
    
    public Muelle getMuelle1() { return muelle1; }
    public Muelle getMuelle2() { return muelle2; }

}
