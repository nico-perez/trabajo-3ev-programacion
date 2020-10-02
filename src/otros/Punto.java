package otros;

public class Punto
{
    private int x, y;

    public Punto(int x, int y)
    {
        setX(x);
        setY(y);
    }

    public Punto(float x, float y)
    {
        this((int) x, (int) y);
    }

    public int x()
    {
        return x;
    }

    public int y()
    {
        return y;
    }

    public void setX(int x)
    {
        this.x = Math.max(x, 0);
    }

    public void setY(int y)
    {
        this.y = Math.max(y, 0);
    }

    @Override
    public String toString()
    {
        return "" + x + ", " + y;
    }

}
