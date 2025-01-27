# Productos y Precios

Se tienen $n$ productos, cada uno de los cuales tiene un precio y presenta una serie de funcionalidades (el mismo producto puede tener más de una funcionalidad). Se desea diseñar un lote con una selección de dichos productos que cubran un conjunto de funcionalidades deseadas entre todos productos seleccionados al menor precio.
Sea un conjunto finito de funcionalidades $F$ que podemos representar por una lista de tamaño $q$ de funcionalidades y un conjunto de índices $D= [d_0,d_1, \ldots,\ d_{m-1}]$ de tamaño $m$ que indique los índices a las funcionalidades deseadas.

## Datos

- $D$: _IntegerSet_, funcionalidades deseadas
- $p_i$: _Double_, el precio del producto $i$. 
- $f_i$: _IntegerSet_, las funcionalidades ofrecidas  por el producto $i$. 
- $\phi(j)= \{i:0..n-1\ | \ j \in \ f_i\}$ . Esta función devuelve un conjunto que contiene los índices de los productos que ofrecen la funcionalidad $j$. 
- $n$: número de productos
- $m$: número de funcionalidades

## Modelo

Sean las variables binarias $x_i,\ i \in [0,n-1]$ que indica si se ha elegido el producto $i$. 
Un modelo del problema es

$$\min{\sum_{i=0}^{n-1}{p_ix_i}}$$

$$\sum_{i:\phi(j)} x_i \geq 1,\ j\in [0,m) $$

$$ bin\ x_i,\ i\in [0,n)$$

 
Otro modelo del problema es

## Modelo

$$ \min \sum_{i=0}^{n-1} p_i\ x_i $$

$$\cup_{i=0|x_i=1}^{n-1} f_i \supseteq D$$

$$bin\ x_i,\ i\in [0,n-1]$$
