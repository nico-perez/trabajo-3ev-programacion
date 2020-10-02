package dibujo.figura;

import dibujo.Color;
import otros.Punto;
import programa.XML;

public class Elipse extends Figura 
{
    // focos de la elipse
    protected Punto f1, f2;

    // distancia caracteristica tal que, siendo p un punto
    // cualquiera en la elipse, d(p, f1) + d(p, f2) = k
    protected float k;

    public Elipse(Punto f1, Punto f2, float k, Color color_relleno)
    {
        this.f1 = f1;
        this.f2 = f2;

        float kmin = getKmin();
        this.k = k < kmin ? kmin : k;

        this.color_relleno = color_relleno;

        calcular_limites();
    }

    protected void calcular_limites()
    {
        Punto centro = this instanceof Circulo ? f1 : new Punto((f2.x() - f1.x()) / 2f + f1.x(), 
                                                                (f2.y() - f1.y()) / 2f + f1.y());
        lim_inf_izq = new Punto(centro.x() - k / 2f, centro.y() - k / 2f);
        lim_sup_der = new Punto(centro.x() + k / 2f, centro.y() + k / 2f);
    }

    @Override
    public Color muestra(Punto p)
    {
        float x1 = f1.x(), y1 = f1.y(), x2 = f2.x(), y2 = f2.y(), x = p.x(), y = p.y();

        if (Math.sqrt((y2 - y) * (y2 - y) + (x2 - x) * (x2 - x))
                + Math.sqrt((y1 - y) * (y1 - y) + (x1 - x) * (x1 - x)) <= k)
            return color_relleno;
        else
            return Color.TRANSPARENTE;
    }

    public void setK(float k)
    {
        float kmin = getKmin();
        this.k = k < kmin ? kmin : k;
        calcular_limites();
    }

    public float getK()
    {
        return k;
    }

    public float getKmin()
    {
        float x1 = f1.x(), y1 = f1.y(), x2 = f2.x(), y2 = f2.y();
        return (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    @Override
    public String etiquetas_xml()
    {
        String s = "";
        s += XML.abrir_etiqueta("elipse");
        {
            s += etiquetas_xml_figura();
            s += XML.abrir_cerrar_etq("foco", f1);
            s += XML.abrir_cerrar_etq("foco", f2);
            s += XML.abrir_cerrar_etq("k", k);
        }
        s += XML.cerrar_etiqueta();
        return s;
    }
}
