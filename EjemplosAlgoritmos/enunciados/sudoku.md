
# Sudoku

El problema consiste en rellenar con los enteros $[1..n]$ las casillas de un tablero $n\times n$ de tal manera que cada fila, cada columna y cada subtabla tenga todos los enteros $[1..n]$  y una sola vez cada uno de ellos. Asumiendo subtablas cuadradas de lado $k,\ \ (n=k^2)$, a cada casilla le podemos asignar las coordenadas $i,j$ que toman valores en $0..n-1$, siendo la casilla $(0,0)$ la inferior izquierda. A cada subtabla le asociamos el índice $t$ que toma valores en $0..n-1$. 
Tenemos $t=\frac{4}{3}3+\frac{2}{3}=3$. Concretemos el problema para n=9. Cada casilla tiene también asociado un entero $p: 0..n^2-1$ que indica su posición en una lista de casillas tomadas por filas de abajo arriba. Por último cada casilla tiene la propiedad $df$ que indica si su valor está definido al principio y el valor $v$ fijado para esa casilla si lo tiene. 

### Casilla

- $i$, columna de celda
- $j$, fila de celda
- $t$: la subtabla de la celda
- $p$: la posición de la celda
- $df$: _Boolean_, si la casilla está definida
- $v$: su valor asociado si la casilla está definida

Invariante

- $t = \frac{j}{k}k+i/k$
- $i=\frac{p}{n},\ j=p\%n$

## Datos

- $n$: número de casillas
- $k$: tamaño de la subtabla, $n = k*k$
- $\phi(t) = \{(i,j)|t=\frac{j}{k}k+i/k\}$: El conjunto de casillas de la subtabla $t$.

## Modelo Lineal

Las variables $x_{ijv}$ indican el valor de la casilla $ij$  es $v$.

$$\sum_{i=0}^{n-1} x_{ijv}\ =\ 1,\ j\in [0,n-1],\ v\in [1,n]$$

$$\sum_{j=0}^{n-1} x_{ijv}\ =\  1,\ i\in [0,n-1],\ v\in [1,n]$$

$$\sum_{(i,j):\phi(t)}x_{ijv} \  =\  1,\ t=\in [0,n-1],\ v\in [1,n]$$

$$ \sum_{v=1}^{n} x_{ijv} \ = \ 1, \ i \in [0,n-1],j\in [0,n-1]$$

$$bin\ x_{ijv},\ \ \ \ \ \ i,j \in [0,n-1],\ v\in [1,n]$$


## Modelo

Las variables $x_{ij}$ indican el valor de la casilla $ij$.

$$P_{i=0}^{n-1} \ (x_{ij},i+1), \ j\in[0,n-1]$$

$$AD_{j=0}^{n-1}\ x_{ij},\ i\in[0,n-1]$$

$$AD_{(i,j)|φ(t)} x_{ij}, \ t\in[0,n-1]$$

$$int \ x_{ij},      i,j\in[0,n-1]$$

## Vértice del grafo (Sudoku)

### Propiedades

- $index$: derivada, es una posición en la lista de índice de índices, el primer valor es el de la primera casilla no definida en $indices$.
- $lc$: básica, _List\<Casilla\>_, lista de casillas
- $c(d)$: casilla cuyo índice es $d$
- $voc(i)$: IntegerSet, valores ocupados en la columna $i$
- $vof(j)$: IntegerSet, valores ocupados en la fila $j$
- $vot(t)$: IntegerSet, valores ocupados en la subtabla $t$
- $vlc(d)$: _IntegerSet_, valores libres en la casilla $c(d)$. Este valor es el conjunto vacío si la casilla está definida y si no lo está:

	$$vlc(d)= [1,n]-(voc(c(d).i)\cup vof(c(d).j) \cup vot(c(d).t)$$
	
- $err$: Número de errores, derivada

$$ err =\sum_{i=0}^{n-1} (n-voc(i)) + \sum_{j=0}^{n-1} (n-vof(j)) +\sum_{t=0}^{n-1} (n-vot(i))$$

Invariante

 - La lista de casillas siempre se mantiene ordenada de menor a mayor según el valor de $|vl(d)|$. El rango de casillas en $[0,index)$ ya están definidas y por lo tanto tienen un valor asignado. Las casillas con un solo valor posible se les da ese valor.





