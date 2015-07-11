package it.cascino.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Articolo{
	private String numeroLinea;
	private String codiceValore;
	private String descrizione;
	private String quantita;
	private String unitaMisura;
	private String prezzoUnitario;
	private String prezzoTotale;
	private String aliquotaIVA;
	
	public Articolo(){
		this.numeroLinea = "nd";
		this.codiceValore = "nd";
		this.descrizione = "nd";
		this.quantita = "nd";
		this.unitaMisura = "nd";
		this.prezzoUnitario = "nd";
		this.prezzoTotale = "nd";
		this.aliquotaIVA = "nd";
	}
	
	public Articolo(String numeroLinea, String codiceValore, String descrizione, String quantita, String unitaMisura, String prezzoUnitario, String prezzoTotale, String aliquotaIVA){
		this.numeroLinea = numeroLinea;
		this.codiceValore = codiceValore;
		this.descrizione = descrizione;
		this.quantita = quantita;
		this.unitaMisura = unitaMisura;
		this.prezzoUnitario = prezzoUnitario;
		this.prezzoTotale = prezzoTotale;
		this.aliquotaIVA = aliquotaIVA;
	}

	public String getCodiceValore(){
		return codiceValore;
	}
	
	public void setCodiceValore(String codiceValore){
		this.codiceValore = codiceValore;
	}
	
	public String getDescrizione(){
		return descrizione;
	}
	
	public void setDescrizione(String descrizione){
		if(descrizione.contains("&")){
			descrizione = descrizione.replace("&", "&amp;");
		}
		this.descrizione = descrizione;
	}
	
	public String getPrezzoUnitario(){
		return prezzoUnitario;
	}
	
	public void setPrezzoUnitario(String prezzoUnitario){
		prezzoUnitario = prezzoUnitario.trim();
		this.prezzoUnitario = ((prezzoUnitario.startsWith(".") == true) ? "0" + prezzoUnitario : prezzoUnitario);
	}
	
	public String getNumeroLinea(){
		return numeroLinea;
	}
	
	public void setNumeroLinea(String numeroLinea){
		numeroLinea = numeroLinea.trim();
		this.numeroLinea = numeroLinea;
	}
	
	public String getQuantita(){
		return quantita;
	}
	
	public void setQuantita(String quantita){
		quantita = quantita.trim();
		if(quantita.contains(".")){
			String qs[] = quantita.split("\\.");
			if(qs.length == 2){
				quantita = qs[0] + "." + (qs[1] + "00").substring(0, 2);
			}else{
				quantita = qs[0] + ".00";			
			}			
		}else{
			quantita = quantita + ".00";
		}
		this.quantita = ((quantita.startsWith(".") == true) ? "0" + quantita : quantita);
	}
	
	public String getUnitaMisura(){
		return unitaMisura;
	}
	
	public void setUnitaMisura(String unitaMisura){
		this.unitaMisura = unitaMisura;
	}
	
	public String getPrezzoTotale(){
		return prezzoTotale;
	}
	
	public void setPrezzoTotale(String prezzoTotale){
		prezzoTotale = prezzoTotale.trim();
		this.prezzoTotale = ((prezzoTotale.startsWith(".") == true) ? "0" + prezzoTotale : prezzoTotale);
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
}