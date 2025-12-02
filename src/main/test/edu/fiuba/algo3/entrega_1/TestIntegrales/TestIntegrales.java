package edu.fiuba.algo3.entrega_1.TestIntegrales;

import edu.fiuba.algo3.Excepciones.ColocacionInvalida;
import edu.fiuba.algo3.modelo.Dados.Dados;
import edu.fiuba.algo3.modelo.Dados.*;
import edu.fiuba.algo3.modelo.GeneradorNumerosAleatorios;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Pieza.Ladron;
import edu.fiuba.algo3.modelo.Pieza.Pieza;
import edu.fiuba.algo3.modelo.Pieza.Poblado;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Terreno.Bosque;
import edu.fiuba.algo3.modelo.Terreno.Montania;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
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

        VerticeTerreno vertice1 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('A'));
        VerticeTerreno vertice2 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('B'));
        VerticeTerreno vertice3 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('C'));
        VerticeTerreno vertice4 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('D'));
        VerticeTerreno vertice5 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('E'));
        VerticeTerreno vertice6 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('F'));
        VerticeTerreno vertice7 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('G'));
        VerticeTerreno vertice8 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('H'));
        VerticeTerreno vertice9 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('I'));
        VerticeTerreno vertice10 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('J'));
        VerticeTerreno vertice11 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('K'));
        VerticeTerreno vertice12 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('L'));
        VerticeTerreno vertice13 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('M'));
        VerticeTerreno vertice14 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('N'));
        VerticeTerreno vertice15 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('O'));
        VerticeTerreno vertice16 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('P'));
        VerticeTerreno vertice17 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('Q'));
        VerticeTerreno vertice18 = (VerticeTerreno) grafo.buscarVertice(new UbicacionVertice('R'));
        VerticeTerreno vertice19 = (VerticeTerreno)grafo.buscarVertice(new UbicacionVertice('S'));

        assertTrue(vertice1.tieneTerreno("Montania"));
        assertTrue(vertice1.tieneFichaDeNumero(2));

        assertTrue(vertice2.tieneTerreno("Colina"));
        assertTrue(vertice2.tieneFichaDeNumero(6));

        assertTrue(vertice3.tieneTerreno("Colina"));
        assertTrue(vertice3.tieneFichaDeNumero(3));

        assertTrue(vertice4.tieneTerreno("Bosque"));
        assertTrue(vertice4.tieneFichaDeNumero(8));

        assertTrue(vertice5.tieneTerreno("Bosque"));
        assertTrue(vertice5.tieneFichaDeNumero(10));

        assertTrue(vertice6.tieneTerreno("Bosque"));
        assertTrue(vertice6.tieneFichaDeNumero(9));

        assertTrue(vertice7.tieneTerreno("Bosque"));
        assertTrue(vertice7.tieneFichaDeNumero(12));

        assertTrue(vertice8.tieneTerreno("Campo"));
        assertTrue(vertice8.tieneFichaDeNumero(11));

        assertTrue(vertice9.tieneTerreno("Colina"));
        assertTrue(vertice9.tieneFichaDeNumero(4));

        assertTrue(vertice10.tieneTerreno("Campo"));
        assertTrue(vertice10.tieneFichaDeNumero(8));

        assertTrue(vertice11.tieneTerreno("Desierto"));
        assertTrue(vertice11.tieneFichaDeNumero(0));

        assertTrue(vertice12.tieneTerreno("Campo"));
        assertTrue(vertice12.tieneFichaDeNumero(10));

        assertTrue(vertice13.tieneTerreno("Montania"));
        assertTrue(vertice13.tieneFichaDeNumero(9));

        assertTrue(vertice14.tieneTerreno("Montania"));
        assertTrue(vertice14.tieneFichaDeNumero(4));

        assertTrue(vertice15.tieneTerreno("Pastizal"));
        assertTrue(vertice15.tieneFichaDeNumero(5));

        assertTrue(vertice16.tieneTerreno("Pastizal"));
        assertTrue(vertice16.tieneFichaDeNumero(6));

        assertTrue(vertice17.tieneTerreno("Pastizal"));
        assertTrue(vertice17.tieneFichaDeNumero(3));

        assertTrue(vertice18.tieneTerreno("Pastizal"));
        assertTrue(vertice18.tieneFichaDeNumero(11));

        assertTrue(vertice19.tieneTerreno("Campo"));
        assertTrue(vertice19.tieneFichaDeNumero(5));
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

        assertThrows(ColocacionInvalida.class, () -> {
            federico.colocarPiezaInicial("poblado", new ArrayList<>(List.of(new UbicacionVertice(6))));
        });
        assertThrows(ColocacionInvalida.class, () -> {
            ricardo.colocarPiezaInicial("poblado", new ArrayList<>(List.of(new UbicacionVertice(2))));;
        });
        assertThrows(ColocacionInvalida.class, () -> {
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

        Jugador jugador1 = new Jugador("Juli");
	    Jugador jugador2 = new Jugador("Valen");
	    Jugador jugador3 = new Jugador("Sofi");
	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);

	    Juego juego = new Juego(jugadores);

        for (Jugador j : juego.jugadores()) {
            j.recibirRecurso(new Madera(),   10);
            j.recibirRecurso(new Ladrillo(), 10);
            j.recibirRecurso(new Ladrillo(),     10);
            j.recibirRecurso(new Grano(),    10);
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

        Dados dados =  new Dados(2);
        int tirada = dados.tiradaFalsa(8);
        juego.manejarTirada(tirada);

        Recurso mineralJ1 = jugador1.buscarRecurso(new Mineral());
        Recurso mineralJ2 = jugador2.buscarRecurso(new Mineral());

        assertEquals(1, mineralJ1.cantidad());
        assertEquals(1, mineralJ2.cantidad());


        Recurso mineralJ3 = jugador3.buscarRecurso(new Mineral());
        assertTrue(mineralJ3.cantidad() == 0);
    }

    @Test
    void cuandoDa12_esValidoYDevuelve12() {
    	Jugador jugador1 = new Jugador("Juli");
 	    Jugador jugador2 = new Jugador("Valen");
 	    Jugador jugador3 = new Jugador("Sofi");
 	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);

 	   Dados dados =  new Dados(2);
       int tirada = dados.tiradaFalsa(12);
 	   Juego juego = new Juego(jugadores);

        assertEquals(12, tirada);
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


        Jugador jugador1 = new Jugador("Juli");
 	    Jugador jugador2 = new Jugador("Valen");
 	    Jugador jugador3 = new Jugador("Sofi");
 	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
 	    Juego juego = new Juego(jugadores);

        jugador1.colocarPiezaInicial("poblado", List.of(ubicacion10));

        Dados dados =  new Dados(2);
        int tirada = dados.tiradaFalsa(8);
        juego.manejarTirada(tirada);

        Recurso mineral = jugador1.buscarRecurso(new Mineral());
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


        Jugador jugador1 = new Jugador("Juli");
 	    Jugador jugador2 = new Jugador("Valen");
 	    Jugador jugador3 = new Jugador("Sofi");
 	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
 	    Juego juego = new Juego(jugadores);

        jugador2.colocarPiezaInicial("poblado", List.of(ubicacion10));
        jugador2.recibirRecurso(new Grano(), 2);
        jugador2.recibirRecurso(new Mineral(), 3);
        jugador2.construirPieza("ciudad", List.of(ubicacion10));

        Dados dados =  new Dados(2);
        int tirada = dados.tiradaFalsa(8);
        juego.manejarTirada(tirada);

        Recurso madera = jugador2.buscarRecurso(new Madera());
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

        Jugador jugador1 = new JugadorQueNoMueveLadron("Juli");
        Jugador jugador2 = new Jugador("Valen");
        Jugador jugador3 = new Jugador("Sofi");
        List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);

        Juego juego = new Juego(jugadores);
        Jugador jugador = juego.jugadores().get(0); 

        jugador.recibirRecurso(new Madera(), 5);
        jugador.recibirRecurso(new Ladrillo(), 4);

        assertEquals(9, jugador.cantidadDeCartas(), "Precondición: debe tener 9 cartas");

        Dados dados =  new Dados(2);
        int tirada = dados.tiradaFalsa(7);
        juego.manejarTirada(tirada);

        assertEquals(5, jugador.cantidadDeCartas(),
                "Después de tirar 7, descarta la mitad y queda con 5 cartas");
    }

    @Test
    public void jugadorActivoMueveAlLadronYRobaCartaAJugadorAdyacenteANuevoTerreno() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();
        Jugador jugador1 = new Jugador("Juli");
 	    Jugador jugador2 = new Jugador("Valen");
 	    Jugador jugador3 = new Jugador("Sofi");

 	    List<Jugador> jugadores = List.of(jugador1, jugador2, jugador3);
        Jugador jugadorActivo = jugador1;
        jugadorActivo.recibirRecurso(new Madera(), 10);
        jugadorActivo.recibirRecurso(new Ladrillo(), 10);
        jugadorActivo.recibirRecurso(new Lana(), 10);
        jugadorActivo.recibirRecurso(new Grano(), 10);

        Jugador jugadorVictima = jugador2;
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

    class JugadorQueNoMueveLadron extends Jugador {

        public JugadorQueNoMueveLadron(String nombre) {
            super(nombre);
        }

        @Override
        public void moverLadron(UbicacionVertice ubicacion, Jugador victima) {
         }
    }



}
