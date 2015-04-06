package com.br.uepb.utilities;

public class VerificadorString {
	
	public static boolean ContemLetra(String palavra){
		for(char c : palavra.toCharArray()){
			if(Character.isLetter(c)) return true;
		}
		return false;
	}

}
