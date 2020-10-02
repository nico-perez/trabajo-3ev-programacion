package programa;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;

import dibujo.Lienzo;

public class XML
{
    private static final String cabecera = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";

    private static int nivel = 0;
    private static String tabs = "";
    private static Stack<String> pila = new Stack<String>();

    private Lienzo lienzo;

    public XML(Lienzo lienzo)
    {
        this.lienzo = lienzo;
    }

    public static String abrir_etiqueta(String nombre)
    {
        String s = nombre.replace(' ', '_');
        pila.push(tabs + "</" + s + ">\n");
        String ret = tabs + "<" + s + ">\n";

        nivel++;
        tabs += "  ";

        return ret;
    }

    public static String abrir_cerrar_etq(String nombre, Object contenido)
    {
        String s = nombre.replace(' ', '_');
        return tabs + "<" + s + ">" + contenido + "</" + s + ">\n";
    }

    public static String linea(Object contenido)
    {
        return tabs + contenido + "\n";
    }

    public static String cerrar_etiqueta() throws EmptyStackException
    {
        nivel--;
        tabs = tabs.substring(2);
        return pila.pop();
    }

    public void exportar_archivo(String nombre) throws IOException
    {
        FileWriter fr = new FileWriter(nombre + ((nombre.endsWith(".xml")) ? "" : ".xml"));
        fr.append(cabecera);
        fr.append(lienzo.etiquetas_xml());
        fr.close();
    }

    
    public static Lienzo importar_archivo(String nombre) throws FileNotFoundException
    {
        FileReader fr = new FileReader(nombre + ((nombre.endsWith(".xml")) ? "" : ".xml"));

        return null;
    }

}
