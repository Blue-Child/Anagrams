package com.psc.anagram;

//���ʻ���ĸ
public class Letter {
	
	private String content;
	
	public Letter(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return this.getContent();
	}

	public String getContent() {
		return content;
	}

	
}
