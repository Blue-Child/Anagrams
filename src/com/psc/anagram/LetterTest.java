package com.psc.anagram;

import java.util.Map;

import org.junit.Test;

public class LetterTest {
	
	@Test
	public void testAnagramWithFour() {
		String anagram = "biro";
		Map<String, Letter> letterMap = LetterHandler.handler(anagram);
		LetterHandler.show(letterMap);
	}
	
	@Test
	public void testAnagramWithFourHasSameLetter() {
		String anagram = "bibo";
		Map<String, Letter> letterMap = LetterHandler.handler(anagram);
		LetterHandler.show(letterMap);
	}
	
	@Test
	public void testAnagramWithFive() {
		String anagram = "biroe";
		Map<String, Letter> letterMap = LetterHandler.handler(anagram);
		LetterHandler.show(letterMap);
	}
}
