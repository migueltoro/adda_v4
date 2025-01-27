# Collectors2

Es una factoría de Collectors adicional a la ya existente en Java. Se usa en la forma: 
	stream.collect(Collector2.metodo()).

- toMultiset(): El resultado es un Multiset
- toIntegerSet(): El resultado es un IntegerSet
- groupingList(Function\<E,K\> key, Function\<E,R\> value): 
	El resultado es de tipo Map\<K,List\<R\>\>. 
	Forma grupos a partir de la clave proporcionada por key y luego transforma los elementos de cada grupo 
	con la función value.
- groupingList(Function\<E,K\> key, Function\<E,R\> value, Function\<List\<R\>,S\> rs): 
	El resultado es de tipo Map\<K,S\>.
	Forma grupos a partir de la clave proporcionada por _key_ y luego transforma los elementos 
	de cada grupo con la función _value_. 
	Finalmente aplica a cada grupo la función _rs_.
- groupingSet(Function\<E,K\> key, Function\<E,R\> value): 
	El resultado es de tipo Map\<K,Set\<R\>\>. 
	Forma grupos a partir de la clave proporcionada por key y luego transforma los elementos 
	de cada grupo con la función _value_.
- groupingSet(Function\<E,K\> key, Function\<E,R\> value, Function\<Set\<R\>,S\> rs): 
	El resultado es de tipo Map\<K,S\>.
	Forma grupos a partir de la clave proporcionada por _key_ y luego transforma los elementos 
	de cada grupo con la función _value_. 
	Finalmente aplica a cada grupo la función _rs_.
- groupingReduce(Function\<E,K\> key, Function\<E,R\> value, BinaryOperator\<R\> rs):
	El resultado es de tipo Map\<K,R\>. 
	Forma grupos a partir de la clave proporcionada por _key_ y luego transforma los elementos 
	de cada grupo con la función _value_. 
	Finalmente aplica a cada grupo la función una reducción con el operador binario _op_.
- groupingSize(Function\<E,K\> key: 
	El resultado es de tipo Map\<K,Integer\>.
	Cuenta los elementos en cada grupo
- groupingSizeDistinct(Function\<E,K\> key, Function\<E,R\> value): 
	El resultado es de tipo Map\<K,Integer\>.
	Cuenta los elementos distintos en cada grupo
- frequencies():
	El resultado es de tipo Multiset.
	Cuenta la frecuencia cada elemento