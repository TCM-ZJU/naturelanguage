package algrithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.chenlb.mmseg4j.example.MaxSplit;

import entity.TAssociativeRels;
import entity.TTerm;

public class AG1 {

/**
	 * 对指定的文献进行识别
	 * @param docSrc 原文献
	 * @param rstFile 结果文献
	 * @throws Exception
	 */
	public void ag3(String docSrc, String rstFile) throws Exception{
//		docSrc = "D:/docsrc/3.txt";
//		rstFile = "D:/docsrc/3_rst.txt";
		
		FileInputStream fins = new FileInputStream(docSrc);
		
		PrintWriter pw = new PrintWriter(new FileOutputStream(rstFile));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(fins));
		
		Map<Integer, String> tupeMap = TypeMap.getTypeMap();
		String raw;
		while((raw=br.readLine())!=null){
			String sents[] = Sentence.sentenceBreak(raw);
			for(String sent:sents){
				
				//调用分词程序来分词
				StringBuffer sb = new StringBuffer("=======================\n");
				sb.append(sent).append('\n');
				
				String words[] = MaxSplit.dosplit(sent);
				for(String word: words)
					sb.append(word).append(" | ");
				sb.append("\n------------------------------\n");
				
				//将中文概念名称识别为数据库中id
				List<TTerm> list = Word2DbId.identifyWordInDB(words);
				for(TTerm term: list)
					sb.append(term.getTermLabel()).append(":").append(term.getConceptId()).append(", ");
				sb.append("\n------------------------------\n");
				
				//将一组id号,进行识别
				List<TAssociativeRels> ascts= GraphIdentity.identitifyGraph(list);
				String graph = GraphIdentity.printGraph(ascts);
				sb.append(graph);
				sb.append("\n");
				
				//对需要预测的关键词对进行预测
				for(int i=0;i<list.size()-1;i++){
					TTerm term1 = list.get(i);
					TTerm term2 = list.get(i+1);
					//判断关系是否已经找到了
					boolean flag = isFound(term1.getConceptId(), term2.getConceptId(), ascts);
					if(flag==true)
						continue;
					sb.append(term1.getConceptId() +" " +term2.getConceptId()+" type: \n");
					Map<Integer, Integer> map = PredictAssociation.predict_hd(term1.getConceptId(), term2.getConceptId());
					for(int key : map.keySet()) {
					    int value = map.get(key);
						sb.append(" , ").append(key).append(" = ").append(value);
						
					    if(key != -1){
					    	sb.append('[').append(tupeMap.get(key)).append(']');
					    }
					}
					sb.append("\n");
				}
				
				System.out.println(sb);
				pw.println(sb);
				pw.flush();
			}
		
		}
		
		br.close();
	}

	@Test
	public void testAg3()throws Exception{
		String docDirName = "f:/docsrc/";
		File docDir = new File(docDirName);
		String [] files = docDir.list(new FilenameFilter(){
			public boolean accept(File dir, String name) {
				if(name.matches("\\d*\\.txt"))
					return true;
				else
					return false;
			}
		});
		
		
		if(files == null)
			System.out.println("没有目标文件，请检查路径！");
		else{
			System.out.println("共有 "+files.length+" 个目标文献：");
			RelTypes.init();
			Terms.init();
			for(String fileName:files){
				ag3(docDirName+fileName, docDirName+fileName+".rst");
			}
		}
		
	}

	//	@Test
	public void ag1() throws Exception{
		String docSrc = "F:/desk/论文/docsrc/1.txt";
		String rstFile = "F:/desk/论文/docrst";
		FileInputStream fins = new FileInputStream(docSrc);
		
		PrintWriter pw = new PrintWriter(new FileOutputStream(rstFile));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(fins));
		int count = 0;
		
		String raw;
		while((raw=br.readLine())!=null){
			String sents[] = Sentence.sentenceBreak(raw);
			for(String sent:sents){
				//调用分词程序来分词
				StringBuffer sb = new StringBuffer("=======================\n");
				sb.append(sent).append("\n");
				String words[] = MaxSplit.dosplit(sent);
				for(String word: words)
					sb.append(word).append(" | ");
				sb.append("\n------------------------------\n");
				
				List<TTerm> list = Word2DbId.identifyWordInDB(words);
				for(TTerm term: list)
					sb.append(term.getTermLabel()).append(":").append(term.getConceptId()).append(", ");
				sb.append("\n------------------------------\n");
				
				List<TAssociativeRels> ascts= GraphIdentity.identitifyGraph(list);
				String graph = GraphIdentity.printGraph(ascts);
				sb.append(graph);
				sb.append("\n");
				
				System.out.println(sb);
			}
		
		}
		
		br.close();
		
	}

	/**
		 * 对预测算法的验证
		 * @throws Exception
		 */
	//	@Test
		public void ag2() throws Exception{
			String docSrc = "F:/desk/论文/docsrc/1.txt";
			String rstFile = "F:/desk/论文/docrst/1_rst.txt";
			FileInputStream fins = new FileInputStream(docSrc);
			
			PrintWriter pw = new PrintWriter(new FileOutputStream(rstFile));
			
			BufferedReader br = new BufferedReader(new InputStreamReader(fins));
			int count = 0;
			
			String raw;
			while((raw=br.readLine())!=null){
				String sents[] = Sentence.sentenceBreak(raw);
				for(String sent:sents){
					System.out.println(sent);
					//调用分词程序来分词
					StringBuffer sb = new StringBuffer("=======================\n");
					
					String words[] = MaxSplit.dosplit(sent);
					for(String word: words)
						sb.append(word).append(" | ");
					sb.append("\n------------------------------\n");
					
					//将中文概念名称识别为数据库中id
					List<TTerm> list = Word2DbId.identifyWordInDB(words);
					for(TTerm term: list)
						sb.append(term.getTermLabel()).append(":").append(term.getConceptId()).append(", ");
					sb.append("\n------------------------------\n");
					
					//将一组id号,识别为
					List<TAssociativeRels> ascts= GraphIdentity.identitifyGraph(list);
	//				String graph = GraphIdentity.printGraph(ascts);
	//				sb.append(graph);
					sb.append("\n");
					
					//用找到的关系(type)来验证算法的正确性
					for(TAssociativeRels rel : ascts){
						sb.append(rel.getLeftConcId() +" "
								+rel.getRightConcId()+" type: "
								+ rel.getType()+" prddict: ");
						Map<Integer, Integer> map = PredictAssociation.predict(rel.getLeftConcId(), rel.getRightConcId());
						for(int key : map.keySet()) {
						    int value = map.get(key);
						    sb.append(" , ").append(key).append(" = ").append(value);
						}
						sb.append("\n");
					}
					
					System.out.println(sb);
					pw.println(sb);
					pw.flush();
				}
			
			}
			
			br.close();
			
		}

	/**
	 * 查看两个id号，是否一定在图ascts中有边联系
	 * @param id1
	 * @param id2
	 * @param ascts
	 * @return true 已经有边，false 没有边
	 */
	private boolean isFound(int id1, int id2, List<TAssociativeRels> ascts){
		for(TAssociativeRels ts: ascts){
			if(id1==ts.getLeftConcId() && id2==ts.getRightConcId())
				return true;
		}
		
		return false;
	}
	
}
