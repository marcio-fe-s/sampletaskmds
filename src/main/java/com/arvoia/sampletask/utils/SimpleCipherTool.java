package com.arvoia.sampletask.utils;

import java.util.stream.Stream;

public class SimpleCipherTool 
{
	private static final char LOWER_ALPHABET_INITIAL_VALUE = 96;
	private static final int ALPHABET_LENGHT = 26;
	private String key;
	private int keyIndex=0;
	

	public SimpleCipherTool(String key){
		this.key=key;
	}
	
	public String cipher(String text) {
		StringBuilder chipherText=new StringBuilder("");
		
		Stream<Character> stream = text.chars().mapToObj(c -> (char) c);
        
		stream.forEach(p -> chipherText.append(chiperLetter(p,getNext())));
		
		return chipherText.toString();
	}
	
	public String decipher(String cipheredText) {
		StringBuilder chipherText=new StringBuilder("");
		
		Stream<Character> stream = cipheredText.chars().mapToObj(c -> (char) c);
        
		stream.forEach(p -> chipherText.append(dechiperLetter(p,getNext())));
		
		return chipherText.toString();
	}
	
	public static char chiperLetter(Character p, int next) {
		if(!verifyEnglishLowercaseAlphabet(p)) {
			return p;
		}
		
		int value = p + next;
		if(value>'z') {
			value = value-ALPHABET_LENGHT;
		}
		
		return (char)(value);
	}

	public static char dechiperLetter(Character p, int next) {
		if(!verifyEnglishLowercaseAlphabet(p)) {
			return p;
		}

		int value = p - next;
		if(value<'a') {
			value = value+ALPHABET_LENGHT;
		}
		
		return (char)(value);
	}

	private int getNext() {
		if(keyIndex==key.length()) {
			keyIndex=0;
		}
		
		return key.charAt(keyIndex++) - LOWER_ALPHABET_INITIAL_VALUE;
	}

	public static boolean verifyEnglishLowercaseAlphabet(char p) {
		return p >= 'a' && p <= 'z';
	}
}
