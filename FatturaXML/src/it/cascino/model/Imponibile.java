package it.cascino.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Imponibile{
	private String aliquotaIVA;
	private String imponibileImporto;
	private String imposta;
	private String  esigibilitaIVA;
	
	public Imponibile(){
		this.aliquotaIVA = "nd";
		this.imponibileImporto = "nd";
		this.imposta = "nd";
		this.esigibilitaIVA = "nd";
	}
	
	public Imponibile(String aliquotaIVA, String imponibileImporto, String imposta, String esigibilitaIVA){
		this.aliquotaIVA = aliquotaIVA;
		this.imponibileImporto = imponibileImporto;
		this.imposta = imposta;
		this.esigibilitaIVA = esigibilitaIVA;
	}
	
	public String getAliquotaIVA(){
		return aliquotaIVA;
	}
	
	public void setAliquotaIVA(String aliquotaIVA){
		aliquotaIVA = aliquotaIVA.trim();
		if(aliquotaIVA.startsWith("0") == true){
			aliquotaIVA = aliquotaIVA.substring(1);
		}
		if(aliquotaIVA.contains(".")){
			String qs[] = aliquotaIVA.split("\\.");
			if(qs.length == 2){
				aliquotaIVA = qs[0] + "." + (qs[1] + "00").substring(0, 2);
			}else{
				aliquotaIVA = qs[0] + ".00";			
			}			
		}else{
			aliquotaIVA = aliquotaIVA + ".00";
		}
		Pattern p = Pattern.compile(".*[a-zA-Z]+.*");
		Matcher m = p.matcher(aliquotaIVA);
		if(m.matches()){
			aliquotaIVA = "0.00";
		}
		this.aliquotaIVA = ((aliquotaIVA.startsWith(".") == true) ? "0" + aliquotaIVA : aliquotaIVA);
	}
	
	public String getImponibileImporto(){
		return imponibileImporto;
	}
	
	public void setImponibileImporto(String imponibileImporto){
		imponibileImporto = imponibileImporto.trim();
		this.imponibileImporto = ((imponibileImporto.startsWith(".") == true) ? "0" + imponibileImporto : imponibileImporto);
	}
	
	public String getImposta(){
		return imposta;
	}
	
	public void setImposta(String imposta){
		imposta = imposta.trim();
		this.imposta = ((imposta.startsWith(".") == true) ? "0" + imposta : imposta);
	}
	
	public String getEsigibilitaIVA(){
		return esigibilitaIVA;
	}
	
	public void setEsigibilitaIVA(String esigibilitaIVA){
		esigibilitaIVA = esigibilitaIVA.trim().toUpperCase();
		this.esigibilitaIVA = esigibilitaIVA;
	}
}
