package edu.fiuba.algo3.testUnitarios;


import edu.fiuba.algo3.Excepciones.RecursoIncorrecto;
import edu.fiuba.algo3.modelo.Intercambio.*;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import edu.fiuba.algo3.modelo.Jugador;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class BancoTest {

    @Test
    public void test01JugadorSinPuertosIntercambiaConTasaBancariaEstandar4a1() {
        Banco banco = Banco.getInstance();
        Jugador jugadorMock = mock(Jugador.class);
       
        when(jugadorMock.getPuertos()).thenReturn(new ArrayList<>());

        Recurso oferta = new Madera(0);
        Recurso pedido = new Ladrillo(0);

        banco.comercializar(jugadorMock, oferta, pedido, 1);

        verify(jugadorMock).descontarRecurso(any(Madera.class), eq(4));
        verify(jugadorMock).recibirRecurso(any(Ladrillo.class), eq(1));
    }

    @Test
    public void test02JugadorConPuertoGenericoIntercambiaConTasa3a1() {
        Banco banco = Banco.getInstance();
        Jugador jugadorMock = mock(Jugador.class);
        Puerto puerto3a1 = new PuertoGenerico(new UbicacionVertice(1), new UbicacionVertice(2));
        
        when(jugadorMock.getPuertos()).thenReturn(List.of(puerto3a1));

        banco.comercializar(jugadorMock, new Madera(0), new Ladrillo(0), 1);
        verify(jugadorMock).descontarRecurso(any(Madera.class), eq(3));
    }

    @Test
    public void test03JugadorConPuertoEspecificoMaderaObtieneTasa2a1() {
    	Banco banco = Banco.getInstance();
        Jugador jugadorMock = mock(Jugador.class);
        Puerto puertoMadera = new PuertoEspecifico(new Madera(), new UbicacionVertice(1), new UbicacionVertice(2));
        
        when(jugadorMock.getPuertos()).thenReturn(List.of(puertoMadera));
        banco.comercializar(jugadorMock, new Madera(0), new Ladrillo(0), 1);

        verify(jugadorMock).descontarRecurso(any(Madera.class), eq(2));
    }

    @Test
    public void test04JugadorConPuertoEspecificoOvejaNoObtieneBeneficioAlVenderMadera() {
        Banco banco = Banco.getInstance();
        Jugador jugadorMock = mock(Jugador.class);

        Puerto puertoLana = new PuertoEspecifico(new Lana(), new UbicacionVertice(1), new UbicacionVertice(2));
        
        when(jugadorMock.getPuertos()).thenReturn(List.of(puertoLana));
        
        banco.comercializar(jugadorMock, new Madera(0), new Ladrillo(0), 1);
        verify(jugadorMock).descontarRecurso(any(Madera.class), eq(4));
    }

    @Test
    public void test05JugadorConPuertoGenericoYPuertoEspecificoEligeLaMejorOpcion() {
        Banco banco = Banco.getInstance();
        Jugador jugadorMock = mock(Jugador.class);

        Puerto puertoGenerico = new PuertoGenerico(new UbicacionVertice(1), new UbicacionVertice(2));
        Puerto puertoMadera = new PuertoEspecifico(new Madera(), new UbicacionVertice(3), new UbicacionVertice(4));
        
        when(jugadorMock.getPuertos()).thenReturn(List.of(puertoGenerico, puertoMadera));
        banco.comercializar(jugadorMock, new Madera(0), new Ladrillo(0), 1);

        verify(jugadorMock).descontarRecurso(any(Madera.class), eq(2));
    }

    @Test
    public void test06JugadorConPuertoGenericoYEspecificoAjenoUsaElGenerico() {
        Banco banco = Banco.getInstance();
        Jugador jugadorMock = mock(Jugador.class);

        Puerto puertoGenerico = new PuertoGenerico(new UbicacionVertice(1), new UbicacionVertice(2));
        Puerto puertoLana = new PuertoEspecifico(new Lana(), new UbicacionVertice(3), new UbicacionVertice(4));
        when(jugadorMock.getPuertos()).thenReturn(List.of(puertoGenerico, puertoLana));

        banco.comercializar(jugadorMock, new Madera(0), new Ladrillo(0), 1);

        verify(jugadorMock).descontarRecurso(any(Madera.class), eq(3));
    }
    
    @Test
    public void test07IntercambioPorMayorCantidadEscalaCorrectamente() {
        Banco banco = Banco.getInstance();
        Jugador jugadorMock = mock(Jugador.class);
        Puerto puertoMadera = new PuertoEspecifico(new Madera(), new UbicacionVertice(1), new UbicacionVertice(2));
        
        when(jugadorMock.getPuertos()).thenReturn(List.of(puertoMadera));
        banco.comercializar(jugadorMock, new Madera(0), new Ladrillo(0), 5);

        // Assert
        verify(jugadorMock).descontarRecurso(any(Madera.class), eq(10));
        verify(jugadorMock).recibirRecurso(any(Ladrillo.class), eq(5));
    }
}
