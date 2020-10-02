package otros;

public class Mates
{
    // Distancia entre el segmento de extremos r1, r2 y el punto p3
    public static float distancia_segmento_punto(Punto r1, Punto r2, Punto p3)
    {
        Punto p1, p2;
        if (r1.x() < r2.x()) 
        {
            p1 = r1;
            p2 = r2;
        } 
        else 
        {
            p1 = r2;
            p2 = r1;
        }

        float x1 = p1.x(), y1 = p1.y(), x2 = p2.x(), y2 = p2.y(), x3 = p3.x(), y3 = p3.y();

        // calculamos el punto de i interseccion entre la recta
        // p1p2 y la recta perpendicular a p1p2 que pasa por p3
        float x = (((x2 - x1) * y2 + (x1 - x2) * y1) * y3 + x1 * y2 * y2 + (-x2 - x1) * y1 * y2 + x2 * y1 * y1
                + (x2 * x2 - 2 * x1 * x2 + x1 * x1) * x3)
                / (y2 * y2 - 2 * y1 * y2 + y1 * y1 + x2 * x2 - 2 * x1 * x2 + x1 * x1);
        float y;
        if (x2 - x1 != 0) y = -((x1 - x) * y2 + (x - x2) * y1) / (x2 - x1);
        else              y = ((y2 - y1) * y3 + (x2 - x1) * x3 - x * x2 + x * x1) / (y2 - y1);

        // el punto c es el que se usara para calcular la distancia de p3 al segmento
        // c puede tomar el valor de los extremos del segmento o i (si i esta contenido
        // en el segmento)
        float xc, yc;
        if (p1.x() == p2.x())
            if (p1.y() < p2.y())
                if (y >= p2.y()) {
                    xc = p2.x();
                    yc = p2.y();
                } else if (y <= p1.y()) {
                    xc = p1.x();
                    yc = p1.y();
                } else {
                    xc = x;
                    yc = y;
                }
            else if (y >= p1.y()) {
                xc = p1.x();
                yc = p1.y();
            } else if (y <= p2.y()) {
                xc = p2.x();
                yc = p2.y();
            } else {
                xc = x;
                yc = y;
            }
        else if (x <= x1) {
            xc = p1.x();
            yc = p1.y();
        } else if (x >= x2) {
            xc = p2.x();
            yc = p2.y();
        } else {
            xc = x;
            yc = y;
        }

        // finalmente, se devuelve la distancia entre los puntos p3 y c
        float xf = x3 - xc, yf = y3 - yc;
        return (float) Math.sqrt(xf * xf + yf * yf);
    }

    // Si v < min, devuelve min. Si v > max, devuelve max. Si no, devuelve v.
    public static int minmax(int min, int v, int max)
    {
        return Math.min(Math.max(v, min), max);
    }
}
