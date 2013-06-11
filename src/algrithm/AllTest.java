package algrithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import hb.HibernateSessionFactory;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import entity.TAssociativeRels;
import entity.TCl25;
import entity.TConceptRels;
import entity.TTerm;

public class AllTest {

	/**
	 * 指定了两个dbid以后, 从数据库查找两个dbid之间的关系(概念的从属关系,父子关系)
	 */
//	@Test
	public void test1(){
		Session session = HibernateSessionFactory.getSession();
		
		Query query = session.createQuery("from TConceptRels where childId = ? and parentId = ?");
		query.setParameter(0, 77673);
		query.setParameter(1, 77623);
		List<TConceptRels> list = query.list();
		System.out.println(list.size());
		TConceptRels conceptRels = list.get(0);
		
//		System.out.println(conceptRels.getChildTerm().getConceptId());
//		System.out.println(conceptRels.getParentTerm().getConceptId());
		
		query.setParameter(0, 77623);
		query.setParameter(1, 77673);
		 list = query.list();
		System.out.println(list.size());
		conceptRels = list.get(0);
		
//		System.out.println(conceptRels.getChildTerm().getConceptId());
//		System.out.println(conceptRels.getParentTerm().getConceptId());
	}
	
	/**
	 *指定了两个dbid以后, 从数据库查找两个dbid之间的关系(概念之间的关系)
	 */
//	@Test
	public void test2(){
		Session session = HibernateSessionFactory.getSession();
		
		Query query = session.createQuery("from TAssociativeRels where leftConcId = ? and rightConcId = ?");
		query.setParameter(0, 77673);
		query.setParameter(1, 77623);
		List<TAssociativeRels> list = query.list();
		System.out.println(list.size());
		TAssociativeRels conceptRels = list.get(0);
		
		System.out.println(conceptRels.getLeftTerm().getConceptId());
		System.out.println(conceptRels.getRightTerm().getConceptId());
	}
	
	
	/**
	 * 调用方法来识别概念之间的关系
	 * @throws Exception
	 */
	@Test
	public void testVector() throws Exception{
		
		FileInputStream fins = new FileInputStream("d:/w_2_concept_id.txt");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(fins));
		int count = 0;
		
		String raw;
		while((raw=br.readLine())!=null){
			
			identify(raw);
			System.out.println();
//			count++;
//			System.out.println(count);
//			if(count++ >10)
//				break;
		}
		br.close();
		
	}
	
	/**
	 * 识别概念之间的关系
	 * @param raw 概念词在数据库中的id
	 */
	public void identify(String raw){
		String sv [] = raw.split(",");
		
		//TODO 没有用事务操作来释放资源, 不知道大量执行后会不会内存溢出
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from TAssociativeRels where leftConcId = ? and rightConcId = ?");
		
		for(int i=0; i<sv.length; i++){
			for(int j=0;j<sv.length;j++){
				if(i == j)
					continue;
				query.setParameter(0, Integer.parseInt(sv[i]));
				query.setParameter(1, Integer.parseInt(sv[j]));
				List<TAssociativeRels> list = query.list();
				if(list.size() > 0){
					TAssociativeRels conceptRels = list.get(0);
					
					System.out.print(conceptRels.getLeftConcId() + " [");
//					Set<TTerm> set = conceptRels.getLeftTerms();
//					Object [] tterms = set.toArray();
//					for(Object tTerm : tterms){
//						TTerm tTerm2 = (TTerm)tTerm; 
//						System.out.print(tTerm2.getTermLabel()+ " ");
//					}
					System.out.print(Terms.mapLabel(conceptRels.getLeftConcId()));
					System.out.print("] ");
					
					System.out.print(conceptRels.getRightConcId()+ " [");
//					set = conceptRels.getRightTerms();
//					tterms = set.toArray();
//					for(Object tTerm : tterms){
//						TTerm tTerm2 = (TTerm)tTerm; 
//						System.out.print(tTerm2.getTermLabel()+ " ");
//					}
					System.out.print(Terms.mapLabel(conceptRels.getRightConcId()));
					System.out.print("] ");
					
					System.out.print(conceptRels.getType()+" [");
//					Set<TCl25> set2 = conceptRels.getTypes();
//					Object[] types = set2.toArray();
//					for(Object typy : types){
//						TCl25 typy2 = (TCl25)typy; 
//						System.out.print(typy2.getFCode()+ " ");
//					}
					System.out.print(RelTypes.mapRelName(conceptRels.getType()));
					
					System.out.print("] ");
					
					System.out.println();
				}
			}
		}
	}
	
	/**
	 * 将文件中的中文概念名转换成数据库中对应的id
	 * @throws Exception
	 */
//	@Test
	public void test4() throws Exception{
		//营卫 补泻 营气 卫气 补泻 方法
		//转换
		String raw = "";
		FileInputStream fins = new FileInputStream("d:/w_2.txt");
		FileOutputStream fouts = new FileOutputStream("d:/w_2_concept_id.txt");
		PrintWriter pw = new PrintWriter(fouts);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(fins));
		int count = 0;
		
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from TTerm where termLabel = ?");
		
		while((raw=br.readLine())!=null){
			
			String words[] = raw.split(" ");
			String rst = "";
			for(String word: words){
				query.setParameter(0, word);
				List<TTerm> list = query.list();
				if(list.size() > 0){
					TTerm term = list.get(0);
					rst += term.getConceptId()+",";
//					System.out.println(term.getTermLabel());
//					System.out.println(term.getConceptId());
				}
			}
//			System.out.println(rst);
			pw.println(rst);
			count++;
			System.out.println(count);
//			if(count++ >10)
//				break;
		}
		
		br.close();
		pw.close();
	}
}
