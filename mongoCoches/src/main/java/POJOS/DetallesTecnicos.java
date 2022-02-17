package POJOS;

import java.util.Objects;

public class DetallesTecnicos {
	private int potenciaMaxima;
	private String descripcion;
	private int numeroAsientos;
	
	public int getPotenciaMaxima() {
		return potenciaMaxima;
	}
	
	public DetallesTecnicos setPotenciaMaxima(int potenciaMaxima) {
		this.potenciaMaxima = potenciaMaxima;
		return this;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public DetallesTecnicos setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}
	
	public int getNumeroAsientos() {
		return numeroAsientos;
	}
	
	public DetallesTecnicos setNumeroAsientos(int numeroAsientos) {
		this.numeroAsientos = numeroAsientos;
		return this;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Detalles Tecnicos{");
        sb.append("potenciaMaxima='").append(potenciaMaxima).append('\'');
        sb.append(", descripcion=").append(descripcion).append('\'');
        sb.append(", numeroAsientos=").append(numeroAsientos);
        sb.append('}');
        return sb.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetallesTecnicos other = (DetallesTecnicos) obj;
		return Objects.equals(descripcion, other.descripcion) && numeroAsientos == other.numeroAsientos
				&& potenciaMaxima == other.potenciaMaxima;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(descripcion, numeroAsientos, potenciaMaxima);
	}
}
