package edu.fiuba.algo3.entrega_1.TestIntegrales;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Dados.*;
import edu.fiuba.algo3.modelo.Pieza.*;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Terreno.*;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import edu.fiuba.algo3.testUnitarios.JugadorTest.JugadorQueNoMueveLadron;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class TestIntegrales {

    @Test
    public void colocacionCorrectaDeHexagonosAleatorios(){
        GeneradorNumerosAleatorios generadorMock = mock(GeneradorNumerosAleatorios.class);
        Grafo grafo = new Grafo();
        GeneradorDeTablero genTablero = new GeneradorDeTablero(generadorMock);

        when(generadorMock.generarEnRangoDesdeCero(anyInt())).thenReturn(11,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0);
        genTablero.generarEstructura(grafo);
        genTablero.generarTerrenos(grafo, new Ladron());

        VerticeTerreno v1 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('A'));
        VerticeTerreno v2 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('B'));
        VerticeTerreno v3 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('C'));
        VerticeTerreno v4 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('D'));
        VerticeTerreno v5 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('E'));
        VerticeTerreno v6 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('F'));
        VerticeTerreno v7 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('G'));
        VerticeTerreno v8 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('H'));
        VerticeTerreno v9 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('I'));
        VerticeTerreno v10 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('J'));
        VerticeTerreno v11 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('K'));
        VerticeTerreno v12 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('L'));
        VerticeTerreno v13 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('M'));
        VerticeTerreno v14 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('N'));
        VerticeTerreno v15 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('O'));
        VerticeTerreno v16 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('P'));
        VerticeTerreno v17 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('Q'));
        VerticeTerreno v18 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('R'));
        VerticeTerreno v19 = (VerticeTerreno)grafo.buscarVertice(new UbicacionVertice('S'));

        assertTrue(v1.tieneTerreno("Montania"));
        assertTrue(v1.tieneFichaDeNumero(2));

        assertTrue(v2.tieneTerreno("Colina"));
        assertTrue(v2.tieneFichaDeNumero(6));

        assertTrue(v3.tieneTerreno("Colina"));
        assertTrue(v3.tieneFichaDeNumero(3));

        assertTrue(v4.tieneTerreno("Bosque"));
        assertTrue(v4.tieneFichaDeNumero(8));

        assertTrue(v5.tieneTerreno("Bosque"));
        assertTrue(v5.tieneFichaDeNumero(10));

        assertTrue(v6.tieneTerreno("Bosque"));
        assertTrue(v6.tieneFichaDeNumero(9));

        assertTrue(v7.tieneTerreno("Bosque"));
        assertTrue(v7.tieneFichaDeNumero(12));

        assertTrue(v8.tieneTerreno("Campo"));
        assertTrue(v8.tieneFichaDeNumero(11));

        assertTrue(v9.tieneTerreno("Colina"));
        assertTrue(v9.tieneFichaDeNumero(4));

        assertTrue(v10.tieneTerreno("Campo"));
        assertTrue(v10.tieneFichaDeNumero(8));

        assertTrue(v11.tieneTerreno("Desierto"));
        assertTrue(v11.tieneFichaDeNumero(0));

        assertTrue(v12.tieneTerreno("Campo"));
        assertTrue(v12.tieneFichaDeNumero(10));

        assertTrue(v13.tieneTerreno("Montania"));
        assertTrue(v13.tieneFichaDeNumero(9));

        assertTrue(v14.tieneTerreno("Montania"));
        assertTrue(v14.tieneFichaDeNumero(4));

        assertTrue(v15.tieneTerreno("Pastizal"));
        assertTrue(v15.tieneFichaDeNumero(5));

        assertTrue(v16.tieneTerreno("Pastizal"));
        assertTrue(v16.tieneFichaDeNumero(6));

        assertTrue(v17.tieneTerreno("Pastizal"));
        assertTrue(v17.tieneFichaDeNumero(3));

        assertTrue(v18.tieneTerreno("Pastizal"));
        assertTrue(v18.tieneFichaDeNumero(11));

        assertTrue(v19.tieneTerreno("Campo"));
        assertTrue(v19.tieneFichaDeNumero(5));
    }

    @Test
    public void validacionReglaDeLaDistanciaAlcolocarPobladosIniciales() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();

        Jugador luis = new Jugador("Luis");
        Jugador federico = new Jugador("Federico");
        Jugador lucas = new Jugador("Lucas");
        Jugador ricardo = new Jugador("Ricardo");
        Jugador fabiano = new Jugador("Fabiano");

        luis.colocarPiezaInicial("poblado", new ArrayList<>(List.of(new UbicacionVertice(1))));
        luis.colocarPiezaInicial("camino", new ArrayList<>(List.of(new UbicacionVertice(1), new UbicacionVertice(2))));

        lucas.colocarPiezaInicial("poblado", new ArrayList<>(List.of(new UbicacionVertice(5))));
        lucas.colocarPiezaInicial("camino", new ArrayList<>(List.of(new UbicacionVertice(5),new UbicacionVertice(6))));

       assertTrue(tablero.hayEdificio(new UbicacionVertice(1)));
        assertTrue(tablero.hayEdificio(new UbicacionVertice(5)));

        assertThrows(IllegalArgumentException.class, () -> {
            federico.colocarPiezaInicial("poblado", new ArrayList<>(List.of(new UbicacionVertice(6))));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ricardo.colocarPiezaInicial("poblado", new ArrayList<>(List.of(new UbicacionVertice(2))));;
        });
        assertThrows(IllegalArgumentException.class, () -> {
            fabiano.colocarPiezaInicial("poblado", new ArrayList<>(List.of(new UbicacionVertice(1))));;
        });
    }

    @Test
    public void jugadoresRecibenRecursosPorSusSegundosPoblados() {
        Tablero tablero = Tablero.getInstance();
        Grafo grafo = new Grafo();

        GeneradorDeTablero generador = new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        generador.generarEstructura(grafo);

        UbicacionVertice ubicacion1 = new UbicacionVertice('A');
        Vertice vertice1 = new VerticeTerreno(ubicacion1, new Montania(), 8);
        grafo.agregarVertice(vertice1);

        UbicacionVertice ubicacion2 = new UbicacionVertice('B');
        Vertice vertice2 = new VerticeTerreno(ubicacion2, new Montania(), 8);
        grafo.agregarVertice(vertice2);

        UbicacionVertice ubicacion10 = new UbicacionVertice(10);
        UbicacionVertice ubicacion40 = new UbicacionVertice(40);
        grafo.agregarArista(ubicacion10, ubicacion1);
        grafo.agregarArista(ubicacion40,ubicacion2);

        tablero.setearGrafo(grafo);

        GeneradorDeDados dado = () -> 8;

        Jugador jugador1 = new Jugador("Juli");
	    Jugador jugador2 = new Jugador("Valen");
	    Jugador jugador3 = new Jugador("Sofi");
	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);

	    Juego juego = new Juego(jugadores, dado);

        for (Jugador j : juego.jugadores()) {
            j.recibirRecurso(RecursoTipo.MADERA,   10);
            j.recibirRecurso(RecursoTipo.LADRILLO, 10);
            j.recibirRecurso(RecursoTipo.LANA,     10);
            j.recibirRecurso(RecursoTipo.GRANO,    10);
        }

        jugador1.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(1)));
        jugador1.colocarPiezaInicial("camino",  List.of(new UbicacionVertice(1), new UbicacionVertice(2)));
        jugador1.colocarPiezaInicial("poblado", List.of(ubicacion10));

        jugador2.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(54)));
        jugador2.colocarPiezaInicial("camino",  List.of(new UbicacionVertice(54), new UbicacionVertice(53)));
        jugador2.colocarPiezaInicial("poblado", List.of(ubicacion40));


        jugador3.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(6)));
        jugador3.colocarPiezaInicial("camino",  List.of(new UbicacionVertice(6), new UbicacionVertice(7)));
        jugador3.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(36)));

        int tirada = juego.tirarDado();
        juego.manejarTirada(tirada);

        Recurso mineralJ1 = jugador1.buscarRecurso(RecursoTipo.MINERAL);
        Recurso mineralJ2 = jugador2.buscarRecurso(RecursoTipo.MINERAL);

        assertEquals(1, mineralJ1.cantidad());
        assertEquals(1, mineralJ2.cantidad());


        Recurso mineralJ3 = jugador3.buscarRecurso(RecursoTipo.MINERAL);
        assertTrue(mineralJ3.cantidad() == 0);
    }

    @Test
    void cuandoDa12_esValidoYDevuelve12() {
    	Jugador jugador1 = new Jugador("Juli");
 	    Jugador jugador2 = new Jugador("Valen");
 	    Jugador jugador3 = new Jugador("Sofi");
 	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);

 	    GeneradorDeDados dado = () -> 12;
 	    Juego juego = new Juego(jugadores, dado);

        int n = juego.tirarDado();
        assertEquals(12, n);
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

        jugador1.colocarPiezaInicial("poblado", List.of(ubicacion10));

        int tirada = juego.tirarDado();
        juego.manejarTirada(tirada);

        Recurso mineral = jugador1.buscarRecurso(RecursoTipo.MINERAL);
        assertEquals(1, mineral.cantidad());
    }

    @Test
    public void jugadorRecibeDosRecursosPorCiudadCuandoCorresponde() {
        Tablero tablero = Tablero.getInstance();
        Grafo grafo = new Grafo();

        GeneradorDeTablero generador = new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        generador.generarEstructura(grafo);

        UbicacionVertice ubicacion1 = new UbicacionVertice('A');
        Vertice vertice1 = new VerticeTerreno(ubicacion1, new Bosque(), 8);
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

        jugador2.colocarPiezaInicial("poblado", List.of(ubicacion10));
        jugador2.recibirRecurso(RecursoTipo.GRANO, 2);
        jugador2.recibirRecurso(RecursoTipo.MINERAL, 3);
        jugador2.construirPieza("ciudad", List.of(ubicacion10));

        int tirada = juego.tirarDado();
        juego.manejarTirada(tirada);

        Recurso madera = jugador2.buscarRecurso(RecursoTipo.MADERA);
        assertEquals(2, madera.cantidad());
    }

    @Test
    public void terrenoConLadronNoProduceRecursos() {
        Tablero tablero = Tablero.getInstance();
        Grafo grafo = new Grafo();

        GeneradorDeTablero generador = new GeneradorDeTablero(new GeneradorNumerosAleatorios());
        generador.generarEstructura(grafo);
        UbicacionVertice ubicacionX = new UbicacionVertice('X');
        VerticeTerreno verticeBosque = new VerticeTerreno(ubicacionX, new Bosque(), 8);
        grafo.agregarVertice(verticeBosque);

        UbicacionVertice ubicacion1 = new UbicacionVertice(1);
        grafo.agregarArista(ubicacion1, ubicacionX);

        tablero.setearGrafo(grafo);

        Jugador jugador = new Jugador("Jugador");
        Pieza poblado = new Poblado(jugador);

        tablero.colocarEdificio(ubicacion1, poblado);
        Ladron ladron = new Ladron();
        verticeBosque.colocarLadron(ladron);

        int antes = jugador.cantidadDeCartas();
        tablero.cosechar(8); 
        int despues = jugador.cantidadDeCartas();

        assertEquals(antes, despues,
                "Un terreno con el Ladrón NO debe producir recursos");
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
        GeneradorDeDados dado = () -> 7;
        Jugador jugador1 = new Jugador("Juli");
 	    Jugador jugador2 = new Jugador("Valen");
 	    Jugador jugador3 = new Jugador("Sofi");

 	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
        Jugador jugadorActivo = jugador1;
        jugadorActivo.recibirRecurso(RecursoTipo.MADERA, 10);
        jugadorActivo.recibirRecurso(RecursoTipo.LADRILLO, 10);
        jugadorActivo.recibirRecurso(RecursoTipo.LANA, 10);
        jugadorActivo.recibirRecurso(RecursoTipo.GRANO, 10);

        Jugador jugadorVictima = jugador2;
        jugadorVictima.recibirRecurso(RecursoTipo.MADERA, 1);
        jugadorVictima.recibirRecurso(RecursoTipo.LADRILLO, 1);
        jugadorVictima.recibirRecurso(RecursoTipo.LANA, 1);
        jugadorVictima.recibirRecurso(RecursoTipo.GRANO, 1);

        jugadorVictima.recibirRecurso(RecursoTipo.MADERA, 1);
        jugadorVictima.colocarPiezaInicial("poblado", List.of(new UbicacionVertice(4)));

        int cartasAntesVictima = jugadorVictima.cantidadDeCartas();
        int cartasAntesActivo = jugadorActivo.cantidadDeCartas();

        jugadorActivo.moverLadron(new UbicacionVertice('B'), jugadorVictima);

        assertEquals(cartasAntesVictima - 1, jugadorVictima.cantidadDeCartas(),
                "La víctima debería tener una carta menos después del robo");
        assertEquals(cartasAntesActivo + 1, jugadorActivo.cantidadDeCartas(),
                "El jugador activo debería tener una carta más después del robo");
    }

    class JugadorQueNoMueveLadron extends Jugador {

        public JugadorQueNoMueveLadron(String nombre) {
            super(nombre);
        }

        @Override
        public void moverLadron(UbicacionVertice ubicacion, Jugador victima) {
         }
    }



}
