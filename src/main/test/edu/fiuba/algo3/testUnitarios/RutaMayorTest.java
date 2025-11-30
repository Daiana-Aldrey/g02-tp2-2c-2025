package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.modelo.Bonificacion.RutaMayor;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pieza.Camino;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class RutaMayorTest {
    @Test
    public void sePoneUnCaminoYseCuentaCorrectamente(){
        Jugador jugador1 = mock(Jugador.class);
        List<Camino> caminos = new ArrayList<>();
        RutaMayor medidor = new RutaMayor(jugador1, caminos);

        Camino camino1 = new Camino(jugador1);
        camino1.setearUbicacion(new UbicacionVertice(1));
        camino1.setearSegundaUbicacion(new UbicacionVertice(2));

        caminos.add(camino1);

        int esperado = 1;
        int obtenido = medidor.calcularRutaMayor();

        assertEquals(esperado, obtenido);
    }
    @Test
    public void sePoneUnaRutaDe3CaminosYSeCuentaCorrectamente() {
        Jugador jugador1 = new Jugador("Javier");
        List<Camino> caminos = new ArrayList<>();
        RutaMayor medidor = new RutaMayor(jugador1, caminos);

        Ubicacion ubicacion1 = new UbicacionVertice(1);
        Ubicacion ubicacion2 = new UbicacionVertice(2);
        Ubicacion ubicacion3 = new UbicacionVertice(3);
        Ubicacion ubicacion4 = new UbicacionVertice(4);

        Map<Ubicacion, List<Ubicacion>> adyacentes = new HashMap<>();
        adyacentes.put(ubicacion1, new ArrayList<>(List.of(ubicacion2)));
        adyacentes.put(ubicacion2, new ArrayList<>(List.of(ubicacion3, ubicacion1)));
        adyacentes.put(ubicacion3, new ArrayList<>(List.of(ubicacion4, ubicacion2)));
        adyacentes.put(ubicacion4, new ArrayList<>(List.of(ubicacion3)));

        List<Ubicacion> ubicaciones = new ArrayList<>(List.of(ubicacion1, ubicacion2, ubicacion3, ubicacion4));

        medidor.setearAdyacenteYUbicaciones(ubicaciones, adyacentes);

        int esperado = 3;
        int obtenido = medidor.calcularRutaMayor();
        assertEquals(esperado, obtenido);
    }

    @Test
    public void ExistenDosCaminosAisladosYSeCaptaLaRutaMasLarga() {
        Jugador jugador1 = new Jugador("Javier");
        List<Camino> caminos = new ArrayList<>();
        RutaMayor medidor = new RutaMayor(jugador1, caminos);

        Ubicacion ubicacion1 = new UbicacionVertice(1);
        Ubicacion ubicacion2 = new UbicacionVertice(2);
        Ubicacion ubicacion3 = new UbicacionVertice(3);
        Ubicacion ubicacion4 = new UbicacionVertice(4);
        Ubicacion ubicacion19 = new UbicacionVertice(19);
        Ubicacion ubicacion20 = new UbicacionVertice(20);

        Map<Ubicacion, List<Ubicacion>> adyacentes = new HashMap<>();
        adyacentes.put(ubicacion1, new ArrayList<>(List.of(ubicacion2)));
        adyacentes.put(ubicacion2, new ArrayList<>(List.of(ubicacion3, ubicacion1)));
        adyacentes.put(ubicacion3, new ArrayList<>(List.of(ubicacion4, ubicacion2)));
        adyacentes.put(ubicacion4, new ArrayList<>(List.of(ubicacion3)));

        adyacentes.put(ubicacion19, new ArrayList<>(List.of(ubicacion20)));
        adyacentes.put(ubicacion20, new ArrayList<>(List.of(ubicacion19)));

        List<Ubicacion> ubicaciones = new ArrayList<>(List.of(ubicacion1, ubicacion2, ubicacion3, ubicacion4, ubicacion19, ubicacion20));

        medidor.setearAdyacenteYUbicaciones(ubicaciones, adyacentes);

        int esperado = 3;
        int obtenido = medidor.calcularRutaMayor();
        assertEquals(esperado, obtenido);

    }

    @Test
    public void EnUnaRutaSeAbreUnaDiagonalYSeTomaComoRutaAparte() {
        Jugador jugador1 = new Jugador("Javier");
        List<Camino> caminos = new ArrayList<>();
        RutaMayor medidor = new RutaMayor(jugador1, caminos);

        Ubicacion ubicacion1 = new UbicacionVertice(1);
        Ubicacion ubicacion2 = new UbicacionVertice(2);
        Ubicacion ubicacion3 = new UbicacionVertice(3);
        Ubicacion ubicacion4 = new UbicacionVertice(4);
        Ubicacion ubicacion11 = new UbicacionVertice(11);
        Ubicacion ubicacion10 = new UbicacionVertice(10);

        Map<Ubicacion, List<Ubicacion>> adyacentes = new HashMap<>();
        adyacentes.put(ubicacion1, new ArrayList<>(List.of(ubicacion2)));
        adyacentes.put(ubicacion2, new ArrayList<>(List.of(ubicacion3, ubicacion1)));
        adyacentes.put(ubicacion3, new ArrayList<>(List.of(ubicacion4, ubicacion2, ubicacion11)));
        adyacentes.put(ubicacion4, new ArrayList<>(List.of(ubicacion3)));

        adyacentes.put(ubicacion11, new ArrayList<>(List.of(ubicacion3, ubicacion10)));
        adyacentes.put(ubicacion10, new ArrayList<>(List.of(ubicacion11)));

        List<Ubicacion> ubicaciones = new ArrayList<>(List.of(ubicacion1, ubicacion2, ubicacion3, ubicacion4, ubicacion11, ubicacion10));

        medidor.setearAdyacenteYUbicaciones(ubicaciones, adyacentes);

        int esperado = 4;
        int obtenido = medidor.calcularRutaMayor();
        assertEquals(esperado, obtenido);
    }

    @Test
    public void SeFormaUnaRutaMayorA4Caminos() {
        Jugador jugador1 = new Jugador("Javier");
        List<Camino> caminos = new ArrayList<>();
        RutaMayor medidor = new RutaMayor(jugador1, caminos);

        Ubicacion ubicacion1 = new UbicacionVertice(1);
        Ubicacion ubicacion2 = new UbicacionVertice(2);
        Ubicacion ubicacion3 = new UbicacionVertice(3);
        Ubicacion ubicacion9 = new UbicacionVertice(9);
        Ubicacion ubicacion11 = new UbicacionVertice(11);
        Ubicacion ubicacion10 = new UbicacionVertice(10);

        Map<Ubicacion, List<Ubicacion>> adyacentes = new HashMap<>();
        adyacentes.put(ubicacion1, new ArrayList<>(List.of(ubicacion2)));
        adyacentes.put(ubicacion2, new ArrayList<>(List.of(ubicacion3, ubicacion1)));
        adyacentes.put(ubicacion3, new ArrayList<>(List.of(ubicacion2, ubicacion11)));
        adyacentes.put(ubicacion11, new ArrayList<>(List.of(ubicacion3, ubicacion10)));
        adyacentes.put(ubicacion10, new ArrayList<>(List.of(ubicacion9, ubicacion11)));
        adyacentes.put(ubicacion9, new ArrayList<>(List.of(ubicacion10)));

        List<Ubicacion> ubicaciones = new ArrayList<>(List.of(ubicacion1, ubicacion2, ubicacion3, ubicacion9, ubicacion11, ubicacion10));

        medidor.setearAdyacenteYUbicaciones(ubicaciones, adyacentes);
        medidor.calcularRutaMayor();
        assertTrue(medidor.pasaCapacidadMinima());
    }

    @Test
    public void DevuelveCorrectamenteElObjetoQueTieneMayorRuta() {
        Jugador jugador1 = mock(Jugador.class);
        RutaMayor ruta1 = new RutaMayor(jugador1, new ArrayList<>());
        RutaMayor ruta2 = new RutaMayor(jugador1, new ArrayList<>());

        ruta1.setearRutaMayor(3);
        ruta2.setearRutaMayor(1);
        assertTrue(ruta1.sosMayor(ruta2));
    }
}