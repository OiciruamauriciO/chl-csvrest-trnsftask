package com.santander.chl.csvrest.chl_csvrest_trnsftask.dto;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",", skipFirstLine = true, generateHeaderColumns = true)
public class CSVCTLFile {
	
	@DataField(pos = 1, columnName = "apellido")
	private String apellido;
	@DataField(pos = 2, columnName = "fechaNacimiento")
	private String fechaNacimiento;
	@DataField(pos = 3, columnName = "nacionalidad")
	private String nacionalidad;
	@DataField(pos = 4, columnName = "nombre")
	private String nombre;
	@DataField(pos = 5, columnName = "numeroDocumento")
	private Integer numeroDocumento;
	@DataField(pos = 6, columnName = "sexo")
	private String sexo;
	@DataField(pos = 7, columnName = "tipoDocumento")
	private String tipoDocumento;
	
	public CSVCTLFile() {

	}
	
	public String getApellido() {
		return this.apellido;
	}
	
	public String getFechaNacimiento() {
		return this.fechaNacimiento;
	}
	
	public String getNacionalidad() {
		return this.nacionalidad;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public Integer getNumeroDocumento() {
		return this.numeroDocumento;
	}
	
	public String getSexo() {
		return this.sexo;
	}
	
	public String getTipoDocumento() {
		return this.tipoDocumento;
	}
	
	public void setApellido(String apellido) {
		this.apellido=apellido;
	}
	
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento=fechaNacimiento;
	}
	
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad=nacionalidad;
	}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	
	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento=numeroDocumento;
	}
	
	public void setSexo(String sexo) {
		this.sexo=sexo;
	}
	
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento=tipoDocumento;
	}
}
