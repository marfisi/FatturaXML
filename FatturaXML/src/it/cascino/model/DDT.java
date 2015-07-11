package it.cascino.model;

import java.util.ArrayList;
import java.util.List;

public class DDT{
	private String numeroDDT;
	private String dataDDT;
	private List<String> riferimentoNumeroLinea;
	
	public DDT(){
		this.numeroDDT = "nd";
		this.dataDDT = "nd";
		this.riferimentoNumeroLinea = new ArrayList<String>();
	}
	
	public DDT(String numeroDDT, String dataDDT, List<String> riferimentoNumeroLinea){
		this.numeroDDT = numeroDDT;
		this.dataDDT = dataDDT;
		this.riferimentoNumeroLinea = riferimentoNumeroLinea;
	}
	
	public String getNumeroDDT(){
		return numeroDDT;
	}
	
	public void setNumeroDDT(String numeroDDT){
		this.numeroDDT = numeroDDT;
	}
	
	public String getDataDDT(){
		return dataDDT;
	}
	
	public void setDataDDT(String dataDDT){
		this.dataDDT = dataDDT;
	}
	
	public List<String> getRiferimentoNumeroLinea(){
		return riferimentoNumeroLinea;
	}
	
	public void setRiferimentoNumeroLinea(List<String> riferimentoNumeroLinea){
		this.riferimentoNumeroLinea = riferimentoNumeroLinea;
	}
}
