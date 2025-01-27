# Investigadores

Existe un grupo de $n$ investigadores para realizar un conjunto de $m$ trabajos durante este curso. De cada investigador se conoce su especialidad (cada especialidad se identifica por un entero), y la cantidad de días disponibles que tiene para avanzar en los trabajos. De cada trabajo se conoce cuántos días de cada especialidad de investigador son necesarios para llevarlo a cabo (por ejemplo 3 días de investigador de especialidad 3 y 2 de especialidad 5), y su calidad (entero en [5,10]). Teniendo en cuenta los días disponibles de los investigadores, puede que no sea posible realizar todos los trabajos, por lo que hay que decidir cuáles llevar a cabo. Se desea conocer cuántos días dedicará cada investigador a cada trabajo de forma que se maximice la suma total de las calidades de los trabajos llevados a cabo.

## Datos

 - $n$: entero, número de investigadores
 - $r:$ entero, número de especialidades
 - $m$: entero, número de trabajos
 - $e_{ik}$: boolean, trabajador $i$ tiene especialidad $k$, $i \in [0,n), k \in [0,r)$
 - $dd_i$: entero, días disponibles del trabajador $i$, $i \in [0,n)$
 - $dn_{jk}$: entero, días necesarios para el trabajo $j$ de investigador con especialidad $k$ con $j \in [0,m), k \in [0,r)$
 - $c_j$: entero, calidad trabajo $j$, $j \in [0,m) $  

## Primer modelo

Las variables $x_{ij}$ que indican la cantidad de horas del trabajador $i$ en en el trabajo $j$ e $y_j$ si el trabajo $j$ se hace o no.

 $$ \max \sum\limits_{j=0}^{m-1} c_j y_j $$
 
 $$ \sum\limits_{j=0}^{m-1} x_{ij} \le {dd}_i, i \in [0,n) $$
 
 $$ \sum\limits_{i=0|e_{ik}}^{n-1} x_{ij} =  y_j\ {dn}_{jk}, j \in [0,m), k \in [0,r) $$
 
 $$ int \ x_{ij}, \ bin\ y_j, i\in[0,n),j \in[0,m) $$


## Segundo modelo

Las variables $x_{ij}$ que indican la cantidad de horas del trabajador $i$ en en el trabajo $j$. Un trabajo se hace si alguna  $x_{ij} \gt 0$ por lo que podemos hacer que las $y_j$ sean derivadas (es decir calculadas a partir de las $x_{ij}$.


$$ \max \sum\limits_{j=0}^{m-1} c_j y_j $$

$$ \sum\limits_{j=0}^{m-1} x_{ij} \le {dd}_i, i\in[0,n) $$

$$ \sum\limits_{i=0|e_{ik}}^{n-1} x_{ij} =  y_j {dn}_{jk}, j \in[0,m),k \in[0,r) $$

$$ y_j= {\exists_{i=0}^n x_{ij} \gt 0} ? 1:0, j \in[0,m) $$

$$ int\ x_{ij},\ i\in[0,n) $$


## Tercer modelo

Eliminado las $y_j$

$$ \max\sum_{j=0|{\exists_{i=0}^nx}_{ij}>0}^{m-1}c_j $$

$$ \sum\limits_{j=0}^{m-1}{x_{ij}\le{dd}_i},\ \ i\in[0,n) $$

$$ \sum\limits_{i=0|e_{ik}}^{n-1}x_{ij}={dn}_{jk},\ \ j \in [0,m), k\ \in[0,r) $$

$$ # Investigadores

Existe un grupo de $n$ investigadores para realizar un conjunto de $m$ trabajos durante este curso. De cada investigador se conoce su especialidad (cada especialidad se identifica por un entero), y la cantidad de días disponibles que tiene para avanzar en los trabajos. De cada trabajo se conoce cuántos días de cada especialidad de investigador son necesarios para llevarlo a cabo (por ejemplo 3 días de investigador de especialidad 3 y 2 de especialidad 5), y su calidad (entero en [5,10]). Teniendo en cuenta los días disponibles de los investigadores, puede que no sea posible realizar todos los trabajos, por lo que hay que decidir cuáles llevar a cabo. Se desea conocer cuántos días dedicará cada investigador a cada trabajo de forma que se maximice la suma total de las calidades de los trabajos llevados a cabo.

## Datos

 - $n$: entero, número de investigadores
 - $r:$ entero, número de especialidades
 - $m$: entero, número de trabajos
 - $e_{ik}$: boolean, trabajador $i$ tiene especialidad $k$, $i \in [0,n), k \in [0,r)$
 - $dd_i$: entero, días disponibles del trabajador $i$, $i \in [0,n)$
 - $dn_{jk}$: entero, días necesarios para el trabajo $j$ de investigador con especialidad $k$ con $j \in [0,m), k \in [0,r)$
 - $c_j$: entero, calidad trabajo $j$, $j \in [0,m) $  

## Primer modelo

Las variables $x_{ij}$ que indican la cantidad de horas del trabajador $i$ en en el trabajo $j$ e $y_j$ si el trabajo $j$ se hace o no.

 $$ \max \sum\limits_{j=0}^{m-1} c_j y_j $$
 
 $$ \sum\limits_{j=0}^{m-1} x_{ij} \le {dd}_i, i \in [0,n) $$
 
 $$ \sum\limits_{i=0|e_{ik}}^{n-1} x_{ij} =  y_j\ {dn}_{jk}, j \in [0,m), k \in [0,r) $$
 
 $$ int \ x_{ij}, \ bin\ y_j, i\in[0,n),j \in[0,m) $$


## Segundo modelo

Las variables $x_{ij}$ que indican la cantidad de horas del trabajador $i$ en en el trabajo $j$. Un trabajo se hace si alguna  $x_{ij} \gt 0$ por lo que podemos hacer que las $y_j$ sean derivadas (es decir calculadas a partir de las $x_{ij}$.


$$ \max \sum\limits_{j=0}^{m-1} c_j y_j $$

$$ \sum\limits_{j=0}^{m-1} x_{ij} \le {dd}_i, i\in[0,n) $$

$$ \sum\limits_{i=0|e_{ik}}^{n-1} x_{ij} =  y_j {dn}_{jk}, j \in[0,m),k \in[0,r) $$

$$ y_j= {\exists_{i=0}^n x_{ij} \gt 0} ? 1:0, j \in[0,m) $$

$$ int\ x_{ij},\ i\in[0,n) $$


## Tercer modelo

Eliminando las $y_j$

$$ \max\sum_{j=0|\phi(j)}^{m-1}c_j $$

$$ \sum\limits_{j=0}^{m-1}{x_{ij} \le {dd}_i},\ \ i\in[0,n) $$

$$ \sum\limits_{i=0|e_{ik}}^{n-1}x_{ij}={dn}_{jk},\ \ j \in [0,m) | \phi(j), k\ \in[0,r) $$

$$ int\ x_{ij},\ \ i\in[0,n),j\ \in[0,m) $$

$$ \phi(j) \equiv \exists_{i=0}^n x_{ij} \gt 0 $$

## Grafo obtenido del tercer modelo

### Vértice: Propiedades


 - $index$: índice que recorre los pares $(i,j)$ desde 0 hasta $n*m$, básica
 - $i$= $index\%n$, derivada
 - $j$= $index/n$, derivada
 - $k$ = $e_i$, derivada
 - $p$ = $(j,k)$, derivada
 - $dIr(i)$: días restantes de cada trabajador $i$
 - $tA(j)$: true si el trabajo $j$ está acabado
 - $dEr(j,k)$: días que faltan dedicar al trabajo $j$ de la especialidad $k$
 



