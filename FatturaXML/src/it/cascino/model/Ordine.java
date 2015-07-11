package it.cascino.model;

import java.util.ArrayList;
import java.util.List;

public class Ordine{
	private String idDocumentoOrdine;
	private String dataOrdine;
	private String codiceCUP;
	private String codiceCIG;
	private List<String> riferimentoNumeroLinea;
	
	public Ordine(){
		this.idDocumentoOrdine = "nd";
		this.dataOrdine = "nd";
		this.codiceCUP = "nd";
		this.codiceCIG = "nd";
		this.riferimentoNumeroLinea = new ArrayList<String>();
	}
	
	public Ordine(String numeroDDT, String dataDDT, String codiceCUP, String codiceCIG, List<String> riferimentoNumeroLinea){
		this.idDocumentoOrdine = numeroDDT;
		this.dataOrdine = dataDDT;
		this.codiceCUP = codiceCUP;
		this.codiceCIG = codiceCIG;
		this.riferimentoNumeroLinea = riferimentoNumeroLinea;
	}
	
	public String getIdDocumentoOrdine(){
		return idDocumentoOrdine;
	}
	
	public void setIdDocumentoOrdine(String numeroDDT){
		this.idDocumentoOrdine = numeroDDT;
	}
	
	public String getDataOrdine(){
		return dataOrdine;
	}
	
	public void setDataOrdine(String dataOrdine){
		this.dataOrdine = dataOrdine;
	}
	
	public String getCodiceCUP(){
		return codiceCUP;
	}

	public void setCodiceCUP(String codiceCUP){
		this.codiceCUP = codiceCUP;
	}
	
	public String getCodiceCIG(){
		return codiceCIG;
	}

	public void setCodiceCIG(String codiceCIG){
		this.codiceCIG = codiceCIG;
	}

	public List<String> getRiferimentoNumeroLinea(){
		return riferimentoNumeroLinea;
	}
	
	public void setRiferimentoNumeroLinea(List<String> riferimentoNumeroLinea){
		this.riferimentoNumeroLinea = riferimentoNumeroLinea;
	}
}
