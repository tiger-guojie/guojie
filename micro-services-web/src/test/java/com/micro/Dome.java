package com.micro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

public class Dome {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		list.add("g");
		list.add("h");
		list.add("i");
		list.add("j");
		list.add("k");
		list.add("l");
		list.add("m");
		
		List<Integer> list2=new ArrayList<>();
		list2.add(0);
		list2.add(1);
		list2.add(2);
		list2.add(3);
		list2.add(4);
		list2.add(5);
		list2.add(6);
		list2.add(7);
		list2.add(8);
		list2.add(9);
		list2.add(10);
		list2.add(11);
		list2.add(12);
		
		System.out.println(groupList(list,list2));
	}
     /**
        * 实现两个list交叉循环遍历
      * @param list
      * @param list2
      * @return
      */
	public static Map<String, Object> groupList(List<String> list,List<Integer> list2) {

		int listSize = list.size();
		int toIndex = 4;
		int j=0;
		int listSize2 = list2.size();
		int toIndex2 = 2;
		Map<String, Object> map = new HashMap<>(); // 用map存起来新的分组后数据
		int keyToken = 0;
		int keyToken2 = 0;
		for (int i = 0; i < listSize; i += 4) {
			if (i + 4 > listSize) { // 作用为toIndex最后没有4条数据则剩余几条newList中就装几条
				toIndex = listSize - i;
			}
			List<String> newList = list.subList(i, i + toIndex);
			System.out.println(JSONArray.fromObject(newList));
			map.put("keyStringName" + keyToken, newList);			
			while (j < listSize2) {
				if (j + 2 > listSize2) { // 作用为toIndex最后没有2条数据则剩余几条newList中就装几条
					toIndex2 = listSize2 - j;
				}
				List<Integer> newList2 = list2.subList(j, j + toIndex2);
				System.out.println(JSONArray.fromObject(newList2));
				map.put("keyIntegerName" + keyToken2, newList2);
				//keyToken++;
				j += 2;
				break;
			}
			keyToken++;
			keyToken2++;
			continue;
		}
		
		while (j < listSize2) {
			if (j + 2 > listSize2) { // 作用为toIndex最后没有2条数据则剩余几条newList中就装几条
				toIndex2 = listSize2 - j;
			}
			List<Integer> newList2 = list2.subList(j, j + toIndex2);
			System.out.println(JSONArray.fromObject(newList2));
			map.put("keyIntegerName" + keyToken2, newList2);
			j += 2;
			keyToken2++;
		}
		return map;
	}
}
