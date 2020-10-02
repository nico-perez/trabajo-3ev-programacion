package programa;

import java.io.*;

import dibujo.*;

public class BMP 
{
    // al windows le mola el little endian pero java
    // utiliza big endian asi que hay que cambiar de
    // orden los bytes al escribir y despues tambien
    // al leer otra vez

    // cabecera BMP (14 bytes)
    private static final short numero_magico = 0x4d42;     // 2 bytes ('B','M' en ASCII)
    private              int   tamano_archivo;             // 4 bytes (entero sin signo)
    private static final int   reservado =     0x4f43494e; // 4 bytes ('N','I','C','O' en ASCII)
    private static final int   datos_imagen =  0x36;       // 4 bytes

    // cabecera DIB (BITMAPINFOHEADER - 40 bytes)
    private static final int   tamano_cabecera =       0x28; // 4 bytes
    private              int   anchura_px;                   // 4 bytes (entero con signo)
    private              int   altura_px;                    // 4 bytes (entero con signo)
    private static final short planos_color =          0x1;  // 2 bytes
    private static final short bits_por_pixel =        0x18; // 2 bytes
    private static final int   compresion =            0x0;  // 4 bytes
    private              int   tamano_imagen;                // 4 bytes (entero sin signo)
    private static final int   resolucion_horizontal = 0;    // 4 bytes (entero con signo)
    private static final int   resolucion_vertical =   0;    // 4 bytes (entero con signo)
    private static final int   colores_paleta =        0x0;  // 4 bytes
    private static final int   colores_imp =           0x0;  // 4 bytes

    private Lienzo lienzo;

    public BMP(Lienzo lienzo)
    {
        this.anchura_px = lienzo.getAnchura();
        this.altura_px = lienzo.getAltura();

        tamano_imagen = anchura_px * altura_px * 3;
        tamano_archivo = datos_imagen + tamano_imagen;

        this.lienzo = lienzo;
    }

    public void exportar_archivo(String nombre) throws IOException 
    {
        FileOutputStream fos = new FileOutputStream(nombre + ((nombre.endsWith(".bmp")) ? "" : ".bmp"));
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        DataOutputStream dos = new DataOutputStream(bos);

        dos.writeShort(Short.reverseBytes(numero_magico));
        dos.writeInt(Integer.reverseBytes(tamano_archivo));
        dos.writeInt(Integer.reverseBytes(reservado));
        dos.writeInt(Integer.reverseBytes(datos_imagen));

        dos.writeInt(Integer.reverseBytes(tamano_cabecera));
        dos.writeInt(Integer.reverseBytes(anchura_px));
        dos.writeInt(Integer.reverseBytes(altura_px));
        dos.writeShort(Short.reverseBytes(planos_color));
        dos.writeShort(Short.reverseBytes(bits_por_pixel));
        dos.writeInt(Integer.reverseBytes(compresion));
        dos.writeInt(Integer.reverseBytes(tamano_imagen));
        dos.writeInt(Integer.reverseBytes(resolucion_horizontal));
        dos.writeInt(Integer.reverseBytes(resolucion_vertical));
        dos.writeInt(Integer.reverseBytes(colores_paleta));
        dos.writeInt(Integer.reverseBytes(colores_imp));

        Color[][] colores = lienzo.aplanar();

        // Datos de color
        for (int y = 0; y < altura_px; ++y)
        {
            // Escribir fila de pixeles
            for (int x = 0; x < anchura_px; ++x)
                dos.write(colores[x][y].rgb24bpp());

            // Alineacion a byte multiplo de 4
            for (int p = 0; p < anchura_px % 4; ++p)
                dos.writeByte(0);
        }

        dos.close();
    }

    public static Color[][] importar_archivo(String nombre) throws IOException
    {
        FileInputStream fis = new FileInputStream(nombre + ((nombre.endsWith(".bmp")) ? "" : ".bmp"));
        BufferedInputStream bis = new BufferedInputStream(fis);
        DataInputStream dis = new DataInputStream(bis);

        boolean bien = true;

        // comprobacion de archivo valido (mas o menos)

        bien &= Short.reverseBytes(dis.readShort()) == numero_magico;
        /* int tamano_archivo = Integer.reverseBytes(dis.readInt()); */ dis.readInt();
        /* reservado */ dis.readInt();
        bien &= Integer.reverseBytes(dis.readInt()) == datos_imagen;

        bien &= Integer.reverseBytes(dis.readInt()) == tamano_cabecera;
        int anchura_px = Integer.reverseBytes(dis.readInt());
        int altura_px = Integer.reverseBytes(dis.readInt());
        bien &= Short.reverseBytes(dis.readShort()) == planos_color;
        bien &= Short.reverseBytes(dis.readShort()) == bits_por_pixel;
        bien &= Integer.reverseBytes(dis.readInt()) == compresion;
        /* int tamano_imagen = Integer.reverseBytes(dis.readInt()); */ dis.readInt();
        /* bien &= Integer.reverseBytes(dis.readInt()) == resolucion_horizontal; */ dis.readInt();
        /* bien &= Integer.reverseBytes(dis.readInt()) == resolucion_vertical; */ dis.readInt();
        /* bien &= Integer.reverseBytes(dis.readInt()) == colores_paleta; */ dis.readInt();
        /* bien &= Integer.reverseBytes(dis.readInt()) == colores_imp; */ dis.readInt();

        if (bien)
        {
            Color[][] fondo = new Color[anchura_px][altura_px];

            // Datos de color
            for (int y = 0; y < altura_px; ++y)
            {
                // Leer fila de pixeles
                for (int x = 0; x < anchura_px; ++x)
                {
                    byte b = dis.readByte(), g = dis.readByte(), r = dis.readByte();
                    fondo[x][y] = new Color(r, g, b);
                }

                // Alineacion modulo 4
                for (int p = 0; p < anchura_px % 4; ++p)
                    dis.readByte();
            }
            dis.close();
            return fondo;
        } 
        else 
        {
            dis.close();
            return null;
        }
    }
}
