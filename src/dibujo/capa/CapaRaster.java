package dibujo.capa;

import java.util.*;

import dibujo.Color;
import dibujo.figura.Figura;
import otros.Punto;
import programa.XML;

public class CapaRaster extends Capa 
{
    private Color[][] pixeles;
    
    // para desplazar la capa sobre el lienzo
    private int desp_derecha, desp_arriba;

    // para recortar la capa
    private Punto rec_inf_izq, rec_sup_der;

    // nombre
    private String nombre = "";

    public CapaRaster(int anchura_px, int altura_px, String nombre) 
    {
        pixeles = new Color[anchura_px][altura_px];
        for (Color[] c : pixeles)
            Arrays.fill(c, Color.TRANSPARENTE);
        this.nombre = nombre;

        rec_inf_izq = new Punto(0, 0);
        rec_sup_der = new Punto(anchura_px, altura_px);
        desp_derecha = 0;
        desp_arriba = 0;
    }

    public CapaRaster(int anchura_px, int altura_px) 
    {
        this(anchura_px, altura_px, "");
    }

    public CapaRaster(Color[][] pixeles, String nombre) 
    {
        this.pixeles = pixeles;
        this.nombre = nombre;

        rec_inf_izq = new Punto(0, 0);
        rec_sup_der = new Punto(pixeles.length, pixeles[0].length);
        desp_derecha = 0;
        desp_arriba = 0;
    }

    public CapaRaster(Color[][] pixeles) 
    {
        this(pixeles, "");
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    // Numero de pixeles que se va a desplazar la imagen en cada eje
    public void desplazar(int hacia_la_derecha, int hacia_arriba) 
    {
        desp_derecha += hacia_la_derecha;
        desp_arriba += hacia_arriba;
    }

    // pone los desplazamientos a 0 (sin desplazamiento)
    public void reset_desplazamiento() 
    {
        desp_derecha = desp_arriba = 0;
    }

    // Puntos que definen el rectangulo que va a recortar la imagen
    public void recortar(Punto p1, Punto p2) 
    {
        rec_inf_izq = new Punto(Math.min(p1.x(), p2.x()), Math.min(p1.y(), p2.y()));
        rec_sup_der = new Punto(Math.max(p1.x(), p2.x()), Math.max(p1.y(), p2.y()));
    }

    // pone el recorte para que ocupe toda la imagen (sin recorte)
    public void reset_recorte() 
    {
        rec_inf_izq = new Punto(0, 0);
        rec_sup_der = new Punto(pixeles.length, pixeles[0].length);
    }

    @Override
    public void agregar_figura(Figura figura) 
    {
        for (int x = 0; x < pixeles.length; ++x)
            for (int y = 0; y < pixeles[0].length; ++y)
                pixeles[x][y] = Color.mezclar(figura.muestra(new Punto(x, y)), pixeles[x][y]);
    }

    @Override
    public void combinar_pixeles(Color[][] fondo) 
    {
        if ((rec_inf_izq.x() >= pixeles.length && rec_inf_izq.y() >= pixeles[0].length)
                || (rec_sup_der.x() < 0 && rec_sup_der.y() < 0))
            return; // El recorte queda fuera de la imagen. No hay nada que recortar.
        if ((desp_derecha >= fondo.length || desp_derecha <= -pixeles.length || desp_arriba >= fondo[0].length
                || desp_arriba <= -pixeles[0].length))
            return; // La imagen esta tan desplazada que no se ve.

        Punto p_ini = new Punto(rec_inf_izq.x() + desp_derecha, rec_sup_der.y() + desp_arriba),
              p_fin = new Punto(rec_sup_der.x() + desp_derecha, rec_inf_izq.y() + desp_arriba);

        if (p_ini.x() >= fondo.length)      p_ini.setX(fondo.length);
        if (p_ini.y() >= fondo[0].length)   p_ini.setY(fondo[0].length);
        if (p_fin.x() >= fondo.length)      p_fin.setX(fondo.length);
        if (p_fin.y() >= fondo[0].length)   p_fin.setY(fondo[0].length);

        for (int x = p_ini.x(); x < p_fin.x(); ++x)
            for (int y = p_ini.y() - 1; y >= p_fin.y(); --y)
                fondo[x][y] = Color.mezclar(pixeles[x - desp_derecha][y - desp_arriba], fondo[x][y]);
    }

    @Override
    public String etiquetas_xml() 
    {
        String s = "";
        s += XML.abrir_etiqueta("capaRaster");
        {
            if (nombre != null && !nombre.equals(""))
                s += XML.abrir_cerrar_etq("nombre", nombre);
            s += XML.abrir_cerrar_etq("desplazamiento", desp_derecha + ", " + desp_arriba);
            s += XML.abrir_cerrar_etq("recorte", rec_inf_izq + ", " + rec_sup_der);

            s += XML.abrir_etiqueta("datosImagen");
            {
                s += XML.abrir_cerrar_etq("dimensiones", pixeles.length + ", " + pixeles[0].length);
                s += XML.abrir_etiqueta("pixeles");
                {
                    String px = "";
                    for (int y = pixeles[0].length - 1; y >= 0; --y)
                        for (int x = 0; x < pixeles.length; ++x)
                            px += pixeles[x][y] + ((x == pixeles.length - 1 && y == 0) ? "" : ", ");
                    s += XML.linea(px);
                } 
                s += XML.cerrar_etiqueta(); // pixeles
            } 
            s += XML.cerrar_etiqueta(); // datosImagen
        } 
        s += XML.cerrar_etiqueta(); // capaRaster
        return s;
    }
}
