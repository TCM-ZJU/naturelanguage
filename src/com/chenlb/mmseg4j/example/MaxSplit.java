package com.chenlb.mmseg4j.example;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MaxSplit extends Complex{

	/**
	 * 进行分词,存中医药的词汇,去除了单个的词汇
	 * @param raw
	 * @return
	 * @throws IOException
	 */
	public static String[] dosplit(String raw)throws IOException{
//		MyMaxWord maxWord = new MyMaxWord();
//		String rst = maxWord.segWords(raw, "|");
		Complex complex = new Complex();
		String rst = complex.segWords(raw, "|");
		
//		System.out.println(rst);
		
		String []ss = rst.split("\\|");
		
		//去除单个词汇
		List<String> list = new LinkedList<String>();
		
		for(String s:ss)
			if(s.length() > 1)
				list.add(s);
		
		ss = new String[list.size()];
		for (int i = 0; i < ss.length; i++) {
			ss[i] = list.get(i);
		}
			   
		return ss;
	}
}
