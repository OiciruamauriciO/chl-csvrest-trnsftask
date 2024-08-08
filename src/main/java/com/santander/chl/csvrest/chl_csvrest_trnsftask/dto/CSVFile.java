package com.santander.chl.csvrest.chl_csvrest_trnsftask.dto;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",", skipFirstLine = true, generateHeaderColumns = true)
public class CSVFile {

	@DataField(pos = 1, columnName = "id")
	private String id;
	@DataField(pos = 2, columnName = "password")
	private String password;	
	@DataField(pos = 3, columnName = "username")
	private String username;

	public CSVFile() {

	}
	
	public String getUserName() {
		return this.username;
	}

	public String getId() {
		return this.id;
	}

	public String getPassword() {
		return this.password;
	}
	
	public void setUserName(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}