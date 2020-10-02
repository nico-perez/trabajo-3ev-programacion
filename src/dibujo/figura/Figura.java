package dibujo.figura;

import dibujo.Color;
import otros.Punto;
import programa.XML;

public abstract class Figura {
    // Color de relleno de la figura
    protected Color color_relleno;
    // esquinas de la caja contenedora
    protected Punto lim_sup_der, lim_inf_izq;

    public void setColor(Color color) {
        color_relleno = color;
    }

    public Color getColor() {
        return color_relleno;
    }

    // Devuelve el color de la figura en el punto p (coordenadas globales)
    public abstract Color muestra(Punto p);

    // Devuelve cierto si y solo si el punto p se encuentra dentro del
    // rectangulo definido por los puntos lim_sup_der y lim_inf_izq
    public boolean dentro(Punto p) {
        return p.x() >= lim_inf_izq.x() && p.x() <= lim_sup_der.x() && p.y() >= lim_inf_izq.y()
                && p.y() <= lim_sup_der.y();
    }

    public Punto getLSD() {
        return lim_sup_der;
    }

    public Punto getLII() {
        return lim_inf_izq;
    }

    protected String etiquetas_xml_figura() {
        String s = "";
        s += XML.abrir_cerrar_etq("color_relleno", color_relleno);
        s += XML.abrir_cerrar_etq("lsd", lim_sup_der);
        s += XML.abrir_cerrar_etq("lii", lim_inf_izq);
        return s;
    }

    public abstract String etiquetas_xml();
}
