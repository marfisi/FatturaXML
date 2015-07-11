package it.cascino.fatturaxml;

import it.cascino.model.Articolo;
import it.cascino.model.DDT;
import it.cascino.model.Fattura;
import it.cascino.model.Imponibile;
import it.cascino.model.Ordine;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FatturaXml{
	private static String basePathInput = "C:/Users/administrator.CASCINOSNC/Desktop/";
	private static String fileNameInput = basePathInput + "fattura_elettronica_as400.txt";
	
	private static String basePathOutput = basePathInput;
	// private static String fileNameOutput = basePathOutput + "IT02458660822_";
	
	private static File fileInput;
	private static File fileOutput;
	
	private static List<String> lstRowInput = new ArrayList<String>();
	private static StringBuilder stringBuilder = null;
	
	// private static Fattura fattura;
	private static List<Fattura> fatturaList = new ArrayList<Fattura>();
	private static final String separator = "|";
	
	public static void main(String[] args){
		for(int numArg = 0; numArg < args.length; numArg++){
			if(args[numArg].compareTo("-dI") == 0){
				numArg++;
				basePathInput = args[numArg];
			}else if(args[numArg].compareTo("-fI") == 0){
				numArg++;
				fileNameInput = basePathInput + args[numArg];
			}else if(args[numArg].compareTo("-dO") == 0){
				numArg++;
				basePathOutput = args[numArg];
				// }else if(args[numArg].compareTo("-fO") == 0){
				// numArg++;
				// fileNameOutput = basePathOutput + args[numArg];
			}else{ // se c'e' almeno un parametro e non e' tra quelli previsti stampo il messaggio d'aiuto
			}
		}
		
		fileInput = new File(fileNameInput);
		
		String line = null;
		try{
			FileReader fstream = new FileReader(fileInput);
			BufferedReader in = new BufferedReader(fstream);
			
			int rigo = 0;
			while((line = in.readLine()) != null){
				rigo++;
				
				System.out.println("rigo: " + rigo);
				
				if(line.isEmpty()){
					continue;
				}
				lstRowInput.add(line);
			}
			
			// Close the input stream
			in.close();
			
			// fattura = new Fattura();
			elaboraLstRowInput();
			creaReportXml();
			
		}catch(IOException ioe){// Catch exception if any
			System.err.println("Error: " + ioe.getMessage());
		}
	}
	
	private static void elaboraLstRowInput(){
		Iterator<String> iteratoreReport;
		iteratoreReport = lstRowInput.iterator();
		String value = null;
		String values[] = null;
		
		Fattura fattura = null;
		while(iteratoreReport.hasNext()){
			value = (String)iteratoreReport.next();
			
			if(value.startsWith("XML")){
				if(fattura != null){
					fatturaList.add(fattura);
				}
				fattura = new Fattura();
			}
			if(value.startsWith("<<<")){
				break;
			}
			
			List<Ordine> ordineList = new ArrayList<Ordine>();
			List<DDT> ddtList = new ArrayList<DDT>();
			List<Articolo> articoloList = new ArrayList<Articolo>();
			List<Imponibile> imponibileList = new ArrayList<Imponibile>();
			
			if(value.startsWith(">>>ordini")){
				Ordine ordine = new Ordine();
				
				while(iteratoreReport.hasNext()){
					value = (String)iteratoreReport.next();
					
					if(value.startsWith("<<<")){
						break;
					}
					if(value.startsWith("<")){
						ordineList.add(ordine);
						ordine = new Ordine();
						continue;
					}
					
					if(value.startsWith("IdDocumentoOrdine")){
						values = value.split("\\" + separator);
						value = values[1];
						ordine.setIdDocumentoOrdine(value);
					}else if(value.startsWith("DataOrdine")){
						values = value.split("\\" + separator);
						value = values[1];
						ordine.setDataOrdine(value);
					}else if(value.startsWith("CodiceCUP")){
						values = value.split("\\" + separator);
						value = values[1];
						ordine.setCodiceCUP(value);
					}else if(value.startsWith("CodiceCIG")){
						values = value.split("\\" + separator);
						value = values[1];
						ordine.setCodiceCIG(value);
					}else if(value.startsWith("RiferimentoNumeroLineaOrdine")){
						values = value.split("\\" + separator);
						value = values[1];
						String numeroLinea[] = value.split(";");
						List<String> riferimentoNumeroLinea = new ArrayList<String>();;
						for(int i = 0; i < numeroLinea.length; i++){
							riferimentoNumeroLinea.add(numeroLinea[i].trim());
						}
						ordine.setRiferimentoNumeroLinea(riferimentoNumeroLinea);
					}
				}
				fattura.setOrdineList(ordineList);
			}else if(value.startsWith(">>>ddt")){
				DDT ddt = new DDT();
				
				while(iteratoreReport.hasNext()){
					value = (String)iteratoreReport.next();
					
					if(value.startsWith("<<<")){
						break;
					}
					if(value.startsWith("<")){
						ddtList.add(ddt);
						ddt = new DDT();
						continue;
					}
					
					if(value.startsWith("NumeroDDT")){
						values = value.split("\\" + separator);
						value = values[1];
						ddt.setNumeroDDT(value);
					}else if(value.startsWith("DataDDT")){
						values = value.split("\\" + separator);
						value = values[1];
						ddt.setDataDDT(value);
					}else if(value.startsWith("RiferimentoNumeroLineaDDT")){
						values = value.split("\\" + separator);
						value = values[1];
						String numeroLinea[] = value.split(";");
						List<String> riferimentoNumeroLinea = new ArrayList<String>();;
						for(int i = 0; i < numeroLinea.length; i++){
							riferimentoNumeroLinea.add(numeroLinea[i].trim());
						}
						ddt.setRiferimentoNumeroLinea(riferimentoNumeroLinea);
					}
				}
				fattura.setDdtList(ddtList);
			}else if(value.startsWith(">>>articoli")){
				Articolo articolo = new Articolo();
				
				while(iteratoreReport.hasNext()){
					value = (String)iteratoreReport.next();
					
					if(value.startsWith("<<<")){
						break;
					}
					if(value.startsWith("<")){
						articoloList.add(articolo);
						articolo = new Articolo();
						continue;
					}
					
					if(value.startsWith("NumeroLinea")){
						values = value.split("\\" + separator);
						value = values[1];
						articolo.setNumeroLinea(value);
					}else if(value.startsWith("CodiceValore")){
						values = value.split("\\" + separator);
						value = values[1];
						articolo.setCodiceValore(value);
					}else if(value.startsWith("Descrizione")){
						values = value.split("\\" + separator);
						value = values[1];
						articolo.setDescrizione(value);
					}else if(value.startsWith("Quantita")){
						values = value.split("\\" + separator);
						value = values[1];
						articolo.setQuantita(value);
					}else if(value.startsWith("UnitaMisura")){
						values = value.split("\\" + separator);
						value = values[1];
						articolo.setUnitaMisura(value);
					}else if(value.startsWith("PrezzoUnitario")){
						values = value.split("\\" + separator);
						value = values[1];
						articolo.setPrezzoUnitario(value);
					}else if(value.startsWith("PrezzoTotale")){
						values = value.split("\\" + separator);
						value = values[1];
						articolo.setPrezzoTotale(value);
					}else if(value.startsWith("AliquotaIVA")){
						values = value.split("\\" + separator);
						value = values[1];
						articolo.setAliquotaIVA(value);
					}
				}
				fattura.setArticoloList(articoloList);
			}else if(value.startsWith(">>>imponibili")){
				Imponibile imponibile = new Imponibile();
				
				while(iteratoreReport.hasNext()){
					value = (String)iteratoreReport.next();
					
					if(value.startsWith("<<<")){
						break;
					}
					if(value.startsWith("<")){
						imponibileList.add(imponibile);
						imponibile = new Imponibile();
						continue;
					}
					
					if(value.startsWith("AliquotaIVA")){
						values = value.split("\\" + separator);
						value = values[1];
						imponibile.setAliquotaIVA(value);
					}else if(value.startsWith("ImponibileImporto")){
						values = value.split("\\" + separator);
						value = values[1];
						imponibile.setImponibileImporto(value);
					}else if(value.startsWith("Imposta")){
						values = value.split("\\" + separator);
						value = values[1];
						imponibile.setImposta(value);
					}else if(value.startsWith("EsigibilitaIVA")){
						values = value.split("\\" + separator);
						value = values[1];
						imponibile.setEsigibilitaIVA(value);
					}
				}
				fattura.setImponibileList(imponibileList);
			}else{
				if(value.startsWith("XML")){
					values = value.split("\\" + separator);
					value = values[1];
					fattura.setNomeFile(value);
				}else if(value.startsWith("ProgressivoInvio")){
					values = value.split("\\" + separator);
					value = values[1];
					fattura.setProgressivoInvio(value);
				}else if(value.startsWith("CodiceDestinatario")){
					values = value.split("\\" + separator);
					value = values[1];
					fattura.setCodiceDestinatario(value);
				}else if(value.startsWith("CodiceFiscale")){
					values = value.split("\\" + separator);
					value = values[1];
					fattura.setCodiceFiscale(value);
				}else if(value.startsWith("Denominazione")){
					values = value.split("\\" + separator);
					value = values[1];
					fattura.setDenominazione(value);
				}else if(value.startsWith("Indirizzo")){
					values = value.split("\\" + separator);
					value = values[1];
					fattura.setIndirizzo(value);
				}else if(value.startsWith("CAP")){
					values = value.split("\\" + separator);
					value = values[1];
					fattura.setCap(value);
				}else if(value.startsWith("Comune")){
					values = value.split("\\" + separator);
					value = values[1];
					fattura.setComune(value);
				}else if(value.startsWith("Provincia")){
					values = value.split("\\" + separator);
					value = values[1];
					fattura.setProvincia(value);
				}else if(value.startsWith("TipoDocumento")){
					values = value.split("\\" + separator);
					value = values[1];
					fattura.setTipoDocumento(value);
				}else if(value.startsWith("Data")){
					values = value.split("\\" + separator);
					value = values[1];
					fattura.setData(value);
				}else if(value.startsWith("Numero")){
					values = value.split("\\" + separator);
					value = values[1];
					fattura.setNumero(value);
				}else if(value.startsWith("CondizioniPagamento")){
					values = value.split("\\" + separator);
					value = values[1];
					fattura.setCondizioniPagamento(value);
				}else if(value.startsWith("ModalitaPagamento")){
					values = value.split("\\" + separator);
					value = values[1];
					fattura.setModalitaPagamento(value);
				}else if(value.startsWith("ImportoPagamento")){
					values = value.split("\\" + separator);
					value = values[1];
					fattura.setImportoPagamento(value);
				}
			}
		}
		if(fattura != null){
			fatturaList.add(fattura);
		}
	}
	
	private static void creaReportXml(){
		final String lineSeparator = System.getProperty("line.separator");
		
		final String tab1 = "\t";
		final String tab2 = "\t\t";
		final String tab3 = "\t\t\t";
		final String tab4 = "\t\t\t\t";
		
		for(int f = 0; f < fatturaList.size(); f++){
			Fattura fattura = fatturaList.get(f);
			
			// fileNameOutput += fattura.getProgressivoInvio() + ".xml";
			// fileOutput = new File(fileNameOutput);
			fileOutput = new File(basePathOutput + fattura.getNomeFile());
			
			stringBuilder = new StringBuilder();
			
			FileWriter fstream = null;
			BufferedWriter out = null;
			
			try{
				fstream = new FileWriter(fileOutput, false); // true = append
				
				out = new BufferedWriter(fstream);
			}catch(IOException ioe){
				System.err.println("Error: " + ioe.getMessage());
			}
			
			stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(lineSeparator);
			stringBuilder.append("<p:FatturaElettronica versione=\"1.1\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:p=\"http://www.fatturapa.gov.it/sdi/fatturapa/v1.1\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">").append(lineSeparator);
			stringBuilder.append("<FatturaElettronicaHeader>").append(lineSeparator);
			stringBuilder.append(tab1).append("<DatiTrasmissione>").append(lineSeparator);
			stringBuilder.append(tab2).append("<IdTrasmittente>").append(lineSeparator);
			stringBuilder.append(tab3).append("<IdPaese>IT</IdPaese>").append(lineSeparator);
			stringBuilder.append(tab3).append("<IdCodice>02458660822</IdCodice>").append(lineSeparator);
			stringBuilder.append(tab2).append("</IdTrasmittente>").append(lineSeparator);
			stringBuilder.append(tab2).append("<ProgressivoInvio>").append(fattura.getProgressivoInvio()).append("</ProgressivoInvio>").append(lineSeparator);
			stringBuilder.append(tab2).append("<FormatoTrasmissione>SDI11</FormatoTrasmissione>").append(lineSeparator);
			stringBuilder.append(tab2).append("<CodiceDestinatario>").append(fattura.getCodiceDestinatario()).append("</CodiceDestinatario>").append(lineSeparator);
			stringBuilder.append(tab1).append("</DatiTrasmissione>").append(lineSeparator);
			stringBuilder.append(tab1).append("<CedentePrestatore>").append(lineSeparator);
			stringBuilder.append(tab2).append("<DatiAnagrafici>").append(lineSeparator);
			stringBuilder.append(tab3).append("<IdFiscaleIVA>").append(lineSeparator);
			stringBuilder.append(tab4).append("<IdPaese>IT</IdPaese>").append(lineSeparator);
			stringBuilder.append(tab4).append("<IdCodice>02458660822</IdCodice>").append(lineSeparator);
			stringBuilder.append(tab3).append("</IdFiscaleIVA>").append(lineSeparator);
			stringBuilder.append(tab3).append("<Anagrafica>").append(lineSeparator);
			stringBuilder.append(tab4).append("<Denominazione>CASCINO ANGELO &amp; C. s.n.c.</Denominazione>").append(lineSeparator);
			stringBuilder.append(tab3).append("</Anagrafica>").append(lineSeparator);
			stringBuilder.append(tab3).append("<RegimeFiscale>RF01</RegimeFiscale>").append(lineSeparator);
			stringBuilder.append(tab2).append("</DatiAnagrafici>").append(lineSeparator);
			stringBuilder.append(tab2).append("<Sede>").append(lineSeparator);
			stringBuilder.append(tab3).append("<Indirizzo>Via Crisone</Indirizzo>").append(lineSeparator);
			stringBuilder.append(tab3).append("<NumeroCivico>24</NumeroCivico>").append(lineSeparator);
			stringBuilder.append(tab3).append("<CAP>90018</CAP>").append(lineSeparator);
			stringBuilder.append(tab3).append("<Comune>Termini Imerese</Comune>").append(lineSeparator);
			stringBuilder.append(tab3).append("<Provincia>PA</Provincia>").append(lineSeparator);
			stringBuilder.append(tab3).append("<Nazione>IT</Nazione>").append(lineSeparator);
			stringBuilder.append(tab2).append("</Sede>").append(lineSeparator);
			stringBuilder.append(tab1).append("</CedentePrestatore>").append(lineSeparator);
			stringBuilder.append(tab1).append("<CessionarioCommittente>").append(lineSeparator);
			stringBuilder.append(tab2).append("<DatiAnagrafici>").append(lineSeparator);
			stringBuilder.append(tab3).append("<CodiceFiscale>").append(fattura.getCodiceFiscale()).append("</CodiceFiscale>").append(lineSeparator);
			stringBuilder.append(tab3).append("<Anagrafica>").append(lineSeparator);
			stringBuilder.append(tab4).append("<Denominazione>").append(fattura.getDenominazione()).append("</Denominazione>").append(lineSeparator);
			stringBuilder.append(tab3).append("</Anagrafica>").append(lineSeparator);
			stringBuilder.append(tab2).append("</DatiAnagrafici>").append(lineSeparator);
			stringBuilder.append(tab2).append("<Sede>").append(lineSeparator);
			stringBuilder.append(tab3).append("<Indirizzo>").append(fattura.getIndirizzo()).append("</Indirizzo>").append(lineSeparator);
			stringBuilder.append(tab3).append("<CAP>").append(fattura.getCap()).append("</CAP>").append(lineSeparator);
			stringBuilder.append(tab3).append("<Comune>").append(fattura.getComune()).append("</Comune>").append(lineSeparator);
			stringBuilder.append(tab3).append("<Provincia>").append(fattura.getProvincia()).append("</Provincia>").append(lineSeparator);
			stringBuilder.append(tab3).append("<Nazione>IT</Nazione>").append(lineSeparator);
			stringBuilder.append(tab2).append("</Sede>").append(lineSeparator);
			stringBuilder.append(tab1).append("</CessionarioCommittente>").append(lineSeparator);
			stringBuilder.append("</FatturaElettronicaHeader>").append(lineSeparator);
			stringBuilder.append("<FatturaElettronicaBody>").append(lineSeparator);
			stringBuilder.append(tab1).append("<DatiGenerali>").append(lineSeparator);
			stringBuilder.append(tab2).append("<DatiGeneraliDocumento>").append(lineSeparator);
			stringBuilder.append(tab3).append("<TipoDocumento>").append(fattura.getTipoDocumento()).append("</TipoDocumento>").append(lineSeparator);
			stringBuilder.append(tab3).append("<Divisa>EUR</Divisa>").append(lineSeparator);
			stringBuilder.append(tab3).append("<Data>").append(fattura.getData()).append("</Data>").append(lineSeparator);
			stringBuilder.append(tab3).append("<Numero>").append(fattura.getNumero()).append("</Numero>").append(lineSeparator);
			stringBuilder.append(tab2).append("</DatiGeneraliDocumento>").append(lineSeparator);
			if(!(fattura.getOrdineList().isEmpty())){
				for(int i = 0; i < fattura.getOrdineList().size(); i++){
					Ordine ordine = fattura.getOrdineList().get(i);
					stringBuilder.append(tab2).append("<DatiOrdineAcquisto>").append(lineSeparator);
					for(int j = 0; j < ordine.getRiferimentoNumeroLinea().size(); j++){
						stringBuilder.append(tab3).append("<RiferimentoNumeroLinea>").append(ordine.getRiferimentoNumeroLinea().get(j)).append("</RiferimentoNumeroLinea>").append(lineSeparator);
					}
					stringBuilder.append(tab3).append("<IdDocumento>").append(ordine.getIdDocumentoOrdine()).append("</IdDocumento>").append(lineSeparator);
					if(!(ordine.getDataOrdine().equals("nd"))){
						stringBuilder.append(tab3).append("<Data>").append(ordine.getDataOrdine()).append("</Data>").append(lineSeparator);
					}
					if(!(ordine.getCodiceCUP().equals("nd"))){
						stringBuilder.append(tab3).append("<CodiceCUP>").append(ordine.getCodiceCUP()).append("</CodiceCUP>").append(lineSeparator);
					}
					stringBuilder.append(tab3).append("<CodiceCIG>").append(ordine.getCodiceCIG()).append("</CodiceCIG>").append(lineSeparator);
					stringBuilder.append(tab2).append("</DatiOrdineAcquisto>").append(lineSeparator);
				}
			}
			if(!(fattura.getDdtList().isEmpty())){
				for(int i = 0; i < fattura.getDdtList().size(); i++){
					DDT ddt = fattura.getDdtList().get(i);
					stringBuilder.append(tab2).append("<DatiDDT>").append(lineSeparator);
					stringBuilder.append(tab3).append("<NumeroDDT>").append(ddt.getNumeroDDT()).append("</NumeroDDT>").append(lineSeparator);
					stringBuilder.append(tab3).append("<DataDDT>").append(ddt.getDataDDT()).append("</DataDDT>").append(lineSeparator);
					for(int j = 0; j < ddt.getRiferimentoNumeroLinea().size(); j++){
						stringBuilder.append(tab3).append("<RiferimentoNumeroLinea>").append(ddt.getRiferimentoNumeroLinea().get(j)).append("</RiferimentoNumeroLinea>").append(lineSeparator);
					}
					stringBuilder.append(tab2).append("</DatiDDT>").append(lineSeparator);
				}
			}
			stringBuilder.append(tab1).append("</DatiGenerali>").append(lineSeparator);
			stringBuilder.append(tab1).append("<DatiBeniServizi>").append(lineSeparator);
			if(!(fattura.getArticoloList().isEmpty())){
				for(int i = 0; i < fattura.getArticoloList().size(); i++){
					Articolo articolo = fattura.getArticoloList().get(i);
					stringBuilder.append(tab2).append("<DettaglioLinee>").append(lineSeparator);
					stringBuilder.append(tab3).append("<NumeroLinea>").append(articolo.getNumeroLinea()).append("</NumeroLinea>").append(lineSeparator);
					stringBuilder.append(tab3).append("<CodiceArticolo>").append(lineSeparator);
					stringBuilder.append(tab4).append("<CodiceTipo>Codice Art. fornitore</CodiceTipo>").append(lineSeparator);
					stringBuilder.append(tab4).append("<CodiceValore>").append(articolo.getCodiceValore()).append("</CodiceValore>").append(lineSeparator);
					stringBuilder.append(tab3).append("</CodiceArticolo>").append(lineSeparator);
					stringBuilder.append(tab3).append("<Descrizione>").append(articolo.getDescrizione()).append("</Descrizione>").append(lineSeparator);
					stringBuilder.append(tab3).append("<Quantita>").append(articolo.getQuantita()).append("</Quantita>").append(lineSeparator);
					if(!(articolo.getUnitaMisura().equals("nd"))){
						// agostino non riesce a darmela, perche' dovrebbe prenderla da un altro db
						stringBuilder.append(tab3).append("<UnitaMisura>").append(articolo.getUnitaMisura()).append("</UnitaMisura>").append(lineSeparator);		
					}
					stringBuilder.append(tab3).append("<PrezzoUnitario>").append(articolo.getPrezzoUnitario()).append("</PrezzoUnitario>").append(lineSeparator);
					stringBuilder.append(tab3).append("<PrezzoTotale>").append(articolo.getPrezzoTotale()).append("</PrezzoTotale>").append(lineSeparator);
					stringBuilder.append(tab3).append("<AliquotaIVA>").append(articolo.getAliquotaIVA()).append("</AliquotaIVA>").append(lineSeparator);
					stringBuilder.append(tab2).append("</DettaglioLinee>").append(lineSeparator);
				}
			}
			if(!(fattura.getImponibileList().isEmpty())){
				for(int i = 0; i < fattura.getImponibileList().size(); i++){
					Imponibile imponibile = fattura.getImponibileList().get(i);
					stringBuilder.append(tab2).append("<DatiRiepilogo>").append(lineSeparator);
					stringBuilder.append(tab3).append("<AliquotaIVA>").append(imponibile.getAliquotaIVA()).append("</AliquotaIVA>").append(lineSeparator);
					stringBuilder.append(tab3).append("<ImponibileImporto>").append(imponibile.getImponibileImporto()).append("</ImponibileImporto>").append(lineSeparator);
					stringBuilder.append(tab3).append("<Imposta>").append(imponibile.getImposta()).append("</Imposta>").append(lineSeparator);
					stringBuilder.append(tab3).append("<EsigibilitaIVA>").append(imponibile.getEsigibilitaIVA()).append("</EsigibilitaIVA>").append(lineSeparator);
					stringBuilder.append(tab2).append("</DatiRiepilogo>").append(lineSeparator);
				}
			}
			stringBuilder.append(tab1).append("</DatiBeniServizi>").append(lineSeparator);
			stringBuilder.append(tab1).append("<DatiPagamento>").append(lineSeparator);
			stringBuilder.append(tab2).append("<CondizioniPagamento>").append(fattura.getCondizioniPagamento()).append("</CondizioniPagamento>").append(lineSeparator);
			stringBuilder.append(tab2).append("<DettaglioPagamento>").append(lineSeparator);
			stringBuilder.append(tab3).append("<ModalitaPagamento>").append(fattura.getModalitaPagamento()).append("</ModalitaPagamento>").append(lineSeparator);
			stringBuilder.append(tab3).append("<ImportoPagamento>").append(fattura.getImportoPagamento()).append("</ImportoPagamento>").append(lineSeparator);
			stringBuilder.append(tab2).append("</DettaglioPagamento>").append(lineSeparator);
			stringBuilder.append(tab1).append("</DatiPagamento>").append(lineSeparator);
			stringBuilder.append("</FatturaElettronicaBody>").append(lineSeparator);
			stringBuilder.append("</p:FatturaElettronica>").append(lineSeparator);
			
			try{
				out.write(stringBuilder.toString());
			}catch(IOException ioe){
				System.err.println("Error: " + ioe.getMessage());
			}
			
			// Close the output stream
			try{
				out.close();
			}catch(IOException ioe){
				System.err.println("Error: " + ioe.getMessage());
			}
			
		}
		
		// // Destination directory
		// File dir = new File(fileOutput.getParent());
		//
		// // Move file to new directory
		// boolean success = fileOutput.renameTo(new File(dir, fileOutput.getName().replace(".csv", "") + "_" + data + ".csv"));
		// if(!success){
		// //
		// }
	}
}
