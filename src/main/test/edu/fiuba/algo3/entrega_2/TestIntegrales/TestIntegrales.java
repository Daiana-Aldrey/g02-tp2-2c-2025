package edu.fiuba.algo3.entrega_2.TestIntegrales;

import edu.fiuba.algo3.Excepciones.ColocacionInvalida;
import edu.fiuba.algo3.modelo.CartaDeDesarrollo.Carta;
import edu.fiuba.algo3.modelo.Dados.*;
import edu.fiuba.algo3.modelo.GeneradorNumerosAleatorios;
import edu.fiuba.algo3.modelo.Intercambio.*;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Pieza.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Terreno.Bosque;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import edu.fiuba.algo3.modelo.Intercambio.PuertoEspecifico;
import edu.fiuba.algo3.Excepciones.ErrorNoUsoDeCartaInvalido;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestIntegrales {

    @Test
    public void validacionDelConsumoDeRecursosYLaCorrectaColocacionDeUnaCarretera() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Jugador luis = new Jugador("Luis");
        Jugador marcelo = new Jugador("Marcelo");

        luis.colocarPiezaInicial("poblado", new ArrayList<>(List.of(new UbicacionVertice(1))));
        luis.colocarPiezaInicial("camino", new ArrayList<>(List.of(new UbicacionVertice(1), new UbicacionVertice(2))));

        marcelo.colocarPiezaInicial("poblado", new ArrayList<>(List.of(new UbicacionVertice(18))));
        marcelo.colocarPiezaInicial("camino", new ArrayList<>(List.of(new UbicacionVertice(18), new UbicacionVertice(17))));

        luis.recibirRecurso(new Madera(), 10);
        luis.recibirRecurso(new Ladrillo(), 10);
        luis.recibirRecurso(new Lana(), 10);
        luis.recibirRecurso(new Grano(), 10);

        marcelo.recibirRecurso(new Madera(), 2);
        marcelo.recibirRecurso(new Ladrillo(), 2);
        marcelo.recibirRecurso(new Lana(), 2);
        marcelo.recibirRecurso(new Grano(), 2);

        luis.construirPieza("camino", new ArrayList<>(List.of(new UbicacionVertice(1), new UbicacionVertice(9))));
        marcelo.construirPieza("camino", new ArrayList<>(List.of(new UbicacionVertice(18), new UbicacionVertice(19))));

        Assertions.assertTrue(tablero.hayCamino(new UbicacionVertice(1), new UbicacionVertice(9)));
        Assertions.assertTrue(tablero.hayCamino(new UbicacionVertice(18), new UbicacionVertice(19)));

        assertEquals(9, luis.buscarRecurso(new Madera()).cantidad());
        assertEquals(9, luis.buscarRecurso(new Ladrillo()).cantidad());
        assertEquals(10, luis.buscarRecurso(new Lana()).cantidad());
        assertEquals(10, luis.buscarRecurso(new Grano()).cantidad());

        assertEquals(1, marcelo.buscarRecurso(new Madera()).cantidad());
        assertEquals(1, marcelo.buscarRecurso(new Ladrillo()).cantidad());
        assertEquals(2, marcelo.buscarRecurso(new Lana()).cantidad());
        assertEquals(2, marcelo.buscarRecurso(new Grano()).cantidad());

    }

    @Test
    public void validacionDelConsumoDeRecursosYValidacionDeLaReglaDeLaDistanciaAlConstruirPoblado() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Jugador luis = new Jugador("Luis");
        Jugador marcelo = new Jugador("Marcelo");

        luis.colocarPiezaInicial("poblado", new ArrayList<>(List.of(new UbicacionVertice(2))));
        luis.colocarPiezaInicial("camino", new ArrayList<>(List.of(new UbicacionVertice(2), new UbicacionVertice(3))));

        marcelo.colocarPiezaInicial("poblado", new ArrayList<>(List.of(new UbicacionVertice(17))));
        marcelo.colocarPiezaInicial("camino", new ArrayList<>(List.of(new UbicacionVertice(18), new UbicacionVertice(17))));

        luis.recibirRecurso(new Madera(), 11);
        luis.recibirRecurso(new Ladrillo(), 11);
        luis.recibirRecurso(new Lana(), 10);
        luis.recibirRecurso(new Grano(), 10);

        marcelo.recibirRecurso(new Madera(), 4);
        marcelo.recibirRecurso(new Ladrillo(), 4);
        marcelo.recibirRecurso(new Lana(), 3);
        marcelo.recibirRecurso(new Grano(), 3);

        luis.construirPieza("camino", new ArrayList<>(List.of(new UbicacionVertice(3), new UbicacionVertice(4))));
        luis.construirPieza("poblado", new ArrayList<>(List.of(new UbicacionVertice(4))));
        marcelo.construirPieza("camino", new ArrayList<>(List.of(new UbicacionVertice(18), new UbicacionVertice(19))));
        marcelo.construirPieza("poblado", new ArrayList<>(List.of(new UbicacionVertice(19))));

        Assertions.assertTrue(tablero.hayEdificio(new UbicacionVertice(4)));
        Assertions.assertTrue(tablero.hayEdificio(new UbicacionVertice(19)));

        assertEquals(9, luis.buscarRecurso(new Madera()).cantidad());
        assertEquals(9, luis.buscarRecurso(new Ladrillo()).cantidad());
        assertEquals(9, luis.buscarRecurso(new Lana()).cantidad());
        assertEquals(9, luis.buscarRecurso(new Grano()).cantidad());

        assertEquals(2, marcelo.buscarRecurso(new Madera()).cantidad());
        assertEquals(2, marcelo.buscarRecurso(new Ladrillo()).cantidad());
        assertEquals(2, marcelo.buscarRecurso(new Lana()).cantidad());
        assertEquals(2, marcelo.buscarRecurso(new Grano()).cantidad());

        assertThrows(ColocacionInvalida.class, () -> {
            luis.construirPieza("poblado", new ArrayList<>(List.of(new UbicacionVertice(19))));;
        });
        assertThrows(ColocacionInvalida.class, () -> {
            marcelo.construirPieza("poblado", new ArrayList<>(List.of(new UbicacionVertice(5))));;
        });
        assertThrows(ColocacionInvalida.class, () -> {
            luis.construirPieza("poblado", new ArrayList<>(List.of(new UbicacionVertice(1))));;
        });
        assertThrows(ColocacionInvalida.class, () -> {
            marcelo.construirPieza("poblado", new ArrayList<>(List.of(new UbicacionVertice(3))));;
        });
    }

    @Test
    public void mejorarPobladoACiudadConsumeRecursosYCambiaPV(){
        Tablero tablero = Tablero.getInstance();
        Grafo grafo = new Grafo();

        GeneradorDeTablero generador = new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        generador.generarEstructura(grafo);

        UbicacionVertice ubicacionA = new UbicacionVertice('A');
        UbicacionVertice ubicacion10 = new UbicacionVertice(10);
        Vertice verticeTerreno = new VerticeTerreno(ubicacionA, new Bosque(), 8);
        grafo.agregarVertice(verticeTerreno);
        grafo.agregarArista(ubicacion10,ubicacionA);
        tablero.setearGrafo(grafo);

        Jugador jugador1 = new Jugador("Juli");
 	    Jugador jugador2 = new Jugador("Valen");
 	    Jugador jugador3 = new Jugador("Sofi");
 	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
 	    Juego juego = new Juego(jugadores);

        assertEquals(0, jugador1.puntosDeVictoria());

        jugador1.colocarPiezaInicial("poblado", List.of(ubicacion10));
        assertEquals(1, jugador1.puntosDeVictoria());

        jugador1.recibirRecurso(new Grano(), 2);
        jugador1.recibirRecurso(new Mineral(), 3);

        jugador1.construirPieza("ciudad", List.of(ubicacion10));
        assertEquals(2, jugador1.puntosDeVictoria());

        Recurso mineral = jugador1.buscarRecurso(new Mineral());
        Recurso grano = jugador1.buscarRecurso(new Grano());
        assertEquals(0, mineral.cantidad());
        assertEquals(0, grano.cantidad());


        jugador1.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(18)));
        assertEquals(3, jugador1.puntosDeVictoria());
        jugador1.recibirRecurso(new Grano(), 2);
        jugador1.recibirRecurso(new Mineral(), 3);
        jugador1.construirPieza("ciudad", List.of(new UbicacionVertice(18)));
        assertEquals(4, jugador1.puntosDeVictoria());
    }

    public void comercioMaritimoAplicaTasaEstandarCuatroAUno() {
        Banco banco = Banco.getInstance();

        Jugador jugador = new Jugador("Valen");
        jugador.recibirRecurso(new Madera(), 4);
        banco.comercializar(jugador, new Madera(), new Grano(), 1);

        Recurso madera = jugador.buscarRecurso(new Madera());
        Recurso grano  = jugador.buscarRecurso(new Grano());

        assertEquals(0, madera.cantidad());
        assertEquals(1, grano.cantidad());
    }

    @Test
    public void comercioMaritimoAplicaTasaTresAUnoConPuertoGenerico() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Puerto puertoGenerico = null;
        for (Puerto p : tablero.getPuertos()) {
            if (p instanceof PuertoGenerico) {
                puertoGenerico = p;
                break;
            }
        }

        UbicacionVertice muelle = puertoGenerico.getMuelle1().getUbicacion();
        Jugador jugador = new Jugador("Valen");
        Pieza poblado = new Poblado(jugador);   
        tablero.colocarEdificio(muelle, poblado);

        jugador.recibirRecurso(new Madera(), 3);
        puertoGenerico.comercializar(jugador, new Madera(), new Lana(), 1);

        assertEquals(0, jugador.buscarRecurso(new Madera()).cantidad());
        assertEquals(1, jugador.buscarRecurso(new Lana()).cantidad());
    }


    @Test
    public void comercioMaritimoAplicaTasaDosAUnoConPuertoEspecificoDeMadera() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        UbicacionVertice v28 = new UbicacionVertice(1);
        UbicacionVertice v39 = new UbicacionVertice(2);

        Puerto puertoMadera = null;
        for (Puerto p : tablero.getPuertos()) {
            if (!(p instanceof PuertoEspecifico)) continue;

            UbicacionVertice u1 = p.getMuelle1().getUbicacion();
            UbicacionVertice u2 = p.getMuelle2().getUbicacion();

            boolean coincide = (u1.equals(v28) && u2.equals(v39)) ||
                               (u1.equals(v39) && u2.equals(v28));

            if (coincide) {
                puertoMadera = p;
                break;
            }
        }

        UbicacionVertice muelle = puertoMadera.getMuelle1().getUbicacion();

        Jugador jugador = new Jugador("Juli");
        Pieza poblado = new Poblado(jugador); 
        tablero.colocarEdificio(muelle, poblado);
        jugador.recibirRecurso(new Madera(), 2);
        puertoMadera.comercializar(jugador, new Madera(), new Mineral(), 1);

        assertEquals(0, jugador.buscarRecurso(new Madera()).cantidad());
        assertEquals(1, jugador.buscarRecurso(new Mineral()).cantidad());
    }

    @Test
    public void intercambioEntreJugadoresTransfiereRecursosCorrectamente() {
    	Jugador ofertante = new Jugador("Ofertante");
        Jugador receptor  = new Jugador("Receptor");

        ofertante.recibirRecurso(new Madera(), 5);
        ofertante.recibirRecurso(new Lana(), 3);
        receptor.recibirRecurso(new Ladrillo(), 4);
        receptor.recibirRecurso(new Mineral(), 2);

        List<Recurso> pedidos = List.of(
                new Ladrillo(1),
                new Mineral(1)
        );

        List<Recurso> ofertas = List.of(
                new Madera(2),
                new Lana(1)
        );

        int maderaOfAntes   = ofertante.buscarRecurso(new Madera()).cantidad();
        int lanaOfAntes     = ofertante.buscarRecurso(new Lana()).cantidad();
        int ladrilloOfAntes = ofertante.buscarRecurso(new Ladrillo()).cantidad();
        int mineralOfAntes  = ofertante.buscarRecurso(new Mineral()).cantidad();

        int maderaRecAntes   = receptor.buscarRecurso(new Madera()).cantidad();
        int lanaRecAntes     = receptor.buscarRecurso(new Lana()).cantidad();
        int ladrilloRecAntes = receptor.buscarRecurso(new Ladrillo()).cantidad();
        int mineralRecAntes  = receptor.buscarRecurso(new Mineral()).cantidad();

        receptor.intercambiar(pedidos, ofertas, ofertante);

        assertEquals(ladrilloRecAntes - 1, receptor.buscarRecurso(new Ladrillo()).cantidad());
        assertEquals(mineralRecAntes - 1,receptor.buscarRecurso(new Mineral()).cantidad());
        assertEquals(ladrilloOfAntes + 1, ofertante.buscarRecurso(new Ladrillo()).cantidad());
        assertEquals(mineralOfAntes + 1, ofertante.buscarRecurso(new Mineral()).cantidad());

        assertEquals(maderaOfAntes - 2, ofertante.buscarRecurso(new Madera()).cantidad());
        assertEquals(lanaOfAntes - 1, ofertante.buscarRecurso(new Lana()).cantidad());
        assertEquals(maderaRecAntes + 2, receptor.buscarRecurso(new Madera()).cantidad());
        assertEquals(lanaRecAntes + 1, receptor.buscarRecurso(new Lana()).cantidad());
    }
    @Test
    void validacionComprarCartaDesarrolloDescuentaRecursosAgregaCartaAlJugador() {
        Jugador jugador1 = new Jugador("Juli");
  	    Jugador jugador2 = new Jugador("Valen");
  	    Jugador jugador3 = new Jugador("Sofi");
  	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
  	    Juego juego = new Juego(jugadores);

        jugador1.recibirRecurso(new Lana(), 1);
        jugador1.recibirRecurso(new Grano(), 1);
        jugador1.recibirRecurso(new Mineral(), 1);

        int lanaAntes = jugador1.buscarRecurso(new Lana()).cantidad();
        int granoAntes = jugador1.buscarRecurso(new Grano()).cantidad();
        int mineralAntes = jugador1.buscarRecurso(new Mineral()).cantidad();

        juego.comprarCartaDesarrollo();

        assertEquals(lanaAntes - 1, jugador1.buscarRecurso(new Lana()).cantidad());
        assertEquals(granoAntes - 1, jugador1.buscarRecurso(new Grano()).cantidad());
        assertEquals(mineralAntes - 1, jugador1.buscarRecurso(new Mineral()).cantidad());

        assertEquals(1, jugador1.obtenerCartasDesarrollo().size(), "El jugador debería tener una carta por que la compro");
    }

    @Test
    void CartaRecienCompradaNoPuedeUsarseEnTurnoActual_SiTrasFinalizarTurno() {
         Jugador jugador1 = new Jugador("Juli");
   	    Jugador jugador2 = new Jugador("Valen");
   	    Jugador jugador3 = new Jugador("Sofi");
   	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
   	    Juego juego = new Juego(jugadores);


        jugador1.recibirRecurso(new Lana(), 1);
        jugador1.recibirRecurso(new Grano(), 1);
        jugador1.recibirRecurso(new Mineral(), 1);

        juego.comprarCartaDesarrollo();

        Carta carta = jugador1.obtenerCartasDesarrollo().get(0);

        assertThrows(ErrorNoUsoDeCartaInvalido.class, () -> jugador1.jugarCartaDesarrollo(carta), "No deberia usar la carta en el mismo turnoque la  compra.");

        juego.finalizarTurnoActual();

        assertDoesNotThrow(() -> jugador1.jugarCartaDesarrollo(carta), "finalizo el turno, podes usar la carta.");

        assertEquals(0, jugador1.obtenerCartasDesarrollo().size(), "usaste tu unica carta no tenes mas");
    }

}

