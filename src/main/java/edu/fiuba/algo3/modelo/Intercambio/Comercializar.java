package edu.fiuba.algo3.modelo.Intercambio;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

public interface Comercializar {
    void comercializar(Jugador jugador,Recurso recursoOferta,Recurso recursoPedido, int cantidadPedida);
}
