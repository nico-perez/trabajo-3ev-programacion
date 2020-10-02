package dibujo.capa;

import dibujo.Color;
import dibujo.figura.*;

public abstract class Capa 
{
    public abstract void agregar_figura(Figura figura);
    public abstract void combinar_pixeles(Color[][] fondo);
    public abstract String etiquetas_xml();
}
