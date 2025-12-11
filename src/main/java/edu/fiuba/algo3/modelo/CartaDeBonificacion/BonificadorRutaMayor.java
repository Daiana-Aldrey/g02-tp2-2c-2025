package edu.fiuba.algo3.modelo.CartaDeBonificacion;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;

public class BonificadorRutaMayor {
    private List<RutaMayor> rutas;
    private boolean primerPunto;
    RutaMayor mayorRuta;

    public BonificadorRutaMayor(){
        this.rutas = new ArrayList<>();
        primerPunto = false;
        mayorRuta = new RutaMayor(new Jugador("ninguno"), new ArrayList<>());
    }

    public void agregarRuta(RutaMayor ruta){
        this.rutas.add(ruta);
    }

    public void bonificarPorRutaMayor(){
        RutaMayor anteriorMayorRuta = mayorRuta;
        for (RutaMayor ruta: rutas){
            if (ruta.sosMayor(mayorRuta)) {
                mayorRuta = ruta;
            }
        }
        if (!primerPunto){
            primerPunto = mayorRuta.otorgarPuntos();
        } else {
            if(!mayorRuta.equals(anteriorMayorRuta)){}
                mayorRuta.otorgarPuntos();
                anteriorMayorRuta.sacarPuntos();
        }
    }

    public RutaMayor getRutaMayor(){
        return mayorRuta;
    }

}
