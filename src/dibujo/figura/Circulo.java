package dibujo.figura;

import dibujo.Color;
import otros.Punto;
import programa.XML;

public class Circulo extends Elipse {

    public Circulo(Punto centro, float radio, Color color_relleno) {
        super(centro, centro, radio * 2, color_relleno);
    }

    public void setRadio(float radio) {
        setK(radio * 2);
    }

    public float getRadio() {
        return getK() / 2;
    }

    @Override
    public String etiquetas_xml() {
        String s = "";

        s += XML.abrir_etiqueta("circulo");

        s += etiquetas_xml_figura();
        s += XML.abrir_cerrar_etq("centro", f1);
        s += XML.abrir_cerrar_etq("diametro", k);

        s += XML.cerrar_etiqueta();

        return s;
    }

}
