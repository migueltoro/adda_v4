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
$b_i$: double, beneficio del cliente ubicado en el vértice $v_i$, $i \in [0,n)$

## Un primer modelo

Si $g$ es el grafo del problema entonces un modelo es:

$$
\begin{array} {c}
\min \sum\limits_{i=0}^{n-1} b_i -\sum\limits_{i=0}^{n-1} (n\ -\ i)\ w(x_i,x_{(i+1)\%n}) \\
P_{i=0}^{n-1}(x_i,i) \\
x_0=a \\
CP_{i=0|g}^{n-1}x_i \\
x_i<n,\ i\in[0,n) \\
int\ x_i,\ i\in[0,n)
\end{array} 
$$

Podemos usar un cromosoma de permutación de tamaño $n$ y secuencia normal la lista $[0,1,…,n-1]$. Pero hay que añadir la restricción $x_0=a$.

Un modelo ligeramente retocado es


$$
\begin{array} {c}
\min \sum_{i=0}^{n-1}b_i -\sum_{i=0}^{n-1} (n\ -\ i)\ w(x_i,x_{(i+1)\%n})} \\
P(L_{i=1}^{n-1}x_i,L_{i=0|i!=a}^{n-1}i) \\
CP_{i=0|g}^{n-1}x_i \\
x_i<n,\ i\in[0,n) \\
int\ x_i,\ i\in[0,n)
\end{array} 
$$

Podemos usar un cromosoma de permutación de tamaño $n-1$ y secuencia normal la lista $[0,1,…,n-1]-a$. Ya está incluida la restricción $x_0=a$.


## Un segundo modelo

Ahora formulamos un modelo lineal donde la variable $x_i$ indica la posición en la que se visita el vértice $i$ y $y_{ij}$ una variable booleana que indica si el camino va desde el vértice $i$ al $j$.

$$
\begin{array} {c}
\min \sum\limits_{i=0}^{n-1} b_i -\sum\limits_{i,j=0|(i,j)\in g}^{n-1} (n\ -\ i) y_{ij}\ w(i,j) \\
\sum\limits_{i=0}^{n-1}y_{ij}=1,\ \ j\in\left[0,n-1\right] \\
\sum\limits_{j=0}^{n-1}y_{ij}=1,\ \ i\in\left[0,n-1\right] \\
x_i-x_j+n\ y_{ij}\le n-1,\ \ i\in\left[1,n-1\right],j\in\left[1,n-1\right]|(i,j)\in g \\
x_i\le n-1,\ \ \ i\in\left[0,n-1\right] \\
x_0=a \\
bin\ y_{ij},\ \ i\in\left[0,n-1\right],j\in\left[0,n-1\right]|(i,j)\in g \\
int\ x_i,\ \ i\in[0,n)
\end{array} 
$$