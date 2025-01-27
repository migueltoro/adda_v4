# Recubrimiento de vértices

El Problema del Recubrimiento de Vértices (Vertex Cover) de un grafo consiste en buscar un subconjunto mínimo de vértices tal que cada arista del conjunto es incidente al menos a uno de los vértices escogidos y la suma de sus pesos es mínima. 

## Datos

 - $g$: un grafo de tipo _Graph\<Integer,SimpleEdge\>\>_
 - $g_e$: las aristas del grafo $g$
 - $n$: el número de vértices del grafo

## Modelo 

Escogiendo las variables binarias $x_j,\ j\in [0,n)$ que tomarán el valor 1 si el vértice $x_j$ es escogido, el __Problema del Vertex Cover__ puede ser formulado como:

$$ \min \sum_{j=0}^{n-1} w_j x_j $$

$$ x_u+x_v \geq 1,\ (u,v)\in g_e $$

$$ bin\ x_j,\  j\in [0,n) $$

