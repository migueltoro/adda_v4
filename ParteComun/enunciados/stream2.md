# Stream2

Contiene un conjunto de métodos que amplía la funcionalidad de Stream\<E\>.

- Stream\<T\> zip(Stream\<L\> s1, Stream\<R\> s2, BiFunction\<L, R, T\> combiner): Construye nuevo stream combinando cada elemento de s1 con otro de s2 mediante la función combiner.
- Stream\<Enumerate\<E\>\> enumerate(Stream\<E\> stream): Construye nuevo stream de pares entero-elemento siendo el entero la posición en el stream del elemento.
- Stream\<Pair\<R,U\>\> cartesianProduct(Stream\<T\> s1, Stream\<U\> s2): Construye nuevo stream con los pares de elementos que se obtienen combinando cada valor de s1 con todos los de s2.
- Stream\<Pair\<E,E\>\> cartesianProduct(Stream\<E\> s1): Construye nuevo stream con los pares de elementos posibles que se obtienen combinando cada valor de s1 con todos los de s1 incluido él mismo.
- Stream\<Pair\<E,E\>\> consecutivePairs(Stream\<E\> sm): Construye nuevo stream con los pares de elementos posibles que se obtienen combinando cada valor de s1 con el siguiente
- Stream\<IntPair\> allPairs(Integer n, Integer m): Construye nuevo stream con pares de enteros [0,n) y el segundo en [0,m).
- Stream\<IntPair\> allPairs(Integer n1, Integer n2, Integer m1, Integer m2): Construye nuevo stream con pares de enteros [n1,n2) y el segundo en [m1,m2).
- Stream\<Pair\<T,U\>\> join(Stream\<T\> s1, Stream\<U\> s2, Function\<T,K\> k1, Function\<U,K\> k2):  Construye nuevo stream con pares de elementos de s1 y s2 que tengan la misma clave calculada con k1 y k2 respectivamente.
- elementRandom(Stream\<T\> st): obtiene un elemento aleatoriamente del stream
- Stream\<String\> split(String s, String delim): obtiene un stream con las partes de s separadas por el delimitador delim.
