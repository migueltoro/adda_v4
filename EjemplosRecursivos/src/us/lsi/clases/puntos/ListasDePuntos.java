package us.lsi.clases.puntos;

import java.util.*;

import us.lsi.common.Comparator2;
import us.lsi.common.List2;
import us.lsi.common.Preconditions;
import us.lsi.common.Set2;
import us.lsi.geometria.ParDePuntos;
import us.lsi.geometria.Punto2D;
import us.lsi.math.Math2;



public class ListasDePuntos {
	
	public static Set<Punto2D> puntosMaximales(List<Punto2D> lista){
		Comparator<Punto2D> ord = new Comparator<Punto2D>(){
			@Override
			public int compare(Punto2D p1, Punto2D p2) {
				return p1.x().compareTo(p2.x());
			}
			
		};
		Collections.sort(lista, ord);
		return  puntosMaximalesGeneralizado(0,lista.size(),lista);
	}
	
	private static Set<Punto2D> puntosMaximalesGeneralizado(int i, int j, List<Punto2D> lista){
		Preconditions.checkArgument(j>=i);
		Set<Punto2D> r;
		Set<Punto2D> ri;
		Set<Punto2D> rd;
		int k;
		if(j-i<=4){
			r = puntosMaximalesBase(i,j,lista);
		}else{
			k = (i+j)/2;
			ri = puntosMaximalesGeneralizado(i,k,lista);
			rd = puntosMaximalesGeneralizado(k,j,lista);
			r = puntosMaximalesCombina(ri,rd);
		}
		return r;
	}
	
	public static Set<Punto2D> puntosMaximalesBase(int a, int b, List<Punto2D> lista){
		Set<Punto2D> r = Set2.of();
		Punto2D pi;
		Punto2D pj;
		boolean piEsDominado;
		for(int i = a; i < b; i++){
			pi = lista.get(i);	
			piEsDominado = false;
			for(int j=a;j<b;j++){				
					pj = lista.get(j);
					if(pj.dominaA(pi)){
						piEsDominado = true;
						break;
					}
			}
			if(!piEsDominado){
				r.add(pi);
			}
		}
		return r;
	}
	
	private static Set<Punto2D> puntosMaximalesCombina(Set<Punto2D> si, Set<Punto2D> sd){
		Double maxYD = Double.MIN_VALUE;
		Set<Punto2D> r = Set2.of(sd);
		for(Punto2D p:sd){
			if(p.y()>maxYD){
				maxYD = p.y();
			}
		}
		for(Punto2D p:si){
			if(p.y()> maxYD){
				r.add(p);
			}
		}
		return r;
	}
	
	public static ParDePuntos parMasCercano(List<Punto2D> lista){
		ParDePuntos p = null;
		List<Punto2D> puntosX;
		List<Punto2D> puntosY;
		Comparator<Punto2D> ordX = new Comparator<Punto2D>(){
			@Override
			public int compare(Punto2D p1, Punto2D p2) {
				return p1.x().compareTo(p2.x());
			}
			
		};
		Comparator<Punto2D> ordY = 	new Comparator<Punto2D>(){
			@Override
			public int compare(Punto2D p1, Punto2D p2) {
				return p1.y().compareTo(p2.y());
			}
			
		};
		Comparator<ParDePuntos> ordNatural =  Comparator.naturalOrder();
		puntosX = Comparator2.sortedCopy(lista,ordX);
		puntosY = Comparator2.sortedCopy(lista,ordY);
		p = masCercano(0,lista.size(),puntosX, puntosY,4,ordNatural);
		return p;
	}
	
	private static ParDePuntos masCercano(int i, int j, List<Punto2D> puntosX, List<Punto2D> puntosY, int umbral, Comparator<ParDePuntos> ordNatural){
		ParDePuntos r = null;
		int k = (i+j)/2;
		if(j-i <= umbral){
			r = parMasCercanoBase(i,j,puntosX);
		}else{
			List<Punto2D> puntosYIzq = List2.empty();
			List<Punto2D> puntosYDer = List2.empty();
			Double xk = puntosX.get(k).x();
			for(Punto2D p:puntosY){
				if(p.x() < xk){
					puntosYIzq.add(p);
				}else{
					puntosYDer.add(p);
				}
			}
			ParDePuntos s1 = masCercano(i,k,puntosX, puntosYIzq,umbral,ordNatural);
			ParDePuntos s2 = masCercano(k,j,puntosX, puntosYDer,umbral,ordNatural);
			r = Comparator2.min(s1, s2,ordNatural);
			List<Punto2D> yCentral = List2.empty();
			for(Punto2D p: puntosY){
				if(Math.abs(p.x()- xk) < r.distancia()){
						yCentral.add(p);
				}
			}
			if(!yCentral.isEmpty()){
				r = masCercanoCentral(r, yCentral, ordNatural);	
			}
		}
		return r;
	}
	
	
	
	private static ParDePuntos masCercanoCentral(ParDePuntos s, List<Punto2D> yCentral, Comparator<ParDePuntos> ordNatural) {	
		ParDePuntos r = null;
		double d = s.distancia();
		Punto2D pIzq = null;
		Punto2D pDer = null;
		for(int i=0; i < yCentral.size(); i++){
			pIzq = yCentral.get(i);
			for(int j=i+1;j<yCentral.size();j++){
				pDer = yCentral.get(j);
				r = ParDePuntos.create(pIzq, pDer);
				if(r.distancia()>d){
					break;
				}
				s = Comparator2.min(s, r,ordNatural) ;
				d = s.distancia();
			}
			for(int j=i-1;j>=0;j--){
				pDer = yCentral.get(j);
				r = ParDePuntos.create(pIzq, pDer);
				if(r.distancia()>d){
					break;
				}
				s = Comparator2.min(s, r,ordNatural) ;
				d = s.distancia();
			}
		}
		return s;
	}

	public static ParDePuntos parMasCercanoBase(int a, int b, List<Punto2D> lista){
		ParDePuntos p = null;
		Double r = Double.MAX_VALUE;
		Punto2D p1;
		Punto2D p2;
		Double d;
		for(int i = a; i < b; i++){
			for(int j = i+1; j < b; j++){
				p1 = lista.get(i);
				p2 = lista.get(j);
				d = p1.distanciaA(p2);
				if(d < r ){
					r = d;
					p = ParDePuntos.create(p1, p2);
				}
			}
		}
		return p;
	}
	
	public static List<Punto2D> getListaPuntosAleatoria(int n){
		List<Punto2D> r = List2.empty();
		for(int i=0; i < n; i++){
			r.add(Punto2D.of(Math2.getDoubleAleatorio(-1000., 1000.),Math2.getDoubleAleatorio(-1000., 1000.)));
		}
		return r;
	}
	
}
