package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Ubicacion.Ubicacion;
import edu.fiuba.algo3.modelo.Ubicacion.UbicacionVertice;
import edu.fiuba.algo3.vistas.*;
import edu.fiuba.algo3.modelo.Intercambio.*;

import java.util.List;

public class ControladorTablero {
    private Tablero modelo;
    private VistaTablero vistaTablero;
    private List<Vertice> vertices;
    private List<Arista> aristas;



    public ControladorTablero(Tablero modelo, VistaTablero vista) {
        this.modelo = modelo;
        this.vistaTablero = vista;

        vertices = modelo.obtenerVertices();
        aristas = modelo.obtenerAristas();
    }

    public void colocarTerrenos() {
        int numeroHexadono = 0;
        for (char i = 'A'; i <= 'S'; i++) {
            VerticeTerreno terreno = (VerticeTerreno) modelo.getTerreno(new UbicacionVertice(i));
            colocarTerrenoCorrespondiente(terreno);
            colocarFichaCorrespondiente(terreno, numeroHexadono);
            numeroHexadono++;
        }
    }

    public void colocarFichaCorrespondiente(VerticeTerreno terreno, int numeroHexadono) {
        if (terreno.tieneFichaDeNumero(2)) {
            vistaTablero.ponerFicha2(numeroHexadono);
        } else if (terreno.tieneFichaDeNumero(3)) {
            vistaTablero.ponerFicha3(numeroHexadono);
        } else if (terreno.tieneFichaDeNumero(4)) {
            vistaTablero.ponerFicha4(numeroHexadono);
        } else if (terreno.tieneFichaDeNumero(5)) {
            vistaTablero.ponerFicha5(numeroHexadono);
        } else if (terreno.tieneFichaDeNumero(6)) {
            vistaTablero.ponerFicha6(numeroHexadono);
        } else if (terreno.tieneFichaDeNumero(8)) {
            vistaTablero.ponerFicha8(numeroHexadono);
        } else if (terreno.tieneFichaDeNumero(9)) {
            vistaTablero.ponerFicha9(numeroHexadono);
        } else if (terreno.tieneFichaDeNumero(10)) {
            vistaTablero.ponerFicha10(numeroHexadono);
        } else if (terreno.tieneFichaDeNumero(11)) {
            vistaTablero.ponerFicha11(numeroHexadono);
        } else if (terreno.tieneFichaDeNumero(12)) {
            vistaTablero.ponerFicha12(numeroHexadono);
        }
    }

    public void colocarTerrenoCorrespondiente(VerticeTerreno terreno) {
        if (terreno.tieneTerreno("Pastizal")) {
            vistaTablero.ponerPastizal();
        } else if (terreno.tieneTerreno("Bosque")) {
            vistaTablero.ponerBosque();
        } else if (terreno.tieneTerreno("Colina")) {
            vistaTablero.ponerColina();
        } else if (terreno.tieneTerreno("Desierto")) {
            vistaTablero.ponerDesierto();
        } else if (terreno.tieneTerreno("Campo")) {
            vistaTablero.ponerCampo();
        } else if (terreno.tieneTerreno("Montania")) {
            vistaTablero.ponerMotania();
        }

    }

    public void colocarVertices() {
        VerticeEdificio verticeObjeto;
        VistaVerticeEdificio vistaVertice;
        Ubicacion ubicacion;
        for (int i = 0; i < 54; i++) {
            verticeObjeto = (VerticeEdificio) vertices.get(i);
            ubicacion = verticeObjeto.obtenerUbicacion();
            vistaVertice = new VistaVerticeEdificio(verticeObjeto);
            vistaTablero.crearVertice(vistaVertice, ubicacion);
        }
    }

    public void colocarAristas() {
        Arista aristaObjeto;
        VistaArista vistaArista;
        Ubicacion ubicacion1;
        Ubicacion ubicacion2;
        for (int i = 0; i < 72; i++) {
            aristaObjeto = aristas.get(i);
            ubicacion1 = aristaObjeto.obtenerPrimeraUbicacion();
            ubicacion2 = aristaObjeto.obtenerSegundaUbicacion();
            vistaArista = new VistaArista(aristaObjeto);
            vistaTablero.crearArista(vistaArista, ubicacion1, ubicacion2);
        }
    }
    
    
    
    public void colocarPuertos() {
        List<Puerto> puertos = modelo.getPuertos();
        List<VistaVerticeEdificio> vistasVertices = vistaTablero.getVertices();
        double distanciaDesdeCosta = 35.0;

        double centroX = 0;
        double centroY = 0;
        for (VistaVerticeEdificio v : vistasVertices) {
            centroX += v.getTranslateX();
            centroY += v.getTranslateY();
        }
        centroX /= vistasVertices.size();
        centroY /= vistasVertices.size();
        
        for (Puerto puerto : puertos) {
            configurarYColocarPuerto(puerto, vistasVertices, centroX, centroY, distanciaDesdeCosta);
        }
    }

    private void configurarYColocarPuerto(Puerto puerto, List<VistaVerticeEdificio> vistasVertices, double centroX, double centroY, double distanciaDesdeCosta) {
        String nombreRecurso = "general";
        if (puerto instanceof PuertoEspecifico) {
            nombreRecurso = ((PuertoEspecifico) puerto).getOferta().getClass().getSimpleName();
        }

        VistaPuerto vistaPuerto = new VistaPuerto(nombreRecurso);
        
        int idx1 = puerto.getMuelle1().getUbicacion().getUbicacionInt() - 1;
        int idx2 = puerto.getMuelle2().getUbicacion().getUbicacionInt() - 1;
        VistaVerticeEdificio v1 = vistasVertices.get(idx1);
        VistaVerticeEdificio v2 = vistasVertices.get(idx2);

        double medioX = (v1.getTranslateX() + v2.getTranslateX()) / 2;
        double medioY = (v1.getTranslateY() + v2.getTranslateY()) / 2;
        double dx = v2.getTranslateX() - v1.getTranslateX();
        double dy = v2.getTranslateY() - v1.getTranslateY();
        double normalX = -dy;
        double normalY = dx;
        double vectorDesdeCentroX = medioX - centroX;
        double vectorDesdeCentroY = medioY - centroY;
        double productoPunto = (normalX * vectorDesdeCentroX) + (normalY * vectorDesdeCentroY);

        if (productoPunto < 0) {
            normalX = -normalX;
            normalY = -normalY;
        }

        double longitud = Math.sqrt(normalX * normalX + normalY * normalY);
        normalX /= longitud;
        normalY /= longitud;

        double finalX = medioX + (normalX * distanciaDesdeCosta);
        double finalY = medioY + (normalY * distanciaDesdeCosta);

        vistaPuerto.setTranslateX(finalX);
        vistaPuerto.setTranslateY(finalY);
        
        double angulo = Math.toDegrees(Math.atan2(normalY, normalX));
        vistaPuerto.setRotate(angulo + 90);
        
        vistaTablero.agregarPuerto(vistaPuerto);
    }

}
