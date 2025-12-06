package edu.fiuba.algo3.modelo.CartaDeDesarrollo;

import edu.fiuba.algo3.Excepciones.AccionNoPermitida;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import java.util.ArrayList;
import java.util.List;

public class CartaDescubrimiento extends Carta {
    public CartaDescubrimiento(){
        nombre = "Descubrimiento";
        descripcion = "Permite al jugador tomar dos recursos a su elección de la banca";
    }

    static final int CANTRECURSOSELEGIDOS = 2;

    private final List<Recurso> recursosElegidos = new ArrayList<>(CANTRECURSOSELEGIDOS);

    public void configurarEleccion(List<Recurso> recursos) {
        if (recursos.size() != CANTRECURSOSELEGIDOS) {
            throw new AccionNoPermitida("necesitas elegir 2 recursos.");
        }
        recursosElegidos.clear();
        recursosElegidos.addAll(recursos);
    }


    @Override
    public void usar(Jugador jugador) {
//        if (recursosElegidos.size() != CANTRECURSOSELEGIDOS) {
//            throw new AccionNoPermitida("error con los recursos elegidos.");
//        }

        for (Recurso recurso : recursosElegidos) {
            jugador.recibirRecurso(recurso, 1);
        }
    }

    public int puntosDeVictoriaOcultos() {
        return 0;
    }
}

