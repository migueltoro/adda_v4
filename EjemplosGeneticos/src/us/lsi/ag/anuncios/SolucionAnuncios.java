package us.lsi.ag.anuncios;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.anuncios.datos.Anuncio;
import us.lsi.anuncios.datos.DatosAnuncios;
import us.lsi.common.IntPair;

public class SolucionAnuncios {
	
	public static SolucionAnuncios of(List<Integer> indices) {
		return new SolucionAnuncios(indices);
	}

	private List<Integer> indices;
	public Double valor;
	public Integer tiempoConsumido;
	public Integer numAnunciosIncompatibles;
	private Integer pos;
	

	private SolucionAnuncios(List<Integer> indices) {
		super();
		this.indices = indices;
		calculaPropiedadesDerivadas();
	};
	
	public List<Anuncio> anuncios() {
		return this.indices.subList(0, pos).stream().map(i->DatosAnuncios.getAnuncio(i)).collect(Collectors.toList());
	}
	
	private void calculaPropiedadesDerivadas(){			
		this.tiempoConsumido = 0;
		this.valor = 0.;
		this.pos = 0;
		for(int i=0; i< indices.size();i++){
			Anuncio a = DatosAnuncios.getAnuncio(indices.get(i));
			if(this.tiempoConsumido+a.getDuracion() > DatosAnuncios.tiempoTotal) break;
			this.valor = this.valor+a.getPrecio(this.pos+1);
			this.tiempoConsumido=this.tiempoConsumido+a.getDuracion();
			this.pos=this.pos+1;
		}
		this.numAnunciosIncompatibles = 0;	
		List<Integer> ae = this.indices.subList(0,pos);
		for(IntPair p: DatosAnuncios.restricciones){
			if(ae.contains(p.first()) && ae.contains(p.second())){
				this.numAnunciosIncompatibles = this.numAnunciosIncompatibles +1;
			}
		}
	}

	@Override
	public String toString() {
		return String.format("valor = %.2f, tiempoConsumido = %d, tiempoTotal = %d, numAnunciosIncompatibles = %d, anuncios = %s",
				valor,tiempoConsumido,DatosAnuncios.tiempoTotal,numAnunciosIncompatibles,anuncios());
	}
	
	

}
