# Tareas solapadas

Se tienen $n$ tareas y cada una está representada por los siguientes 3 elementos: hora de inicio, duración, ganancia asociada. Encontrar el subconjunto de tareas que sin solaparse proporcionen la ganancia máxima.

## Datos

 - n: número de tareas
 - las tareas se mantienen ordenadas por $s_i$.
 -  $sp\left(i,j\right)=\max (s_i+d_i-s_j,0)$. Solapamiento entre las tareas $i,j$. Compartida. 

### Tarea

- $s_i$: inicio de la tarea $i$
- $d_i$: duración de la tarea $i$
- $g_i$: ganancia de la tarea $i$

## Modelo

$$ \max \sum_{i=0}^{n-1} g_i x_i $$

$$ x_i +x_j\le 1,\ i,j \in \left[0,n-1\right] \ |\ i \lt j, \ sp\left(i,j\right) \gt 0 $$

$$ bin\ \ \ x_i,\ \ \ \ \ \ i\in\left[0,n-1\right] $$

## Grafo

### Propiedades del vértice

 - $index$: Integer, básica
 - $te$: IntegerSet, básica, tareas ya escogidas


	
