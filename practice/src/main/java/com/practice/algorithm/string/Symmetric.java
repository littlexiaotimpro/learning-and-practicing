package com.practice.algorithm.string;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 求解字符串的最长对称字符串
 */
public class Symmetric {

	private static void findLongestSymmetricString(String source){
		List<String> answer = new ArrayList<>();
		String[] split = source.split("[^a-zA-Z]");
		for (String subSource : split) {
			handleSubSource(subSource,answer);
		}
		// 求多个字符字串的最长对称字符串
		int max = answer.get(0).length();
		for (String s : answer) {
			if (s.length() >= max) {
				max = s.length();
			}
		}
		// 使用JDK 8的流特性处理集合
		int finalMax = max;
		List<String> collect = answer.stream()
				.filter(item -> item.length() == finalMax)
				.distinct()
				.collect(Collectors.toList());
		System.out.println(collect);
	}

	/**
	 * 过滤掉特殊字符后对单个字符串进行处理，最后求所有字符串集合中的最长的结果
	 * 对称字符串的对称字符的索引之和相等，及，I(0)+I(n-1) = I(1)+I(n-2) = ......
	 * @param str    单个字串
	 * @param answer 结果集
	 */
	private static void handleSubSource(String str,List<String> answer){
		// 使用桶排序的特性，以字符作为集合的索引，将每个字符出现的索引位构成一个集合
		int length = str.length();
		int min = str.charAt(0);
		int max = str.charAt(0);
		for (int i = 1; i < length; i++) {
			if (min > str.charAt(i)) {
				min = str.charAt(i);
			}
			if (max < str.charAt(i)) {
				max = str.charAt(i);
			}
		}
		List<LinkedList<Integer>> tranList = new ArrayList<>(max - min + 1);
		for (int i=0;i<max-min+1;i++){
			tranList.add(new LinkedList<>());
		}
		int items = 0;
		for (int i = 0; i < length; i++) {
			LinkedList<Integer> list = tranList.get(str.charAt(i) - min);
			list.add(i);
			if (list.size()>items){
				items = list.size();
			}
		}
		// 所有字符只出现 1 次
		if (items <= 1) {
			for (LinkedList<Integer> list : tranList) {
				if (list.size() == 1) {
					answer.add(String.valueOf(str.charAt(list.get(0))));
				}
			}
		} else {
			// 对字符出现次数大于 1 的字符进行两两归并
			List<LinkedList<Integer>> result = new ArrayList<>();
			for (LinkedList<Integer> integers : tranList) {
				if (integers.size() > 1) {
					for (int i = 0; i < integers.size(); i++) {
						for (int j = i + 1; j < integers.size(); j++) {
							LinkedList<Integer> list = new LinkedList<>();
							list.add(integers.get(i));
							list.add(integers.get(j));
							result.add(list);
						}
					}
				}
			}
			handleResult(str, result, answer);
		}
	}

	/**
	 * 结果集处理
	 */
	private static void handleResult(String str,List<LinkedList<Integer>> result,List<String> answer){
		int maxLength = 2;
		for (LinkedList<Integer> list : result) {
			int sum = list.getFirst() + list.getLast();
			int index = result.indexOf(list);
			for (int i = index + 1; i < result.size(); i++) {
				LinkedList<Integer> list1 = result.get(i);
				int sum1 = list1.getFirst() + list1.getLast();
				if (sum == sum1) {
					list.addAll(list1);
					list.sort(Comparator.comparing(item -> item));
					if (list.size() > maxLength) {
						maxLength = list.size();
					}
				}
			}
		}
		for (LinkedList<Integer> list : result) {
			if (list.size() >= maxLength) {
				int right = list.get(maxLength / 2);
				int left = list.get(maxLength / 2 - 1);
				if (right - left > 1) {
					StringBuilder stringBuilder;
					LinkedList<Integer> res;
					for (int i = left + 1; i < right; i++) {
						stringBuilder = new StringBuilder();
						res = new LinkedList<>(list);
						res.add(maxLength / 2, i);
						for (Integer re : res) {
							stringBuilder.append(str.charAt(re));
						}
						answer.add(stringBuilder.toString());
					}
				} else {
					StringBuilder stringBuilder = new StringBuilder();
					for (Integer re : list) {
						stringBuilder.append(str.charAt(re));
					}
					answer.add(stringBuilder.toString());
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO 输出最长对称字符串：goog
		String input1 = "googlegasgasg";
		findLongestSymmetricString(input1);

		// TODO 输出3个最长对称字符串：aba/aca/ada
		String input2 = "abcdaa";
		findLongestSymmetricString(input2);

		// TODO 输出2个最长对称字符串：pop/upu，不会输出特殊字符的对称字符串p-p
		String input3 = "pop-upu-ppp";
		findLongestSymmetricString(input3);
	}
}
