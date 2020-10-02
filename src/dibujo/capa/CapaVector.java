package dibujo.capa;

import java.util.ArrayList;

import dibujo.Color;
import dibujo.figura.Figura;
import otros.Mates;
import otros.Punto;
import programa.XML;

public class CapaVector extends Capa 
{
    private ArrayList<Figura> figuras;

    public CapaVector() 
    {
        figuras = new ArrayList<Figura>();
    }

    @Override
    public void agregar_figura(Figura figura) 
    {
        figuras.add(figura);
    }

    @Override
    public void combinar_pixeles(Color[][] fondo) 
    {
        for (Figura f : figuras) 
        {
            int x_f = Mates.minmax(0, f.getLSD().x(), fondo.length - 1),
                y_f = Mates.minmax(0, f.getLSD().y(), fondo[0].length - 1),
                x_0 = Mates.minmax(0, f.getLII().x(), fondo.length - 1),
                y_0 = Mates.minmax(0, f.getLII().y(), fondo[0].length - 1);

            for (int x = x_0; x <= x_f; ++x)
                for (int y = y_0; y <= y_f; ++y)
                    fondo[x][y] = Color.mezclar(f.muestra(new Punto(x, y)), fondo[x][y]);
        }
    }

    @Override
    public String etiquetas_xml() 
    {
        String s = "";
        s += XML.abrir_etiqueta("capaVector");
        {
            for (Figura f : figuras)
                s += f.etiquetas_xml();
        }
        s += XML.cerrar_etiqueta();
        return s;
    }
}
