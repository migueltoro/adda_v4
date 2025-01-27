# Coloreado de grafos

El Problema de Coloreado de Grafos consiste en buscar el mínimo número de colores tal que dando un color a cada vértice sea distinto el color asociado a dos vértices vecinos. Para modelarlo como un Problema de Programación Lineal Entera partimos de las variables binarias $y_k,k∈[0,m)$, que tomarán el valor 1 si el color $k$ es usado y $n$ es el número de vértices del grafo. Además, introducimos las variables binarias $x_{ik}$ que serán 1 si el vértice $i$ se le asigna el color $k$.  El modelo del problema es:

## Datos

 - $g$: Graph\<Integer,SimpleEdge\<Integer\>\>, el grafo
 - $g_e$: SimpleEdge\<Integer\>, Las aristas del grafo
 - $n$: Integer, el número de vértices del grafo
 - $m$: Integer, número de colores, obtenido previamente mediante una solución voraz 
 - $N(i)$:  los vértices vecinos del vértice $i$ sin incluir él mismk:o 
 - $S_{k : N(i)} cav.get(k)$: el conjunto de los colores de los vértices en $N(i)$.

## Primer modelo

$$ min⁡∑_{k=0}^{m-1} y_k $$

$$ \sum\limits_{k=0}^{m-1} x_{ik} = 1,     i \in [0,n-1] $$

$$ x_{ik} \leq y_k,           i \in [0,n-1],k \in [0,m-1] $$

$$ x_{ik}+x_{jk} \leq 1,         (i,j) \in g_e,k\in [0,m-1] $$

$$ bin \  x_{ik},   y_k,      i \in [0,n-1],k \in [0,m-1] $$

## Segundo modelo

$$ \min⁡ CD_{i=0}^{n-1} x_i $$

$$ x_i \notin S_{k:N(i)} x_k,      i \in [0,n-1] $$

$$ 0 \leq  x_i \lt m,         i \in [0,n-1]$$

$$ int  \ x_i,   i \in [0,n-1] $$

## Grafo

### Propiedades del vértice
 
- $index$: Integer, básica
- $cav$: Map\<Integer,Integer\>, colores asignados a cada vértice en $[0,index-1]$, básica
- $ca$: IntegerSet, colores ya asignados, derivada, $ca=cav.values()$.
- $nc$: Integer, número de colores ya asignados, derivada, $nc = ca.size()$.
- $cv$: IntegerSet, colores asignados a los vecinos de $i$ que ya tienen color, derivada. 
	$$cv =  S_{k : N(i) \cap cav.keys()} \ cav.get(k) $$








