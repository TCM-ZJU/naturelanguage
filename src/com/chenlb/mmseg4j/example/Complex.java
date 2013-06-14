package com.chenlb.mmseg4j.example;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import com.chenlb.mmseg4j.Chunk;
import com.chenlb.mmseg4j.ComplexSeg;
import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.MMSeg;
import com.chenlb.mmseg4j.Seg;
import com.chenlb.mmseg4j.Chunk.Word;

public class Complex {

	protected Dictionary dic;
	
	public Complex() {
		dic = new Dictionary();
	}

	public Complex(Dictionary dic) {
		this.dic = dic;
	}

	protected Seg getSeg() {
		return new ComplexSeg(dic);
	}
	
	public String segWords(Reader input, String wordSpilt) throws IOException {
		StringBuilder sb = new StringBuilder();
		Seg seg = getSeg();
		MMSeg mmSeg = new MMSeg(input, seg);
		Chunk chunk = null;
		boolean first = true;
		while((chunk=mmSeg.next())!=null) {
			for(int i=0; i<chunk.getCount(); i++) {
				Word word = chunk.getWords()[i];
				if(!first) {
					sb.append(wordSpilt);
				}
				String w = word.getString();
				sb.append(w);
				first = false;
			}
		}
		return sb.toString();
	}
	
	public String segWords(String txt, String wordSpilt) throws IOException {
		return segWords(new StringReader(txt), wordSpilt);
	}
	
	public static void main(String[] args) throws IOException {
//		String txt = "京华时报1月23日报道 昨天，受一股来自中西伯利亚的强冷空气影响，本市出现大风降温天气，白天最高气温只有零下7摄氏度，同时伴有6到7级的偏北风。";
		//String txt = "麦芽茵陈茶产于北京，属于理气方，可以治疗疏肝理气消食退黄";
		//String txt = "ABS给雄性大鼠补肾阳中药淫羊藿和补肾阴中药女贞子,从分子水平探讨补肾中药对雄性大鼠杏仁核和皮质顶叶雌激素受体(estrogen receptor,EP)mRNA的调节表达差异,采用反转录聚合酶链反应(RT-PCR)方法检测雌激素受体ERα和ERβmRNA表达量。结果:淫羊藿组、女贞子组大鼠杏仁核、皮质顶叶雌激素受体ERαmRNA表达量无明显变化,而淫羊藿组杏仁核、皮质顶叶雌激素受体ERβmRNA表达量高于女贞子组且均高于空白对照组(P<0.01),女贞子杏仁核ERβmRNA表达量高于空白对照组(P<0.05)。说明补肾中药可能对ERβ具有调节作用。实验结果与“对雌激素的调节补肾阴药物";
		
		String txt = "人参有“补五脏、安精神、定魂魄、止惊悸、除邪气、明目开心益智”的功效，“久服轻身延年”。";
		
		if(args.length > 0) {
			txt = args[0];
		}
		
		//txt = "today,…………i'am chenlb,<《公主小妹》>?我@$#%&*()$!!,";
		Complex complex = new Complex();
		System.out.println(complex.segWords(txt, " | "));
	}

}
