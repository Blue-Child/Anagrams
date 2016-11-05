package com.psc.anagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//letter��Э����
public class LetterHandler {

	//�����ַ������õ������ַ������(��ʽ���ָ�+���)
	public static Map<String, Letter> handler(String anagram) {
		List<Letter> letterList = split(anagram);
		Map<String, Letter> letterMap = composite(letterList);
		return letterMap;
	}

	//�ָ��ַ���
	private static List<Letter> split(String anagram) {
		List<Letter> letterList = new ArrayList<Letter>();
		for (int i = 0; i < anagram.length(); i++) {
			char c = anagram.charAt(i);
			Letter letter = new Letter(c + "");
			letterList.add(letter);
		}
		return letterList;
	}
	
	//��������ַ�������
	private static Map<String, Letter> composite(List<Letter> letterList) {
		Map<String, Letter> letterMap = new HashMap<String, Letter>();
		recComposite(letterList, letterMap, "", new int[]{});
		return letterMap;
	}
	
	//�ݹ�����ַ�������
	private static void recComposite(List<Letter> letterList, Map<String, Letter> letterMap, String beforeStr, int[] targetIndex) {
		for (int k = 0; k < letterList.size(); k++) {
			String composite = "";
			if (!isContainSameIndex(k, targetIndex)) {
				composite = beforeStr + letterList.get(k);  
				if (isTheLastChar(targetIndex, letterList)) {
					addToMap(letterMap, composite);
					break;
				} else {
					int[] targetIndexs = appendTargetIndexs(k, targetIndex);
					recComposite(letterList, letterMap, composite, targetIndexs);
				}
			} else {
				continue;
			}
		}
	}

	//�ж��±��Ƿ�һ��
	private static boolean isContainSameIndex(int sourceIndex, int... targetIndex) {
		for (int i = 0; i < targetIndex.length; i++) {
			int target = targetIndex[i];
			if (sourceIndex == target) {
				return true;
			}
		}
		return false;
	}

	//�ж��Ƿ������һ���ַ�
	private static boolean isTheLastChar(int[] targetIndex, List<Letter> letterList) {
		return (targetIndex.length + 1) == (letterList.size());
	}
	
	//���ַ������н����ӵ�map��
	private static void addToMap(Map<String, Letter> letterMap, String composite) {
		letterMap.put(composite, new Letter(composite));
	}
	
	//��ת�±�����
	private static int[] appendTargetIndexs(int beforeIndex, int[] targetIndex) {
		int[] targetIndexs = new int[targetIndex.length + 1];
		targetIndexs[0] = beforeIndex;
		for (int i = 0; i < targetIndex.length; i++) {
			targetIndexs[i + 1] = targetIndex[i];
		}
		return targetIndexs;
	}
	
	//չʾ
	public static void show(Map<String, Letter> letterMap) {
		int n = 0;
		for (String key : letterMap.keySet()) {
			Letter letter = letterMap.get(key);
			System.out.print(letter.toString() + "	");
			if ((++n % 6) == 0) {
				System.out.println();
			}
		}
	}
}
