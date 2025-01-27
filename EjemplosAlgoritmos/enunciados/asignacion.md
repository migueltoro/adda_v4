# Asignación

En este problema tenemos una lista de agentes $L$ y una lista de tareas $T$ ambas del mismo tamaño $n$. El coste de que el agente $i$ realice la tarea $j$ sea $c(i,j)$.  Se pretende asignar a cada agente una tarea y sólo una de tal forma que se ejecuten todas las tareas con el coste mínimo. 

## Datos

 - $n$: número de agentes y tareas
 - $c(i,j)$: coste de que el agente $i$ realice la tarea $j$ 

## Primer modelo

$$ \min⁡ \sum\limits_{i=0,j=0}^{n-1} x_{ij}  c(i,j)$$

$$ \sum\limits_{j=0}^{n-1} x_{ij}  = 1,    i \in [0,n-1] $$

$$ \sum\limits_{i=0}^{n-1} x_{ij}  = 1,    j \in[0,n-1] $$

$$ bin \ x_{ij},    i\in [0,n-1],   j \in [0,n-1] $$

## Segundo Modelo

$$ min⁡ \sum\limits_{i=0}^{n-1} c(i,x_i) $$

$$ P_{i=0}^{n-1} (x_i,i) $$

$$ int \ x_i,    i \in [0,n-1]  $$

## Grafo

Construido  a partir del segundo modelo

### Propiedades del vértice

 - $index$: Integer, básica
 - $tna$: IntegerSet, tareas no asignadas todavía, básica






