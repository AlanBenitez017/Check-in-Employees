package Consultas;

public class DatosPersona {

	/*Todo los atributos*/
	int id;
	int idLegajo;
	String momentoMarcacion;
	Object idTipoMarca;
	
	public DatosPersona(){}

	/*Todo los codigos get*/
	public int getId(){
		return id;
	}
	public int getIdLegajo(){
		return idLegajo;
	}
	public String getMomentoMarcacion(){
		return momentoMarcacion;
	}
	public Object getIdTipoMarca(){
		return idTipoMarca;
	}


	/*Todo los codigos set*/
	public void setId(int id){
		this.id = id;
	}
	public void setIdLegajo(int idLegajo){
		this.idLegajo = idLegajo;
	}
	public void setMomentoMarcacion(String momentoMarcacion){
		this.momentoMarcacion = momentoMarcacion;
	}
	public void setIdTipoMarca(Object object){
		this.idTipoMarca = object;
	}

}
