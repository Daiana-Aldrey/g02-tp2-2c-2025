package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TableroTest {
	@Test
    public void tableroColocaEdificioCorrectamente() {
        Tablero tablero = Tablero.getInstance();
        tablero.crearGrafo(); 
        
        Pieza pieza = new Poblado(new Jugador("Luis"));
        int vertice = 10;
        tablero.colocarEdificio(vertice, pieza);
        
        List<Integer> ubicacion = new ArrayList<>();
        ubicacion.add(vertice);
        boolean valorEsperado = true;
        boolean valorObtenido = tablero.hayPieza(ubicacion);
        
        Assertions.assertEquals(valorEsperado, valorObtenido);
    }

    @Test
    public void tableroColocaCaminoCorrectamente() {
        Tablero tablero = Tablero.getInstance();
        tablero.crearGrafo();

        Camino camino = new Camino(new Jugador("Luis"));

        List<Integer> ubicacion = List.of(10, 11);
        tablero.colocarCamino(ubicacion, camino);
        boolean valorEsperado = true;
        boolean valorObtenido = tablero.hayPieza(ubicacion);
        Assertions.assertEquals(valorEsperado,valorObtenido);
    }

    @Test
    public void tableroNoPermiteColocarDosPiezasEnElMismoVertice() throws Exception {
        Tablero tablero = Tablero.getInstance();
        tablero.crearGrafo();

        Pieza p1 = new Poblado(new Jugador("Luis"));
        tablero.colocarEdificio(5, p1);
        Pieza p2 = new Poblado(new Jugador("Juan"));

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            tablero.colocarEdificio(5, p2);
        });
    }
    
    public void colocacionCorrectaDeHexagonosAleatorios(){
    	
    }
    
    public void colocacionCorrectaDePobladosIniciales() {
    	
    }
    
    @Test
    public void terrenoConLadronNoProduceRecursos() {
        Tablero tablero = Tablero.getInstance();
        tablero.reset();
        tablero.crearGrafo();

        Terreno bosque = new Bosque(8);
        tablero.registrarTerreno('X', bosque, List.of(1,2,3,4,5,6));
        VerticeTerreno vt = tablero.buscarVerticeTerreno('X');

        Jugador jugador = new Jugador("Jugador");
        Pieza poblado = new Poblado(jugador);
        vt.agregarEdificio(poblado);

        tablero.moverLadronA('X', jugador);

        int antes = jugador.cantidadDeCartas();
        tablero.cosechar(6);
        int despues = jugador.cantidadDeCartas();

        assertEquals(antes, despues,
                "Un terreno con el Ladrón NO debe producir recursos");
    }

}
