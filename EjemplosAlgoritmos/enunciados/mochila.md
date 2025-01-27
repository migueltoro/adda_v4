# Mochila

El problema de la Mochila parte de una lista de objetos L de tamaño. Cada objeto $ob_i$ de la lista es de la forma $ob_i= (w_i, v_i, m_i)$ dónde  $w_i, v_i, m_i$ son, respectivamente, su peso, su valor unitario y el número de unidades disponibles. La mochila tiene una capacidad $C$.  El problema busca ubicar en la mochila el máximo número unidades de cada objeto, teniendo en cuenta las disponibles, que quepan en la mochila para que el valor de estos sea máximo. 

## Datos

- $w_i$: peso unitario del objeto $i$
- $v_i$: valor unitario del objeto $i$
- $m_i$: número de unidades disponibles del objeto $i$
- $C$: capacidad de la mochila
- $n$: número de objetos

## Modelo

Si $x_i$ es el número de unidades del objeto $i$ en la mochila el problema puede modelarse como:

$$ \max \sum\limits_{i=0}^{n-1} x_i v_i $$

$$ \sum\limits_{i=0}^{n-1} x_i w_i \le C $$

$$ x_i\le m_i,\ \ \ \ i\in\left[0,n-1\right] $$

$$ int\ \ \ x_i,\ \ \ \ \ i\in\left[0,n-1\right] $$

## Grafo

### Propiedades del vértice

 - $i$: Integer, básica
 - $pr$: Integer, peso restante, básica
