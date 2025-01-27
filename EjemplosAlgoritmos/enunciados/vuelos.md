# Vuelos

Tenemos un conjunto de vuelos, cada vuelo tiene un origen, un destino y un tiempo de vuelo y queremos calcular el vuelo más corto desde un origen a un destino posiblemente con varias escalas. Claramente el problema se puede modelar mediante un multigrafo con vértices que son aeropuertos y aristas que son vuelos de un aeropuerto a otro. Cada vuelo tiene como propiedades el tiempo de salida del aeropuerto origen y el tiempo de llegada al aeropuerto destino. En el problema la función objetivo, el tiempo total para ir de un aeropuerto a otro posiblemente pasando por algunos aeropuertos intermedios, es la suma del tiempo de los vuelos más las esperas en los aeropuertos intermedios para conectar un vuelo con otro. 

# Vuelo

- $from$: String
- $to$: String 
- $horaDeSalida$: Double
- $duracion$: Double

## Grafo

Para modelar el problema tenemos un multigrafo $g$ cuyos vértices son los aeropuertos, de tipo _String_, y cuyas aristas son los vuelos.

Si $g_e$ es el conjunto de las aristas del grafo y $C_P$ el conjunto de tripletas $ijk$ dónde $i$ es un vértice del camino,  $j$ una arista el camino que llega al vértice $i$, y $k$ una arista el camino que sale del vértice $i$ el peso de un camino  $C$ es:

$$ |C|=\sum_{j\in g_e} w_j+\sum_{ijk\in C_P} u_{ijk}$$

## Modelo

El problema podemos modelarlo como uno de camino mínimo desde el vértice $v_0$ hasta el $v_1$. La variable $x_i$ indica el vértice en la posición $i$.

$$min \ r$$

$$x_0=v_0$$

$$x_{r}=v_1$$

$$OP_{i=0|g}^{r} \ x_i$$

$$int\ r$$

$$Vuelo \ x_i,i∈[0,r-1]$$
