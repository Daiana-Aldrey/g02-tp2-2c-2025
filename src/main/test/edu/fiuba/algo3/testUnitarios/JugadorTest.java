package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.Excepciones.ColocacionInvalida;
import edu.fiuba.algo3.modelo.Dados.GeneradorDeDados;
import edu.fiuba.algo3.modelo.GeneradorNumerosAleatorios;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Pieza.Poblado;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Terreno.Montania;
import edu.fiuba.algo3.modelo.Terreno.Terreno;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;


public class JugadorTest {
	@Test
	public void jugadorInicializaCorrectamenteLosRecursos() {
	    Jugador jugador = new Jugador("Lu");

	    assertNotNull(jugador.buscarRecurso(new Madera()));
	    assertNotNull(jugador.buscarRecurso(new Ladrillo()));
	    assertNotNull(jugador.buscarRecurso(new Lana()));
	    assertNotNull(jugador.buscarRecurso(new Grano()));
	    assertNotNull(jugador.buscarRecurso(new Mineral()));
	}

	@Test
	public void jugadorPuedeConstruirPobladoSiTieneRecursosSuficientes() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();
        GeneradorDeDados dadoPrueba = () -> 7;

        Jugador jugador1 = new Jugador("Juli");
	    Jugador jugador2 = new Jugador("Valen");
	    Jugador jugador3 = new Jugador("Sofi");
	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
	    Juego juego = new Juego(jugadores, dadoPrueba);

