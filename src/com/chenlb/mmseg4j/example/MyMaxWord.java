package com.chenlb.mmseg4j.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.MaxWordSeg;
import com.chenlb.mmseg4j.Seg;

public class MyMaxWord extends Complex {

	public MyMaxWord() {
		super();
	}

	public MyMaxWord(Dictionary dic) {
		super(dic);
	}

	@Override
	protected Seg getSeg() {

		return new MaxWordSeg(dic);
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("d:/_2.txt")));
		PrintWriter pw = new PrintWriter(new FileOutputStream("d:/w_2.txt"));
		
		MyMaxWord maxWord = new MyMaxWord();
		Map<String, Integer> map = new HashMap<String, Integer>();
		int wc = 0;
		String str ;
		while((str=br.readLine())!=null){
			String rst = maxWord.segWords(str, "|");
			String []ss = rst.split("\\|");
			for(String s : ss)
				if(s.length()>1){
					Integer wt = map.get(s);
					if(wt == null)
						map.put(s, ++wc);
					pw.print(map.get(s)+" ");
				}
			
			pw.println();
//			for(String s : ss)
//				System.out.println(s);
		}
		
		pw.close();
		br.close();
		System.out.println(map);
	}
}
