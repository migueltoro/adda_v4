# Robots

Se tienen $n=4$ tipos de materiales $(A, B, C, D)$. Para cada uno, existe un robot que lo puede generar, pero fabricar ese robot tiene un coste en materiales. Cuando se fabrica un robot, éste produce una unidad de su material por minuto indefinidamente. Se empieza con 1 robot de A y 0 unidades de todos los materiales.
Al principio de cada minuto (estado) se puede decidir si se construye un robot de algún tipo (siempre que se tengan los materiales necesarios) o no. El robot construido empieza a producir en el minuto siguiente, y en caso de que se construya, los materiales necesarios para su construcción se restan de los existentes en el estado. También se puede decidir no construir nada en ese minuto.
A continuación, se actualizan los materiales añadiendo lo que han fabricado los robots existentes. Este orden es importante, ya que la decisión de construir o no un robot en un estado se toma antes de que se actualicen los materiales.
El objetivo es saber cual es la cantidad máxima de material de tipo $D$, $j=3$ que se pueden fabricar tras $T= 24$ minutos.

## Datos

- $n$: número de robots
-  Sea $mt(i,j), \ i,j \in[0,n)$ la cantidad de material $j$ necesaria para construir un robots de tipo $i$. Usaremos la notación $mt(i)$ para indicar el material necesario para construir el robots $i$. Ej: 

$$ mt = [[4,0,0,0],[2,0,0,0],[3,14,0,0],[3,0,7,0]] $$

## Modelo


## Ejemplo

Diseñamos un grafo cuyos vértices son del tipo $Vr$  y cuyas aristas son de tipo $Ar$:

### Tipo Vr: Propiedades

- $r(i)$: List\<Integer\>, número de robots de tipo $i$, básica, $\forall_{i=0}^{n-1} r(i) \geq 0$.
- $m(i)$: List\<Integer\>, cantidad de material de tipo $i$, básica, $\forall_{i=0}^{n-1} m(i) \geq 0$$.
- $t$: Integer, tiempo transcurrido, básica, $0 \le t \le T$

## Tipo Ar

Es un tipo enumerado que toma los valores $Ar = \{-1,0,..n-1\}$.  La acción $a$ significa crear un robot de tipo $a$  si $a \ge 0$ y $-1$ si no se crea ningún robots. Para que se pueda crear un robots debe existir material suficiente . 

## Modelo

$$max \ (x_T).m(3) $$

$$x_0=([1,0,0,0],[0,0,0,0])$$

$$OP_{t=0|g}^T x_t$$

$$ Vr \ \ x_t,\ t∈[0,T]$$
