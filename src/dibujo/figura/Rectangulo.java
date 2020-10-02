package dibujo.figura;

import dibujo.Color;
import otros.Punto;
import programa.XML;

public class Rectangulo extends Figura {

    public Rectangulo(Punto p1, Punto p2, Color color) {
        this.lim_inf_izq = new Punto(Math.min(p1.x(), p2.x()), Math.min(p1.y(), p2.y()));
        this.lim_sup_der = new Punto(Math.max(p1.x(), p2.x()), Math.max(p1.y(), p2.y()));
        this.color_relleno = color;
    }

    @Override
    public Color muestra(Punto p) {
        if (dentro(p)) { // esto es malo pero pf
            return color_relleno;
        } else {
            return Color.TRANSPARENTE;
        }
    }

    @Override
    public String etiquetas_xml() {
        String s = "";
        s += XML.abrir_etiqueta("rectangulo");
        s += etiquetas_xml_figura();
        s += XML.cerrar_etiqueta();
        return s;
    }

}
