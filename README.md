# Trabajo de la tercera evaluación de programación
(que no llegué a terminar. Lo siento, Julián 😅)

### Es una «herramienta» para hacer dibujos mediante comandos
Los dibujos pueden importarse o exportarse como BMP<sup>1</sup> o XML<sup>2</sup>, y editarse mediante la adición de *capas raster* o *capas vector*.
- Las *capas raster* pueden ser importadas desde un archivo BMP<sup>1</sup> o XML<sup>2</sup> y consisten en una matriz de n·m píxeles RGBA(32bpp). Estas capas se pueden desplazar, recortar y modificar pintando los píxeles.
- Las *capas vector* pueden ser importadas desde un archivo XML<sup>2</sup>, y almacenan las figuras (elipses, segmentos, rectángulos) de manera matemática, no los píxeles.

Las figuras de momento disponibles con los datos que contienen:
- Elipses y círculos: dos puntos y un `float` (distancia característica k). Dos puntos para su AABB. Un color de relleno.
- Segmentos: dos puntos, inicial y final; y un `int` para el grosor en píxeles. Dos puntos para su AABB. Un color de relleno.
- Rectángulos: Dos puntos, los de su AABB: límite inferior izquierdo y superior derecho. Un color de relleno.

Cada punto son dos `int`. Cada color son cuatro `byte`. AABB significa [*Axis-aligned Bounding Box*](https://en.wikipedia.org/wiki/Minimum_bounding_box#Axis-aligned_minimum_bounding_box), creo que no hay término equivalente en castellano.

El tema de los comandos está todavía por implementar. A ver si algún día me pongo a ello. 🤔

<sub>1: siempre que sea [BITMAPINFOHEADER](https://en.wikipedia.org/wiki/BMP_file_format#DIB_header_(bitmap_information_header)), exclusivamente.</sub><br>
<sup>2: los XML generados por el propio programa, por lo menos.</sup>
