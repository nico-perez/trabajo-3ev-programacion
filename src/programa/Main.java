package programa;

import java.io.*;

import dibujo.Color;
import dibujo.Lienzo;
import dibujo.capa.CapaRaster;
import dibujo.figura.*;
import otros.Punto;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        // Lienzo lienzo = new Lienzo(BMP.importar_archivo("testrec1"));
        // lienzo.agregar_capa_raster(BMP.importar_archivo("d"));
        //
        //// ((CapaRaster) lienzo.capa(1)).recortar(new Punto(0, 0), new Punto(50, 50));
        // ((CapaRaster) lienzo.capa(1)).desplazar(-300, -300);
        //
        // BMP bmp = new BMP(lienzo);
        // bmp.exportar_archivo("resulrec");
        //
        Lienzo l = new Lienzo(300, 300);

        l.agregar_capa_vector();
        l.agregar_figura(0, new Rectangulo(new Punto(30, 30), new Punto(270, 270), Color.ROJO));
        l.agregar_figura(0, new Segmento(new Punto(40, 40), new Punto(260, 260), Color.VERDE, 9));
        l.agregar_figura(0, new Segmento(new Punto(5, 240), new Punto(240, 60), new Color(100, 60, 240, 140), 50));
        l.agregar_figura(0, new Circulo(new Punto(200, 150), 115, new Color(30, 255, 200, 100)));
        l.agregar_figura(0, new Elipse(new Punto(50, 150), new Punto(250, 150), 220, new Color(230, 130, 0, 120)));
        /*
         * l.agregar_capa_raster(BMP.importar_archivo("resulrec"));
         * ((CapaRaster)l.capa(1)).recortar(new Punto(0, 0), new Punto(50, 50));
         * ((CapaRaster)l.capa(1)).desplazar(100, 100);
         */
        l.agregar_capa_vector();
        l.agregar_figura(2, new Rectangulo(new Punto(120, 120), new Punto(180, 180), Color.ROJO));

        BMP bmp = new BMP(l);
        bmp.exportar_archivo("cosis");

        // XML xml = new XML(l);
        // xml.exportar_archivo("cosis");
    }
}
