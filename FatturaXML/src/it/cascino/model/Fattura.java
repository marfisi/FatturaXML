package it.cascino.model;

import java.util.ArrayList;
import java.util.List;

public class Fattura{
	private String nomeFile;
	private String progressivoInvio;
	private String codiceDestinatario;
	private String codiceFiscale;
	private String denominazione;
	private String indirizzo;
	private String cap;
	private String comune;
	private String provincia;
	private String tipoDocumento;
	private String data;
	private String numero;
	private String condizioniPagamento;
	private String modalitaPagamento;
	private String importoPagamento;
	
	private List<Ordine> ordineList;
	private List<DDT> ddtList;
	private List<Articolo> articoloList;
	private List<Imponibile> imponibileList;

	public Fattura(){
		this.nomeFile = "nd";
		this.progressivoInvio = "nd";
		this.codiceDestinatario = "nd";
		this.codiceFiscale = "nd";
		this.denominazione = "nd";
		this.indirizzo = "nd";
		this.cap = "nd";
		this.comune = "nd";
		this.provincia = "nd";
		this.tipoDocumento = "nd";
		this.data = "nd";
		this.numero = "nd";
		this.condizioniPagamento = "nd";
		this.modalitaPagamento = "nd";
		this.importoPagamento = "nd";
		this.ordineList = new ArrayList<Ordine>();
		this.ddtList = new ArrayList<DDT>();
		this.articoloList = new ArrayList<Articolo>();
		this.imponibileList = new ArrayList<Imponibile>();
	}
	
	public Fattura(String nomeFile, String progressivoInvio, String codiceDestinatario, String codiceFiscale, String denominazione, String indirizzo, String cap, String comune, String provincia, String tipoDocumento, String data, String numero, String condizioniPagamento, String modalitaPagamento, String importoPagamento, List<Ordine> ordineList, List<DDT> ddtList, List<Articolo> articoloList, List<Imponibile> imponibileList){
		this.nomeFile = nomeFile;
		this.progressivoInvio = progressivoInvio;
		this.codiceDestinatario = codiceDestinatario;
		this.codiceFiscale = codiceFiscale;
		this.denominazione = denominazione;
		this.indirizzo = indirizzo;
		this.cap = cap;
		this.comune = comune;
		this.provincia = provincia;
		this.tipoDocumento = tipoDocumento;
		this.data = data;
		this.numero = numero;
		this.condizioniPagamento = condizioniPagamento;
		this.modalitaPagamento = modalitaPagamento;
		this.importoPagamento = importoPagamento;
		this.ordineList = ordineList;
		this.ddtList = ddtList;
		this.articoloList = articoloList;
		this.imponibileList = imponibileList;
	}
	
	public String getNomeFile(){
		return nomeFile;
	}
	
	public void setNomeFile(String nomeFile){
		nomeFile = nomeFile.replace(".XML", ".xml");
		this.nomeFile = nomeFile;
	}
	
	public String getProgressivoInvio(){
		return progressivoInvio;
	}
	
	public void setProgressivoInvio(String progressivoInvio){
		this.progressivoInvio = progressivoInvio;
	}
	
	public String getCodiceDestinatario(){
		return codiceDestinatario;
	}
	
	public void setCodiceDestinatario(String codiceDestinatario){
		this.codiceDestinatario = codiceDestinatario;
	}
	
	public String getCodiceFiscale(){
		return codiceFiscale;
	}
	
	public void setCodiceFiscale(String codiceFiscale){
		this.codiceFiscale = codiceFiscale;
	}
	
	public String getDenominazione(){
		return denominazione;
	}
	
	public void setDenominazione(String denominazione){
		this.denominazione = denominazione;
	}
	
	public String getIndirizzo(){
		return indirizzo;
	}
	
	public void setIndirizzo(String indirizzo){
		this.indirizzo = indirizzo;
	}
	
	public String getCap(){
		return cap;
	}
	
	public void setCap(String cap){
		this.cap = cap;
	}
	
	public String getComune(){
		return comune;
	}
	
	public void setComune(String comune){
		this.comune = comune;
	}
	
	public String getProvincia(){
		return provincia;
	}
	
	public void setProvincia(String provincia){
		this.provincia = provincia;
	}
	
	public String getTipoDocumento(){
		return tipoDocumento;
	}
	
	public void setTipoDocumento(String tipoDocumento){
		this.tipoDocumento = tipoDocumento;
	}
	
	public String getData(){
		return data;
	}
	
	public void setData(String data){
		this.data = data;
	}
	
	public String getNumero(){
		return numero;
	}
	
	public void setNumero(String numero){
		this.numero = numero;
	}
	
	public String getCondizioniPagamento(){
		return condizioniPagamento;
	}
	
	public void setCondizioniPagamento(String condizioniPagamento){
		this.condizioniPagamento = condizioniPagamento;
	}
	
	public String getModalitaPagamento(){
		return modalitaPagamento;
	}
	
	public void setModalitaPagamento(String modalitaPagamento){
		this.modalitaPagamento = modalitaPagamento;
	}
	
	public String getImportoPagamento(){
		return importoPagamento;
	}
	
	public void setImportoPagamento(String importoPagamento){
		importoPagamento = importoPagamento.trim();
		this.importoPagamento = ((importoPagamento.startsWith(".") == true) ? "0" + importoPagamento : importoPagamento);
	}
	
	public List<Ordine> getOrdineList(){
		return ordineList;
	}
	
	public void setOrdineList(List<Ordine> ordineList){
		this.ordineList = ordineList;
	}
	
	public List<DDT> getDdtList(){
		return ddtList;
	}
	
	public void setDdtList(List<DDT> ddtList){
		this.ddtList = ddtList;
	}
	
	public List<Articolo> getArticoloList(){
		return articoloList;
	}
	
	public void setArticoloList(List<Articolo> articoloList){
		this.articoloList = articoloList;
	}
	
	public List<Imponibile> getImponibileList(){
		return imponibileList;
	}

	public void setImponibileList(List<Imponibile> imponibileList){
		this.imponibileList = imponibileList;
	}

}
