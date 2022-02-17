package POJOS;

import java.util.List;
import java.util.Objects;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Coche {
	private ObjectId id;
	
	@BsonProperty(value = "idCoche")
	private int idCoche;
	@BsonProperty(value = "Matricula")
	private String Matricula;
	@BsonProperty(value = "Marca")
	private String Marca;
	@BsonProperty(value = "Modelo")
	private String Modelo;
	@BsonProperty(value = "Altura")
	private Double Altura;
	@BsonProperty(value = "Anchura")
	private Double Anchura;
	private List<DetallesTecnicos> DetallesTecnicos;
	@BsonProperty(value = "Airbag")
	private Boolean Airbag;

	public ObjectId getId() {
		return id;
	}
	
	public Coche setId(ObjectId id) {
		this.id = id;
		return this;
	}
	
	public int getIdCoche() {
		return idCoche;
	}

	public Coche setIdCoche(int idCoche) {
		this.idCoche = idCoche;
		return this;
	}

	public String getMatricula() {
		return Matricula;
	}
	
	public Coche setMatricula(String Matricula) {
		this.Matricula = Matricula;
		return this;		
	}
	
	public String getMarca() {
		return Marca;
	}
	
	public Coche setMarca(String Marca) {
		this.Marca = Marca;
		return this;
	}
	
	public String getModelo() {
		return Modelo;
	}
	
	public Coche setModelo(String Modelo) {
		this.Modelo = Modelo;
		return this;
	}
	
	public Double getAltura() {
		return Altura;
	}
	
	public Coche setAltura(Double Altura) {
		this.Altura = Altura;
		return this;
	}
	
	public Double getAnchura() {
		return Anchura;
	}
	
	public Coche setAnchura(Double Anchura) {
		this.Anchura = Anchura;
		return this;
	}
	
	public List<DetallesTecnicos> getDetallesTecnicos() {
		return DetallesTecnicos;
	}
	
	public Coche setDetallesTecnicos(List<DetallesTecnicos> DetallesTecnicos) {
		this.DetallesTecnicos = DetallesTecnicos;
		return this;
	}
	
	public Boolean getAirbag() {
		return Airbag;
	}
	
	public Coche setAirbag(Boolean Airbag) {
		this.Airbag = Airbag;
		return this;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Coche {");
        sb.append("id=").append(id);
        sb.append(", idCoche=").append(idCoche);
        sb.append(", Matricula=").append(Matricula);
        sb.append(", Marca=").append(Marca);
        sb.append(", Modelo=").append(Modelo);
        sb.append(", Altura=").append(Altura);
        sb.append(", Anchura=").append(Anchura);
        sb.append(", DetallesTecnicos=").append(DetallesTecnicos);
        sb.append(", Airbag=").append(Airbag);
        sb.append('}');
        return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(Airbag, Altura, Anchura, DetallesTecnicos, Marca, Matricula, Modelo, id, idCoche);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		return Objects.equals(Airbag, other.Airbag) && Objects.equals(Altura, other.Altura)
				&& Objects.equals(Anchura, other.Anchura) && Objects.equals(DetallesTecnicos, other.DetallesTecnicos)
				&& Objects.equals(Marca, other.Marca) && Objects.equals(Matricula, other.Matricula)
				&& Objects.equals(Modelo, other.Modelo) && Objects.equals(id, other.id) && idCoche == other.idCoche;
	}
}
