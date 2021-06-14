### «Herramienta» para hacer dibujos mediante comandos pero sin comandos porque está sin terminar jeje
Los dibujos pueden importarse o exportarse como BMP<sup>1</sup> o XML, y editarse mediante la adición de capas.
- Las *capas raster* pueden ser importadas desde un archivo BMP<sup>1</sup> o XML y consisten en una matriz de n·m píxeles RGBA(32bpp). Estas capas se pueden desplazar, recortar y modificar pintando los píxeles.
- Las *capas vector* pueden ser importadas desde un archivo XML, y almacenan figuras en vez de píxeles.

Datos de las figuras:
- Elipses y círculos
  - Dos puntos con distancia característica. 
  - Dos puntos para AABB. 
  - Color de relleno.
- Segmentos
  - Puntos inicial y final.
  - Grosor en px.
  - Dos puntos para AABB.
  - Color de relleno.
- Rectángulos:
  - Dos puntos para AABB.
  - Color de relleno.

[AABB](https://en.wikipedia.org/wiki/Minimum_bounding_box#Axis-aligned_minimum_bounding_box)

<sub>1: solo [BITMAPINFOHEADER](https://en.wikipedia.org/wiki/BMP_file_format#DIB_header_(bitmap_information_header)), que es la cabecera más sencilla</sub><br>
