## DataText

- Es un agregado de datos tabular inmutable que se compone filas y columnas _similar_ a una tabla en un archivo de Excel. Se leen de un fichero _csv_ con datos separados por comas u otro delimitador especificado. Un fichero de ejemplo:
```
Roberto,Alfaro,70,1.78,28/01/2005
Laura,Bustamante,57,1.6,03/04/2006
Erick,Estrada,65,1.74,13/02/2004
Maria,Dominez,52,1.49,01/02/2003
Jorge,Jimenez,60,1.7,04/04/2004
Rosa,Ortiz,56,1.53,09/11/2007
```

El fichero se puede leer mediante Excel y lo veríamos de una forma similar.

Atributos

- _List\<List\<String\>\> datos_

Propiedades

- _String get(Integer f, Integer c)_
- _Integer rn()_
- _Integer cn()_

Métodos de factoría

- _DataText subDataText(Integer f0, Integer c0, Integer nf, Integer nc)__
- _DataText of(String file, Integer nf, Integer nc)_

La representación como tabla de este _DataText_ sería de la forma.

|<!-- -->| <!-- -->| <!-- -->| <!-- -->| <!-- -->|
|-------:| --------:| ----:| ------:|------:|
| Roberto| Alfaro   |   70 |   1.78 | 28/01/2005 |
| Laura  | Bustamante | 57 | 1.6 | 03/04/2006 |
| Erick  | Estrada | 65 | 1.74 | 13/02/2004 |
| Maria  | Domínez | 52 | 1.49 | 01/02/2003 |
| Jorge  | Jimenez | 60 | 1.7 | 04/04/2004 |
| Rosa   | Ortiz | 56 | 1.53 | 09/11/2007 |


Igualdad: dos _DataText_ son iguales cuando tienen todas su casillas iguales

## DataFrame

- Es un caso particular de _DataText_ (es decir que lo extiende) que define un conjunto de valores de un tipo.  Tiene un titulo, una cabecera con nombres para cada una de las columnas y un identificador para cada fila. Los nombres en columnas y filas puede servir como índices para acceder a las filas, columnas y casillas. Los nombres en columnas deben ser distintos. Los nombres en las filas deben ser distintos también. Un ejemplo es:

|Alumnos | Nombre | Apellido | Peso | Altura | FNacimiento |
|-------:|-------:| --------:| ----:| ------:|------:|
| A1     | Roberto| Alfaro   |   70 |   1.78 | 28/01/2005 |
| A2     | Laura  | Bustamante | 57 | 1.6 | 03/04/2006 |
| A3     | Erick  | Estrada | 65 | 1.74 | 13/02/2004 |
| A4     | Maria  | Domínez | 52 | 1.49 | 01/02/2003 |
| A5     | Jorge  | Jimenez | 60 | 1.7 | 04/04/2004 |
| A6     | Rosa   | Ortiz | 56 | 1.53 | 09/11/2007 |

- En el ejemplo de arriba la casilla (A3,Apellido) tiene valor estrada. La misma casilla también sería la (2,1). La casilla (0,0) tiene valor Roberto. El mismo ejemplo tiene 6 filas y 5 columnas. 

Propiedades

- Integer rn()
- Integer cn()
- String title()
- Integer rowId(String name)
- String rowName(Integer f)
- List\<String\> rowNames()
- E rowValue(String rowName, Function\<List\<String\>,E\> t): datos de una fila agregados en un valor del tipo E después de transformarlos mediante una función f
- E rowValue(Integer rowId, Function\<List\<String\>,E\> t): datos de una fila agregados en un valor del tipo E después de transformarlos mediante una función
- List\<E\> allRowValues(Function\<List\<String\>,E\> t)
- Integer columnId(String name)
- String columnName(Integer c)
- List\<String\> columnNames()
- List\<E\> columnValues(Integer c, Function\<String,E\> t): los datos de una columna después de transformarlos mediante una función t
- List\<E\> columnValues(String columnName, Function\<String,E\> t): los datos de una columna después de transformarlos mediante una función 
- String get(String row, String column)

Métodos de factoría

- DataFrame of(DataText d)

Invariante

- Se debe comprobar que los nombres de las filas son distintos y los nombres de las columnas también

## DataRel

- Es un caso particular de _DataText_ que define un conjunto de pares de valores con, posiblemente propiedades adicionales.  Sus dos primeras columnas contienen índices a valores de dos tipos previamente definidos en un _DataFrame_.
- En el ejemplo siguiente se supone que disponemos previamente de e dos DataFrame que definen las poblaciones de alumnos (A1, A2, ... ) y productos (P1,P2, ... ). El DataRel indica el número de productos de cada tipo que tiene cada alumno.

|<!-- -->| <!-- -->| <!-- -->| 
|-------:| --------:| ----:| 
| A1| P1   |   70 | 
| A1  | P2 | 57 | 
| A1  | P3 | 32 | 
| A2  | P1 | 52 | 
| A2  | P3 | 60 | 
| A3   | P1 | 56 | 

Propiedades

- Boolean contains(String n1, String n2)
- Boolean contains(Integer n1, Integer n2)
- List\<String\> values(String n1, String n2)
- E value(String n1, String n2, Function<String,E> f)
- E value(String n1, String n2, Integer index, Function<String,E> f)
- Set<Pair<String,String>> pairs()


Métodos de factoría:

DataRel of(DataText d, List\<String\> rowNames1, List\<String\> rowNames2)