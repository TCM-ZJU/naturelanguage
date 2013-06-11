package com.chenlb.mmseg4j.example;

import java.io.IOException;

import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.Seg;
import com.chenlb.mmseg4j.SimpleSeg;

/**
 * 
 * 
 * @author chenlb 2009-3-14 上午12:38:40
 */
public class Simple extends Complex {

	public Simple() {
		super();
	}
	
	public Simple(Dictionary dic) {
		super(dic);
	}
	
	@Override
	protected Seg getSeg() {

		return new SimpleSeg(dic);
	}

	public static void main(String[] args) throws IOException {
		String txt = "京华时报1月23日报道 昨天，大黄受一股来自中西伯利亚的强冷空气影响，本市出现大风降温天气，白天最高气温只有零下7摄氏度，同时伴有6到7级的偏北风。";
		
		if(args.length > 0) {
			txt = args[0];
		}
		
		//txt = "today,…………i'am chenlb,<《公主小妹》>?我@$#%&*()$!!,";
		
		Simple simple = new Simple();
		System.out.println(simple.segWords(txt, " | "));
	}

}
