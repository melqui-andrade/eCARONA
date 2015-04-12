package com.br.uepb.utilities;

/**
 * Verifica a ocorrência de padrões em Strings
 * @author Melqui
 *
 */
public class VerificadorString {
	
	/**
	 * Verica se palavra contém letra
	 * @param palavra a ser analisada
	 * @return true, caso contenha palavra
	 */
	public static boolean ContemLetra(String palavra){
		for(char c : palavra.toCharArray()){
			if(Character.isLetter(c)) return true;
		}
		return false;
	}

}
