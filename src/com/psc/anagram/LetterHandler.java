package com.psc.anagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//letter的协助类
public class LetterHandler {

	//处理字符串，得到回文字符串组合(形式：分隔+组合)
	public static Map<String, Letter> handler(String anagram) {
		List<Letter> letterList = split(anagram);
		Map<String, Letter> letterMap = composite(letterList);
		return letterMap;
	}

	//分隔字符串
	private static List<Letter> split(String anagram) {
		List<Letter> letterList = new ArrayList<Letter>();
		for (int i = 0; i < anagram.length(); i++) {
			char c = anagram.charAt(i);
			Letter letter = new Letter(c + "");
			letterList.add(letter);
		}
		return letterList;
	}
	
	//排列组合字符串回文
	private static Map<String, Letter> composite(List<Letter> letterList) {
		Map<String, Letter> letterMap = new HashMap<String, Letter>();
		recComposite(letterList, letterMap, "", new int[]{});
		return letterMap;
	}
	
	//递归组合字符串回文
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

	//判断下标是否一样
	private static boolean isContainSameIndex(int sourceIndex, int... targetIndex) {
		for (int i = 0; i < targetIndex.length; i++) {
			int target = targetIndex[i];
			if (sourceIndex == target) {
				return true;
			}
		}
		return false;
	}

	//判断是否是最后一个字符
	private static boolean isTheLastChar(int[] targetIndex, List<Letter> letterList) {
		return (targetIndex.length + 1) == (letterList.size());
	}
	
	//将字符串排列结果添加到map中
	private static void addToMap(Map<String, Letter> letterMap, String composite) {
		letterMap.put(composite, new Letter(composite));
	}
	
	//组转下标数组
	private static int[] appendTargetIndexs(int beforeIndex, int[] targetIndex) {
		int[] targetIndexs = new int[targetIndex.length + 1];
		targetIndexs[0] = beforeIndex;
		for (int i = 0; i < targetIndex.length; i++) {
			targetIndexs[i + 1] = targetIndex[i];
		}
		return targetIndexs;
	}
	
	//展示
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
