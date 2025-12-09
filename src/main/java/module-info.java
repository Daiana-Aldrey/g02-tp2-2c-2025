module edu.fiuba.algo3 {
    requires javafx.controls;
    requires json.simple;
    requires junit;
    requires jdk.compiler;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.media;
    exports edu.fiuba.algo3;
    exports edu.fiuba.algo3.vistas;
    exports edu.fiuba.algo3.modelo;
    exports edu.fiuba.algo3.modelo.Tablero;
    exports edu.fiuba.algo3.modelo.Pieza;
    exports edu.fiuba.algo3.modelo.Terreno;
    exports edu.fiuba.algo3.modelo.CartaDeDesarrollo;
    exports edu.fiuba.algo3.modelo.Recurso;
    exports edu.fiuba.algo3.modelo.Intercambio;
    exports edu.fiuba.algo3.modelo.Dados;
    exports edu.fiuba.algo3.modelo.Ubicacion;
    exports edu.fiuba.algo3.modelo.RondaInicial;
}
