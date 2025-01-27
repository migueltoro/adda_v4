# Extensión de las colecciones: list, set, map, ...

## List2

Este tipo ofrece algunos métodos adicionales a List de uso frecuente:

- **parse(String[] tokens, Function\<String,E\> f_map)**:  Hace parsing de una lista a partir de un array de String
- **parse(String s, String sep, Function\<String,E\> f_map)**: Hace parsing de una lista a partir de un String y unos separadores
- **Métodos funcionales**: first(ls), last(ls), addLast(ls, e), addFirst(ls,e), removeLast(ls), removeFirst(ls), setElement(ls,index,e).
- **Conversión a array**: toArray(ls).

## Set2

Este tipo ofrece algunos métodos adicionales a Set de uso frecuente:

- **parse(String[] tokens, Function\<String,E\> f_map)**:  Hace parsing de una lista a partir de un array de String
- **parse(String s, String sep, Function\<String,E\> f_map)**: Hace parsing de una lista a partir de un String y unos separadores
- **Métodos funcionales**: 
	- add(ls, e)
	- remove(ls,e)
	- difference(Collection\<E\> s1, Collection\<E\> s2)
	- union(Collection\<E\> s1, Collection\<E\> s2)
	- intersection(Collection\<E\> s1, Collection\<E\> s2)
- **Métodos de factoría**: 
	- of(E... e)
	- copy(Collection\<T\> c)
	- range(Integer a, Integer b, Integer c)
	- range(Integer a, Integer b)

## Map2

- **Métodos de factoría**
	- Map\<K, V\> of(Function\<K,V\> f): Un map cuyos pares clave-valor son definidos por la función. Algunos de los métodos del map no son soportados. 
	- of(Entry\<? extends K, ? extends V\>... entries): Un map obtenido a partir de los pares indicados

- **Métodos funcionales**
	- put(Map\<K,V\> m, K key,V value)
	- Map\<K,R\> of(Map\<K,V\> m, Function\<V,R\> f): Un nueo map con os valores cambiados por los obtenidos al aplicarle al función.
	- \<K,V\> Map\<V,K\> reverseHashMap(Map\<K,V\> m): Un map inverso asumiendo que los valores en todos distintos

# Otros tipos agregados:

## IntegerSet: 
- Es un conjunto de enteros en un rango implementado mediante un array de bits. Implementa el la interface Set\<E\>. Tiene la ventaja de ofrecer las operaciones de unión, intersección y diferencia de conjuntos en tiempo constante.
- Hay disponible en Collectors2 el método toIntegerSet para ser usado sobre un stream de la forma collect(Collectors2.toIntegerSet()).

## Multiset\<E\>:

- Es un conjunto donde se pueden repetir los elementos. Mantiene para cada elemento su multiplicidad. Es equivalente a Map\<E,Integer\>.
- Hay disponible en Collectors2 el método toMultiset() para ser usado sobre un stream de la forma collect(Collectors2.toMultiset()).

## BiMap\<K,V\>

- Es un Map\<K,V\> que puede ser invertido. Es decir obtener a partir del él otro Map\<V,K\>. ESto es posible cuando los valores son todos diferentes.

## ListMultimap\<K,V\>

- Es equivalente a Map\<E,List\<V\>\>

## SetMultimap\<K,V\>

- Es equivalente a Map\<E,Set\<V\>\>

## Matrix\<E\>

- Una **matriz** es un agregado bidimensional y mutable de elementos con _nf_ filas y _nc_ columnas. Las filas y columnas comienzan en cero y la casilla cero, cero está en la parte superuor izquierda.
- De una matriz podemos obtener una vista con el método Matrix\<E\> view(Integer fMin, Integer cMin, Integer nf, Integer nc)
- De una matriz podemos obtener una submatriz con el método Matrix\<E\> view(Integer fMin, Integer cMin, Integer nf, Integer nc)
- Tiene el método Matrix\<R\> map(Function\<E,R\> ft) para obtener otra matriz formada por los elementos obtenidos aplicando ala función ft.
- Si los elementos del tipo E forman un campo se dispone de los métodos
	- \<E extends FieldElement\<E\>\> Matrix\<E\> add(Matrix\<E\> m1, Matrix\<E\> m2)
	- \<E extends FieldElement\<E\>\> Matrix\<E\> multiply(Matrix\<E\> in1, Matrix\<E\> in2)
	- \<E extends FieldElement\<E\>\> Matrix\<E\> pow(Matrix\<E\> m, Integer n)

## PairsSet\<A, B\>

- Es un conjunto de pares de enteros con métodos adicionales
- Map\<A,List\<B\>\> asMap1(): produce un map tomando como clave el primer elemento el par
- Map\<B,List\<A\>\> asMap2(): produce un map tomando como clave el segundo elemento el par
- Map\<A,Set\<B\>\> asMapSet1(): produce un map tomando como clave el primer elemento el par
- Map\<B,Set\<A\>\> asMapSet2(): produce un map tomando como clave el segundo elemento el par





	





 

