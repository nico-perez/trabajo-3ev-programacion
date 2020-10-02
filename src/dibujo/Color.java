package dibujo;

public class Color 
{
    private byte r, g, b, a;

    public Color(int r, int g, int b, int a)
    {
        this.r = (byte) r;
        this.g = (byte) g;
        this.b = (byte) b;
        this.a = (byte) a;
    }

    public Color(int r, int g, int b)
    {
        this(r, g, b, 0xff);
    }

    public Color(byte r, byte g, byte b, byte a)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Color(byte r, byte g, byte b)
    {
        this(r, g, b, (byte) 0xff);
    }

    public static final Color
        ROJO = new Color(205, 92, 92),
        ROSA = new Color(255, 192, 203),
        NARANJA = new Color(255, 160, 122),
        AMARILLO = new Color(240, 230, 140),
        LILA = new Color(230, 230, 250),
        VERDE = new Color(152, 251, 152),
        AZUL = new Color(175, 238, 238),
        MARRON = new Color(222, 184, 135),
        BLANCO = new Color(255, 245, 238),
        GRIS = new Color(192, 192, 192),
        NEGRO = new Color(0, 0, 0),
        TRANSPARENTE = new Color(0, 0, 0, 0);

    public void alfa(byte b)
    {
        a = b;
    }

    public static Color mezclar(Color encima, Color debajo)
    {
        float enc_01_r = (float) Byte.toUnsignedInt(encima.r) / 255,
              enc_01_g = (float) Byte.toUnsignedInt(encima.g) / 255,
              enc_01_b = (float) Byte.toUnsignedInt(encima.b) / 255,
              enc_01_a = (float) Byte.toUnsignedInt(encima.a) / 255,

              deb_01_r = (float) Byte.toUnsignedInt(debajo.r) / 255,
              deb_01_g = (float) Byte.toUnsignedInt(debajo.g) / 255,
              deb_01_b = (float) Byte.toUnsignedInt(debajo.b) / 255,
              deb_01_a = (float) Byte.toUnsignedInt(debajo.a) / 255;

        if (enc_01_a < .001f)
            return debajo;
        else if (enc_01_a > .997f || deb_01_a < .001f)
            return encima;
        else 
        {
            float res_01_a = enc_01_a + deb_01_a * (1 - enc_01_a);
            if (res_01_a < .001f)
                return Color.TRANSPARENTE;
            else
                return new Color((int) (((enc_01_r * enc_01_a + deb_01_r * deb_01_a * (1 - enc_01_a)) / res_01_a) * 255),
                                 (int) (((enc_01_g * enc_01_a + deb_01_g * deb_01_a * (1 - enc_01_a)) / res_01_a) * 255),
                                 (int) (((enc_01_b * enc_01_a + deb_01_b * deb_01_a * (1 - enc_01_a)) / res_01_a) * 255),
                                 (int) (res_01_a * 255));
        }
    }

    public byte[] rgb24bpp()
    {
        return new byte[] { b, g, r };
    }

    @Override
    public String toString()
    {
        return "" + Byte.toUnsignedInt(r) + ", " + 
                    Byte.toUnsignedInt(g) + ", " + 
                    Byte.toUnsignedInt(b) + ", " + 
                    Byte.toUnsignedInt(a);
    }

}