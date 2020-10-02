package dibujo.figura;

import dibujo.Color;
import otros.Punto;
import programa.XML;
import otros.Mates;

public class Segmento extends Figura {
    // grosor del trazo del segmento
    private int grosor_px;
    // extremos del segmento
    private Punto p1, p2;

    public Segmento(Punto p1, Punto p2, Color color_relleno, int grosor_px) {
        this.p1 = p1;
        this.p2 = p2;
        this.color_relleno = color_relleno;
        this.grosor_px = grosor_px < 0 ? 0 : grosor_px;

        calcular_limites();
    }

    @Override
    public Color muestra(Punto p) {
        if (Mates.distancia_segmento_punto(p1, p2, p) <= grosor_px * .5)
            return color_relleno;
        else
            return Color.TRANSPARENTE;
    }

    public void setGrosor(int grosor_px) {
        this.grosor_px = grosor_px < 0 ? 0 : grosor_px;
    }

    public int getGrosor() {
        return grosor_px;
    }

    protected void calcular_limites() {
        lim_inf_izq = new Punto(Math.min(p1.x(), p2.x()) - grosor_px * .5f, Math.min(p1.y(), p2.y()) - grosor_px * .5f);
        lim_sup_der = new Punto(Math.max(p1.x(), p2.x()) + grosor_px * .5f, Math.max(p1.y(), p2.y()) + grosor_px * .5f);
    }

    @Override
    public String etiquetas_xml() {
        String s = "";

        s += XML.abrir_etiqueta("segmento");

        s += etiquetas_xml_figura();

        s += XML.abrir_cerrar_etq("grosor", grosor_px);
        s += XML.abrir_cerrar_etq("extremo", p1);
        s += XML.abrir_cerrar_etq("extremo", p2);

        s += XML.cerrar_etiqueta();

        return s;
    }

}
