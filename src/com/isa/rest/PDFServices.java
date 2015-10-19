package com.isa.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.isa.entities.Documento;
import com.isa.utiles.UtilesResources;

@Path("pdfs")
public class PDFServices {

	@GET
	@Path("getUnsigned/")
	@Produces("application/json")
	public String obtenerDocumentosNoFirmados(){
		System.out.println("PDFServices::obtenerDocumentosNoFirmados");
		try{
			List <Documento>  documentos =new ArrayList<Documento>();
			File dir = new File ( UtilesResources.getProperty("config.docNoFirmados"));
    		File[] ficheros = dir.listFiles();
    	
    		for (int x = 0; x < ficheros.length; x++) {
             	File file = ficheros[x];
             	if (file.isFile()) {
             		String name = ficheros[x].getName();
                 	String ruta = ficheros[x].getAbsolutePath();
                 	
					Documento docToBeSigned = new Documento();
						
					docToBeSigned.setNombre(name);
					docToBeSigned.setEstado(name.indexOf("-F.pdf") > 0 ? UtilesResources.getProperty("config.EstadoFirmado") : UtilesResources.getProperty("config.EstadoSinFirmar"));
					docToBeSigned.setConcepto(UtilesResources.getProperty("config.Documento" + x + ".concepto"));
					docToBeSigned.setNroRecibo(name.substring(0, name.indexOf("-") > 0 ? name.indexOf("-") : name.indexOf(".")));
					docToBeSigned.setFecha(UtilesResources.getProperty("config.Documento" + x + ".fecha"));
					docToBeSigned.setRuta( ruta );

					documentos.add(docToBeSigned);
 				}	
             }     		
    		
    		System.out.println("PDFServices::obtenerDocumentosNoFirmadosOK");
    		Gson gson = new Gson();
			return gson.toJson( documentos );
		}
		catch(Exception e){
			e.printStackTrace();
			return "ERROR";
		}
	}
	
	@GET
	@Path("getSigned/")
	@Produces("application/json")
	public String obtenerDocumentosFirmados(){
		System.out.println("PDFServices::obtenerDocumentosFirmados");
		try{
			List <Documento>  documentos = new ArrayList<Documento>();
			
			File dir = new File ( UtilesResources.getProperty("config.docFirmados") );
    		File[] ficheros = dir.listFiles();
    	
    		for (int x = 0; x < ficheros.length; x++) {
             	File file = ficheros[x];
             	if (file.isFile()) {
             		String name = ficheros[x].getName();
                 	String ruta = ficheros[x].getAbsolutePath();
                 	
					Documento docToBeSigned = new Documento();
						
					docToBeSigned.setNombre(name);
					docToBeSigned.setEstado(UtilesResources.getProperty("config.EstadoFirmado"));
					docToBeSigned.setConcepto(UtilesResources.getProperty("config.Documento" + x +".concepto"));
					docToBeSigned.setNroRecibo(name.substring(0, name.length()- 4));
					docToBeSigned.setFecha(UtilesResources.getProperty("config.Documento" + x +".fecha"));
					docToBeSigned.setRuta( ruta );

					documentos.add(docToBeSigned);
 				}	
             }    		
    		
    		System.out.println("PDFServices::obtenerDocumentosFirmadosOK");
     		Gson gson = new Gson();
 			return gson.toJson( documentos );
		}
		catch(Exception e){
			e.printStackTrace();
			return "ERROR";
		}
	}
}
