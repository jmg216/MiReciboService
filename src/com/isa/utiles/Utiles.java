package com.isa.utiles;

import java.text.SimpleDateFormat;

public class Utiles {
	
	public static final String ESTADO_FIRMANDO = "Firmado";
	public static final String ESTADO_SINFIRMAR = "Sin Firmar";
	
	
	/**
	 * Contante utilizada para formatear y parsear las variables de tipo fecha: dd/MM/yyyy HH:mm:ss
	 * */
	public static final SimpleDateFormat DATE_FORMAT_FULL = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	/**
	 * Devuelve true si el String pasado por parámetro
	 * es null o vacío.
	 * */
	public static boolean isNullOrEmpty ( String str ){
		return (str == null || str.equals(""));
	}	
	
}