        Jugador jugador = juego.jugadores().get(0);
        jugador.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(4)));
        jugador.colocarPiezaInicial("camino", List.of(new UbicacionVertice(3), new UbicacionVertice(4)));

        jugador.recibirRecurso(new Madera(), 1);
        jugador.recibirRecurso(new Ladrillo(), 1); //cambiar

        jugador.construirPieza("camino",List.of(new UbicacionVertice(2), new UbicacionVertice(3)));
        Pieza pobladoDeReferencia = Pieza.crear("poblado", jugador);
        List<Recurso> precio = pobladoDeReferencia.costoDeConstruccion();

        for (Recurso costo : precio) {
            jugador.recibirRecurso(costo, costo.cantidad());
        }
  
        List<Ubicacion> ubicacion = List.of(new UbicacionVertice(2));
        jugador.construirPieza("poblado", ubicacion);

        Assertions.assertTrue(tablero.hayEdificio(ubicacion.get(0)));
        Assertions.assertEquals(0, jugador.cantidadDeCartas());
    }

	

    @Test
    public void jugadorRecibeRecursoDelTerreno() {
    	Tablero tablero = Tablero.getInstance();
        tablero.reset();
      
        Jugador jugador = new Jugador("Luis");
        Terreno montania = new Montania();
        VerticeTerreno vt = new VerticeTerreno(new UbicacionVertice('A'), montania, 8);
        Poblado p = new Poblado(jugador);
        
        vt.agregarEdificio(p);
        vt.cosecharTerreno();
        
        Recurso mineral = jugador.buscarRecurso(new Mineral());
        assertEquals(1, mineral.cantidad());
    }

    @Test
    public void jugadorPagaRecursosCorrectamente() {
        Jugador jugador = new Jugador("Luis");

        jugador.recibirRecurso(new Madera(), 1);
        jugador.recibirRecurso(new Ladrillo(), 1);
        jugador.recibirRecurso(new Lana(), 1);
        jugador.recibirRecurso(new Grano(), 1);

        List<Recurso> precio = List.of(
                new Madera(1),
                new Ladrillo(1),
                new Lana(1),
                new Grano(1)
        );

        jugador.pagarRecursos(precio);

        assertEquals(0, jugador.buscarRecurso(new Madera()).cantidad());
        assertEquals(0, jugador.buscarRecurso(new Ladrillo()).cantidad());
        assertEquals(0, jugador.buscarRecurso(new Lana()).cantidad());
        assertEquals(0, jugador.buscarRecurso(new Grano()).cantidad());
    }

    
    @Test
    public void jugadorRobaUnaCartaAleatoriaDeVictima() {
        Jugador ladron = new Jugador("Ladrón");
        Jugador victima = new Jugador("Víctima");
        
        victima.recibirRecurso(new Ladrillo(), 1);

        int antesVictima = victima.cantidadDeCartas();   
        int antesLadron = ladron.cantidadDeCartas();     

        ladron.robarCartaAleatoriaA(victima);

        assertEquals(antesVictima - 1, victima.cantidadDeCartas());
        assertEquals(antesLadron + 1, ladron.cantidadDeCartas());
    }

    @Test
    public void jugadorRecibeUnRecursoPorPobladoCuandoCorresponde() {
        Tablero tablero = Tablero.getInstance();
        Grafo grafo = new Grafo();

        GeneradorDeTablero generador = new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        generador.generarEstructura(grafo);

        UbicacionVertice ubicacion1 = new UbicacionVertice('A');
        Vertice vertice1 = new VerticeTerreno(ubicacion1, new Montania(), 8);
        grafo.agregarVertice(vertice1);

        UbicacionVertice ubicacion10 = new UbicacionVertice(10);
        grafo.agregarArista(ubicacion10,ubicacion1);

        tablero.setearGrafo(grafo);

        GeneradorDeDados dado = () -> 8;
        
        Jugador jugador1 = new Jugador("Juli");
	    Jugador jugador2 = new Jugador("Valen");
	    Jugador jugador3 = new Jugador("Sofi");
	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
	    
	    Juego juego = new Juego(jugadores, dado);

        Jugador jugador = juego.jugadores().get(0);


        jugador.colocarPiezaInicial("poblado", List.of(ubicacion10));

        int tirada = juego.tirarDado();
        juego.manejarTirada(tirada);

        Recurso mineral = jugador.buscarRecurso(new Mineral());
        assertEquals(1, mineral.cantidad());
    }

    @Test
    public void jugadorRecibeDosRecursosPorCiudadCuandoCorresponde() {
        Tablero tablero = Tablero.getInstance();
        Grafo grafo = new Grafo();

        GeneradorDeTablero generador = new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        generador.generarEstructura(grafo);
        UbicacionVertice ubicacion1 = new UbicacionVertice('A');
        Vertice vertice1 = new VerticeTerreno(ubicacion1, new Montania(), 8);
        grafo.agregarVertice(vertice1);
        UbicacionVertice ubicacion10 = new UbicacionVertice(10);
        grafo.agregarArista(ubicacion10, ubicacion1);

        tablero.setearGrafo(grafo);

        GeneradorDeDados dado = () -> 8;

        Jugador jugador1 = new Jugador("Juli");
	    Jugador jugador2 = new Jugador("Valen");
	    Jugador jugador3 = new Jugador("Sofi");
	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
	    
	    Juego juego = new Juego(jugadores, dado);

        Jugador jugador = juego.jugadores().get(0);

        jugador.colocarPiezaInicial("poblado", List.of(ubicacion10));
        jugador.recibirRecurso(new Grano(),2);
        jugador.recibirRecurso(new Mineral(), 3);
        jugador.construirPieza("ciudad", List.of(ubicacion10));

        int tirada = juego.tirarDado();
        juego.manejarTirada(tirada);

        Recurso mineral = jugador.buscarRecurso(new Mineral());
        assertEquals(2, mineral.cantidad());
    }

    @Test
    public void jugadorDescartaLaMitadDeCartasSiSale7yTieneMasDe7Cartas() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        GeneradorDeDados dado = () -> 7;

        Jugador jugador1 = new JugadorQueNoMueveLadron("Juli");
        Jugador jugador2 = new Jugador("Valen");
        Jugador jugador3 = new Jugador("Sofi");
        List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);

        Juego juego = new Juego(jugadores, dado);
        Jugador jugador = juego.jugadores().get(0); 

        jugador.recibirRecurso(new Madera(), 5);
        jugador.recibirRecurso(new Ladrillo(), 4);

        assertEquals(9, jugador.cantidadDeCartas(), "Precondición: debe tener 9 cartas");

        int tirada = juego.tirarDado();
        juego.manejarTirada(tirada);

        assertEquals(5, jugador.cantidadDeCartas(),
                "Después de tirar 7, descarta la mitad y queda con 5 cartas");
    }


    
    @Test
    public void jugadorActivoMueveAlLadronYRobaCartaAJugadorAdyacenteANuevoTerreno() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset(); 
        GeneradorDeDados dado = () -> 7;
        
        Jugador jugador1 = new Jugador("Juli");
	    Jugador jugador2 = new Jugador("Valen");
	    Jugador jugador3 = new Jugador("Sofi");
	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
	    
	    Juego juego = new Juego(jugadores, dado);


        Jugador jugadorActivo = juego.jugadores().get(0);
        jugadorActivo.recibirRecurso(new Madera(), 10);
        jugadorActivo.recibirRecurso(new Ladrillo(), 10);
        jugadorActivo.recibirRecurso(new Lana(), 10);
        jugadorActivo.recibirRecurso(new Grano(), 10);

        Jugador jugadorVictima = juego.jugadores().get(1);

        jugadorVictima.recibirRecurso(new Madera(), 1);
        jugadorVictima.recibirRecurso(new Ladrillo(), 1);
        jugadorVictima.recibirRecurso(new Lana(), 1);
        jugadorVictima.recibirRecurso(new Grano(), 1);

        jugadorVictima.recibirRecurso(new Madera(), 1);
        jugadorVictima.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(4)));

        int cartasAntesVictima = jugadorVictima.cantidadDeCartas();
        int cartasAntesActivo = jugadorActivo.cantidadDeCartas();

        jugadorActivo.moverLadron(new UbicacionVertice('B'), jugadorVictima);

        assertEquals(cartasAntesVictima - 1, jugadorVictima.cantidadDeCartas(),
                "La víctima debería tener una carta menos después del robo");
        assertEquals(cartasAntesActivo + 1, jugadorActivo.cantidadDeCartas(),
                "El jugador activo debería tener una carta más después del robo");
    }

    @Test
    public void SeIntentaPonerPrimerCaminoNoAlLadoDeUnPobladoYSeLanzaUnaExcepcion() {
        Jugador jugador1 = new Jugador("Luis");
        List<Ubicacion> ubicaciones = new ArrayList<>();

        ubicaciones.add(new UbicacionVertice(10));
        ubicaciones.add(new UbicacionVertice(9));

        jugador1.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(1)));

        assertThrows(ColocacionInvalida.class, () -> {
            jugador1.colocarPiezaInicial("camino", ubicaciones);
        });
    }

    @Test
    public void LuegoDeLasPrimerasRondasSeIntetaPonerUnPobladoEnUnaUbicacionSinCaminoAntecesorYSeLanzaExcepcion() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Jugador jugador = new Jugador("Jugador");
        jugador.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(1)));
        jugador.colocarPiezaInicial("camino", List.of(new UbicacionVertice(1), new UbicacionVertice(9)));

        jugador.recibirRecurso(new Madera(), 2);
        jugador.recibirRecurso(new Lana(), 1);
        jugador.recibirRecurso(new Grano(), 1);
        jugador.recibirRecurso(new Ladrillo(), 2);

        jugador.construirPieza("camino", List.of(new UbicacionVertice(9), new UbicacionVertice(10)));

        assertThrows(ColocacionInvalida.class, () -> {
            jugador.construirPieza("poblado", List.of(new UbicacionVertice(20)));
        });
    }
    
    
    public class JugadorQueNoMueveLadron extends Jugador {

        public JugadorQueNoMueveLadron(String nombre) {
            super(nombre);
        }

        @Override
        public void moverLadron(UbicacionVertice ubicacion, Jugador victima) {
         }
    }
}
