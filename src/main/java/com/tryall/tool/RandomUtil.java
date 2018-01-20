package com.tryall.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class RandomUtil {

	
	/**
	 * 取10个不重复的随机数
	 */
	@Test
	public static List<Integer> getRandom(int bookCount) {
		List<Integer> list =  new ArrayList<>();
		int r ;
		for(int i=0;i<10;i++) {
			r =new Random().nextInt(bookCount-1); //随机数范围
			if (!list.contains(r+1)) {
				list.add(r+1);
			}else {
				i--;                         //当包含重复数字时，回退一位重新开始，确保10个数字
				continue;
			}
			
		}
	//	System.out.println(list.toString());
		return list;
		//return 0;
	}
}
