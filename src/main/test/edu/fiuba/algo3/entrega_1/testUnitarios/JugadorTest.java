package edu.fiuba.algo3.entrega_1.testUnitarios;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Assertions;



public class JugadorTest {
	@Test
	public void jugadorInicializaCorrectamenteLosRecursos() {
	    Jugador jugador = new Jugador("Lu");

	    assertNotNull(jugador.buscarRecurso(RecursoTipo.MADERA));
	    assertNotNull(jugador.buscarRecurso(RecursoTipo.LADRILLO));
	    assertNotNull(jugador.buscarRecurso(RecursoTipo.LANA));
	    assertNotNull(jugador.buscarRecurso(RecursoTipo.GRANO));
	    assertNotNull(jugador.buscarRecurso(RecursoTipo.MINERAL));
	}

	@Test
	public void jugadorPuedeConstruirPobladoSiTieneRecursosSuficientes() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();
        GeneradorDeDados dadoPrueba = () -> 7;

        List<String> nombres = List.of("Luis", "Ana", "Maria");
        Juego juego = new Juego(3, nombres, dadoPrueba);
        Jugador jugador = juego.jugadores().get(0);
        Pieza pobladoDeReferencia = Pieza.crear("poblado", jugador);
        List<Recurso> precio = pobladoDeReferencia.costoDeConstruccion();

        for (Recurso costo : precio) {
            jugador.recibirRecurso(costo.tipo(), costo.cantidad());
        }
  
        List<Integer> ubicacion = List.of(10);
        jugador.construirPieza("poblado", ubicacion);

        Assertions.assertTrue(tablero.hayPieza(ubicacion));
        Assertions.assertEquals(0, jugador.cantidadDeCartas());
    }

	

    @Test
    public void jugadorRecibeRecursoDelTerreno() {
    	Tablero tablero = Tablero.getInstance();
        tablero.reset();
      
        Jugador jugador = new Jugador("Luis");
        Terreno montania = new Montania();
        VerticeTerreno vt = new VerticeTerreno('A', montania, 8);
        Poblado p = new Poblado(jugador);
        
        vt.agregarEdificio(p);
        vt.cosecharTerreno();
        
        Recurso mineral = jugador.buscarRecurso(RecursoTipo.MINERAL);
        assertEquals(1, mineral.cantidad());
    }

    @Test
    public void jugadorPagaRecursosCorrectamente() {
        Jugador jugador = new Jugador("Luis");

        jugador.recibirRecurso(RecursoTipo.MADERA, 1);
        jugador.recibirRecurso(RecursoTipo.LADRILLO, 1);
        jugador.recibirRecurso(RecursoTipo.LANA, 1);
        jugador.recibirRecurso(RecursoTipo.GRANO, 1);

        List<Recurso> precio = List.of(
                new Recurso(RecursoTipo.MADERA, 1),
                new Recurso(RecursoTipo.LADRILLO, 1),
                new Recurso(RecursoTipo.LANA, 1),
                new Recurso(RecursoTipo.GRANO, 1)
        );

        jugador.pagarRecursos(precio);

        assertEquals(0, jugador.buscarRecurso(RecursoTipo.MADERA).cantidad());
        assertEquals(0, jugador.buscarRecurso(RecursoTipo.LADRILLO).cantidad());
        assertEquals(0, jugador.buscarRecurso(RecursoTipo.LANA).cantidad());
        assertEquals(0, jugador.buscarRecurso(RecursoTipo.GRANO).cantidad());
    }

    
    @Test
    public void jugadorRobaUnaCartaAleatoriaDeVictima() {
        Jugador ladron = new Jugador("Ladrón");
        Jugador victima = new Jugador("Víctima");
        
        victima.recibirRecurso(RecursoTipo.LADRILLO, 1);

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
        grafo.agregarVertice('A', new Montania(), 8);
        grafo.agregarArista(10,'A');

        tablero.setearGrafo(grafo);

        GeneradorDeDados dado = () -> 8;

        Juego juego = new Juego(3, List.of("Luis", "Ana", "Marcos"), dado);
        Jugador jugador = juego.jugadores().get(0);


        jugador.colocarPiezaInicial("poblado", List.of(10));

        int tirada = juego.tirarDado();
        juego.manejarTirada(tirada);

        Recurso mineral = jugador.buscarRecurso(RecursoTipo.MINERAL);
        assertEquals(1, mineral.cantidad());
    }

    @Test
    public void jugadorRecibeDosRecursosPorCiudadCuandoCorresponde() {
        Tablero tablero = Tablero.getInstance();
        Grafo grafo = new Grafo();

        GeneradorDeTablero generador = new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        generador.generarEstructura(grafo);
        grafo.agregarVertice('A', new Montania(), 8);
        grafo.agregarArista(10,'A');

        tablero.setearGrafo(grafo);

        GeneradorDeDados dado = () -> 8;

        Juego juego = new Juego(3, List.of("Luis", "Ana", "Marcos"), dado);
        Jugador jugador = juego.jugadores().get(0);

        jugador.colocarPiezaInicial("poblado", List.of(10));
        jugador.recibirRecurso(RecursoTipo.GRANO,2);
        jugador.recibirRecurso(RecursoTipo.MINERAL, 3);
        jugador.construirPieza("ciudad", List.of(10));

        int tirada = juego.tirarDado();
        juego.manejarTirada(tirada);

        Recurso mineral = jugador.buscarRecurso(RecursoTipo.MINERAL);
        assertEquals(2, mineral.cantidad());
    }

    @Test
    public void jugadorDescartaLaMitadDeCartasSiSale7yTieneMasDe7Cartas() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        GeneradorDeDados dado = () -> 7;

        Juego juego = new Juego(3, List.of("lu", "gia", "da"), dado);
        Jugador jugador = juego.jugadores().get(0);

        jugador.recibirRecurso(RecursoTipo.MADERA, 5);
        jugador.recibirRecurso(RecursoTipo.LADRILLO, 4);

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
        GeneradorDeDados generador = () -> 7;
        Juego juego = new Juego(3, List.of("Luis", "Ana", "Marcos"), generador);

        Jugador jugadorActivo = juego.jugadores().get(0);
        jugadorActivo.recibirRecurso(RecursoTipo.MADERA, 10);
        jugadorActivo.recibirRecurso(RecursoTipo.LADRILLO, 10);
        jugadorActivo.recibirRecurso(RecursoTipo.LANA, 10);
        jugadorActivo.recibirRecurso(RecursoTipo.GRANO, 10);

        Jugador jugadorVictima = juego.jugadores().get(1);

        jugadorVictima.recibirRecurso(RecursoTipo.MADERA, 1);
        jugadorVictima.recibirRecurso(RecursoTipo.LADRILLO, 1);
        jugadorVictima.recibirRecurso(RecursoTipo.LANA, 1);
        jugadorVictima.recibirRecurso(RecursoTipo.GRANO, 1);

        jugadorVictima.recibirRecurso(RecursoTipo.MADERA, 1);
        jugadorVictima.construirPieza("poblado", List.of(4));

        int cartasAntesVictima = jugadorVictima.cantidadDeCartas();
        int cartasAntesActivo = jugadorActivo.cantidadDeCartas();

        jugadorActivo.moverLadron('B');

        assertEquals(cartasAntesVictima - 1, jugadorVictima.cantidadDeCartas(),
                "La víctima debería tener una carta menos después del robo");
        assertEquals(cartasAntesActivo + 1, jugadorActivo.cantidadDeCartas(),
                "El jugador activo debería tener una carta más después del robo");
    }
}
