package algrithm;

import org.junit.Test;

public class Sentence {

	/**
	 * 将段落分解为句子
	 * @param paragraph
	 * @return
	 */
	public static String[] sentenceBreak(String  paragraph){
		String sentences[] = paragraph.split("[。]");
		return sentences;
	}
	
	@Test
	public void testSentenceBreak(){
		String  paragraph="中草药大黄治疗疾病。大黄产地在中国。";
		String rst[] = sentenceBreak(paragraph);
		System.out.println("length: " + rst.length);
		for(String s: rst)
			System.out.println(s);
	}
}
