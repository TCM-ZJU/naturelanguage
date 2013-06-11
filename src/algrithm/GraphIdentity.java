package algrithm;

import hb.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.sun.org.apache.regexp.internal.recompile;

import entity.TAssociativeRels;
import entity.TCl25;
import entity.TTerm;

public class GraphIdentity {

	/**
	 * 对于输入的几个节点, 在数据库进行关系的查找. 返回关系的列表
	 * @param terms
	 * @return
	 */
	public static List<TAssociativeRels> identitifyGraph(List<TTerm> terms){
		
		ArrayList<TAssociativeRels> rst = new ArrayList<TAssociativeRels>();
		
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from TAssociativeRels where leftConcId = ? and rightConcId = ?");
		
		for(int i=0; i<terms.size(); i++){
			for(int j=0;j<terms.size();j++){
				if(i == j)
					continue;
				
				//在数据库中找到两个概念之间的关系
				query.setParameter(0, terms.get(i).getConceptId());
				query.setParameter(1, terms.get(j).getConceptId());
				List<TAssociativeRels> list = query.list();
				
				if(list.size() > 0){
					TAssociativeRels conceptRels = list.get(0);
					rst.add(conceptRels);
				}
			}
		}
		
		return rst;
	}
	
	/**
	 * 输入关系的集合, 格式化为字符串进行输出
	 * @param list
	 * @return
	 */
	public static String printGraph(List<TAssociativeRels> list){
		
		StringBuffer sb = new StringBuffer();
		
		for(TAssociativeRels conceptRels:list){
			sb.append(conceptRels.getLeftConcId() + " [");
//			Set<TTerm> set = conceptRels.getLeftTerms();
//			Object [] tterms = set.toArray();
//			for(Object tTerm : tterms){
//				TTerm tTerm2 = (TTerm)tTerm; 
//				sb.append(tTerm2.getTermLabel()+ " ");
//			}
			sb.append(Terms.mapLabel(conceptRels.getLeftConcId()));
			sb.append("] ");
			
			sb.append(conceptRels.getRightConcId()+ " [");
//			set = conceptRels.getRightTerms();
//			tterms = set.toArray();
//			for(Object tTerm : tterms){
//				TTerm tTerm2 = (TTerm)tTerm; 
//				sb.append(tTerm2.getTermLabel()+ " ");
//			}
			sb.append(Terms.mapLabel(conceptRels.getRightConcId()));
			sb.append("] ");
			
			sb.append(conceptRels.getType()+" [");
//			Set<TCl25> set2 = conceptRels.getTypes();
//			Object[] types = set2.toArray();
//			for(Object typy : types){
//				TCl25 typy2 = (TCl25)typy; 
//				sb.append(typy2.getFCode()+ " ");
//			}
			sb.append(RelTypes.mapRelName(conceptRels.getType()));
			sb.append("] ");
			
			sb.append("\n");
		}
//		System.out.println(sb);
		return sb.toString();
		
	}
	
//	@Test
	public void testIdentifyGraph(){
		List<TTerm> list = new ArrayList<TTerm>();
		
		TTerm term = new TTerm();
		term.setConceptId(164427);
		list.add(term);
		
		term = new TTerm();
		term.setConceptId(33606);
		list.add(term);
		
		term = new TTerm();
		term.setConceptId(166999);
		list.add(term);
		
		term = new TTerm();
		term.setConceptId(71446);
		list.add(term);
		
		List<TAssociativeRels> rst = identitifyGraph(list);
		
		System.out.println(rst.size());
		
	}
	
	@Test
	public void testPrintGraph(){
		List<TTerm> list = new ArrayList<TTerm>();
		
		TTerm term = new TTerm();
		term.setConceptId(164427);
		list.add(term);
		
		term = new TTerm();
		term.setConceptId(33606);
		list.add(term);
		
		term = new TTerm();
		term.setConceptId(166999);
		list.add(term);
		
		term = new TTerm();
		term.setConceptId(71446);
		list.add(term);
		
		List<TAssociativeRels> rst = identitifyGraph(list);
		
		System.out.println(rst.size());
		
		printGraph(rst);
	}
}
