package dibujo;

import java.util.ArrayList;
import java.util.Arrays;

import dibujo.capa.Capa;
import dibujo.capa.CapaRaster;
import dibujo.capa.CapaVector;
import dibujo.figura.Figura;
import programa.XML;

public class Lienzo
{
	private String titulo = "sin_nombre";
	private Color fondo = Color.BLANCO;
	private ArrayList<Capa> capas;
	private int altura, anchura;
	
	public Lienzo(int altura, int anchura, Color relleno)
	{
		this.altura = altura;
		this.anchura = anchura;
		fondo = relleno;
		capas = new ArrayList<Capa>();
	}
	
	public Lienzo(Color[][] pixeles)
	{
		this.altura = pixeles[0].length;
		this.anchura = pixeles.length;
		capas = new ArrayList<Capa>();
		capas.add(new CapaRaster(pixeles));
	}
	
	public Lienzo(int altura, int anchura)
	{
		this(altura, anchura, Color.BLANCO);
	}
	
	public boolean agregar_figura(int ncapa, Figura figura)
	{
		Capa c;
		try 
		{
			c = capas.get(ncapa);
		}
		catch(IndexOutOfBoundsException e)
		{
			return false;
		}
		c.agregar_figura(figura);
		return true;
	}
	
	public void agregar_capa_raster(Color[][] pixeles)
	{
		capas.add(new CapaRaster(pixeles));
	}
	
	public void agregar_capa_raster(int anchura, int altura)
	{
		capas.add(new CapaRaster(anchura, altura));
	}
	
	public Capa capa(int indice)
	{
		return capas.get(indice);
	}
	
	public void agregar_capa_vector()
	{
		capas.add(new CapaVector());
	}
	
	
	
	public Color[][] aplanar()
	{
		Color[][] def = new Color[anchura][altura];
		for (Color[] c : def) Arrays.fill(c, fondo);
		
//		for (int y = 0; y < altura; ++y)
//			for (int x = 0; x < anchura; ++x)
//				def[x][y] = new Color((byte)(x / 100.0f * 255) , (byte)(x * y / 10000.0f * 255), (byte)(y / 100.0f * 255));
		
		for (Capa c : capas) c.combinar_pixeles(def);

		return def;
	}
	
	//public 
	
	public int getAltura()
	{
		return altura;
	}
	
	public int getAnchura()
	{
		return anchura;
	}

	public String getTitulo() 
	{
		return titulo;
	}

	public void setTitulo(String titulo) 
	{
		this.titulo = titulo;
	}

	public Color getFondo() 
	{
		return fondo;
	}
	
	public void setFondo(Color fondo)
	{
		this.fondo = fondo;
	}
	
	public String etiquetas_xml()
	{
		String s = "";
		
		s += XML.abrir_etiqueta(getTitulo());
		
		s += XML.abrir_cerrar_etq("color_fondo", fondo);
		s += XML.abrir_cerrar_etq("altura", altura);
		s += XML.abrir_cerrar_etq("anchura", anchura);
		
		for (Capa c : capas)
			s += c.etiquetas_xml();
		
		s += XML.cerrar_etiqueta();

		return s;
	}
}
