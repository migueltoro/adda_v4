# Reparto

Un mensajero debe repartir paquetes a una serie de clientes, ubicados en distintas localizaciones, estando las conexiones entre ellas modeladas con un grafo ponderado por la distancia en kilómetros. Determinar en qué orden debe repartir los paquetes si:

 - El reparto comienza desde una localización concreta (el almacén) a las 8am.
 - Debe visitar todas las localizaciones
 - Cada kilómetro recorrido tiene un coste de un céntimo

La entrega a cada cliente tiene un beneficio distinto, que puede reducirse al aplicar una penalización por el retraso en la recepción del paquete: el repartidor tarda un minuto en recorrer cada kilómetro, y cada cliente penaliza con un céntimo/min de retraso (a contar desde las 8am).

## Datos

$n$: entero, número de vértices
$E$: aristas del grafo
$a$: vértice origen, a en [0,n).
$w(i,j)$: double, peso de la arista $(i,j)$, $i,j \in [0,n)$
$b_i$: double, beneficio del cliente ubicado en el vértice $i$, $i \in [0,n)$
$g$: el grafo del problema


Un modelo lineal donde la variable $x_i$ indica la posición en la que se visita el vértice $i$ y $y_{ij}$ una variable booleana que indica si el camino va desde el vértice $i$ al $j$.

$$ \min \sum\limits_{i=0}^{n-1} b_i -\sum\limits_{i,j=0|(i,j)\in g}^{n-1} (n\ -\ i) y_{ij}\ w(i,j) $$

$$ \sum\limits_{i=0}^{n-1}y_{ij}=1,\ \ j\in\left[0,n-1\right] $$

$$ \sum\limits_{j=0}^{n-1}y_{ij}=1,\ \ i\in\left[0,n-1\right] $$

$$ x_i-x_j+n\ y_{ij}\le n-1,\ \ i\in\left[1,n-1\right],j\in\left[1,n-1\right]|(i,j)\in g $$

$$ x_i\le n-1,\ \ \ i\in\left[0,n-1\right] $$

$$x_0=a $$

$$ bin\ y_{ij},\ \ i\in\left[0,n-1\right],j\in\left[0,n-1\right]|(i,j)\in g $$

$$ int\ x_i,\ \ i\in[0,n) $4
