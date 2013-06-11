package algrithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import hb.HibernateSessionFactory;

import org.hibernate.Session;
import org.junit.Test;

import util.Search;
import util.Sort;

/**
 * 预测两个词之间的关联关系
 * @author Administrator
 *
 */
public class PredictAssociation {

	/**
	 * 高效版，对子节点之间的关系统计进行优化，因为子节点可能达到5000*5000数量级
	 * @param id1
	 * @param id2
	 * @return Map<Integer, Integer> 说明:<type, quantity> -1表示没有关系的数量, 
	 * @throws SQLException
	 */
	public static Map<Integer, Integer> predict_hd(int id1, int id2) throws SQLException{
		//找到对应的父概念，可能有多个父概念
		List<Integer> flist1 = findFatherIds(id1);
		List<Integer> flist2 = findFatherIds(id2);
		System.out.println("父节点1大小： "+ flist1.size());
		System.out.println("父节点2大小： "+ flist1.size());
		
		//多个父节点的对应的子节点
		List<Integer> clist1 = new LinkedList<Integer>();
		for(int fid : flist1){
			List<Integer> list= findChildIds(fid);
			clist1.addAll(list);
		}
		
		//多个父节点的对应的子节点
		List<Integer> clist2 = new LinkedList<Integer>();
		for(int fid : flist2){
			List<Integer> list= findChildIds(fid);
			clist2.addAll(list);
		}
		
		System.out.println("子节点1数量：" + clist1.size());
		System.out.println("子节点2数量：" + clist2.size());
		if(clist1.size()==0 || clist2.size()==0)
			return new HashMap<Integer, Integer>();
		
		//对子节点进行排序
		Sort.insertsort(clist1);
		Sort.insertsort(clist2);
		
//		for(int i: clist1)
//			System.out.print(" "+i);
//		System.out.println();
		
		//开始统计两组子节点之间的各种关系的数量 <type, quantity>
		Map<Integer, Integer> relationCount = new HashMap<Integer, Integer>();
		relationCount.put(-1, clist1.size()*clist2.size());
		
		List<Integer> rlist = new LinkedList<Integer>();
		for(int cid1 : clist1){
				rlist = findRelations(cid1, clist2);
				for(int type: rlist){
					Integer c = relationCount.get(type);
					if(c==null)
						relationCount.put(type, 1);
					else
						relationCount.put(type, relationCount.get(type)+1);
				}
		}
		//TODO 加入没有语义关系的数量，-1类型
//		System.out.println(relationCount);
		return relationCount;
	}
	
	/**
	 * 效率较低版
	 * @param id1
	 * @param id2
	 * @return Map<Integer, Integer> 说明:<type, quantity> -1表示没有关系的数量
	 * @throws SQLException
	 */
	public static Map<Integer, Integer> predict(int id1, int id2) throws SQLException{
		//找到对应的父概念，可能有多个父概念
		List<Integer> flist1 = findFatherIds(id1);
		List<Integer> flist2 = findFatherIds(id2);
		System.out.println("父节点1大小： "+ flist1.size());
		System.out.println("父节点2大小： "+ flist1.size());
		
		//多个父节点的对应的子节点
		List<Integer> clist1 = new LinkedList<Integer>();
		for(int fid : flist1){
			List<Integer> list= findChildIds(fid);
			clist1.addAll(list);
		}
		
		//多个父节点的对应的子节点
		List<Integer> clist2 = new LinkedList<Integer>();
		for(int fid : flist2){
			List<Integer> list= findChildIds(fid);
			clist2.addAll(list);
		}
		
		System.out.println("子节点1数量：" + clist1.size());
		System.out.println("子节点2数量：" + clist2.size());
		
		
		//开始统计两组子节点之间的各种关系的数量 <type, quantity>
		Map<Integer, Integer> relationCount = new HashMap<Integer, Integer>();
		List<Integer> rlist = new LinkedList<Integer>();
		for(int cid1 : clist1){
			for(int cid2:clist2){
				rlist = findRelations(cid1, cid2);
				for(int type: rlist){
					Integer c = relationCount.get(type);
					if(c==null)
						relationCount.put(type, 1);
					else
						relationCount.put(type, relationCount.get(type)+1);
				}
			}
		}
		//TODO 加入没有语义关系的数量，-1类型
//		System.out.println(relationCount);
		return relationCount;
	}
	
	/**
	 * 这个方法与上面的统计父节点的子节点的语义关系的方法不同，该方法统计父节点之间的语义关系
	 * @param id1
	 * @param id2
	 * @return Map<Integer, Integer> 说明:<type, quantity> -1表示没有关系的数量
	 * @throws SQLException
	 */
	public static Map<Integer, Integer> predict_1(int id1, int id2) throws SQLException{
		//找到对应的父概念，可能有多个父概念
		List<Integer> flist1 = findFatherIds(id1);
		List<Integer> flist2 = findFatherIds(id2);
		List<Integer> rlist = new LinkedList<Integer>();
		
		Map<Integer, Integer> relationCount = new HashMap<Integer, Integer>();
		
		for(int cid1 : flist1){
			for(int cid2:flist2){
				rlist = findRelations(cid1, cid2);
				for(int type: rlist){
					Integer c = relationCount.get(type);
					if(c==null)
						relationCount.put(type, 1);
					else
						relationCount.put(type, relationCount.get(type)+1);
				}
			}
		}
		return relationCount;
	}
	
	private static List<Integer> findFatherIds(int childId)throws SQLException{
		Session session = HibernateSessionFactory.getSession();
		Connection connection = session.connection();
		
		PreparedStatement statement = connection.prepareStatement("select parent_id from t_concept_rels where CHILD_ID = ?");
		statement.setInt(1, childId);
		ResultSet rs = statement.executeQuery();
		
		List<Integer> flist = new LinkedList<Integer>();
		
		while(rs.next()){
			flist.add(rs.getInt(1));
		}
		
//		System.out.println(flist.size());
//		System.out.println(flist.get(0));
		rs.close();
		statement.close();
		connection.close();
		return flist;
	}
	
	private static List<Integer> findChildIds(int fid)throws SQLException{
		Session session = HibernateSessionFactory.getSession();
		Connection connection = session.connection();
		
		PreparedStatement statement = connection.prepareStatement("select CHILD_ID from t_concept_rels where parent_id = ?");
		statement.setInt(1, fid);
		ResultSet rs = statement.executeQuery();
		
		List<Integer> clist = new LinkedList<Integer>();
		
		while(rs.next()){
			clist.add(rs.getInt(1));
		}
		
//		System.out.println(clist.size());
//		System.out.println(clist.get(0));
		rs.close();
		statement.close();
		connection.close();
		return clist;
	}
	
	/**
	 * 找到两个概念之间的关系
	 * @param id1
	 * @param id2
	 * @return
	 * @throws SQLException
	 */
	public static List<Integer> findRelations(int id1, int id2) throws SQLException{
		Session session = HibernateSessionFactory.getSession();
		Connection connection = session.connection();
		
		PreparedStatement statement = connection.prepareStatement("select TYPE from t_associative_rels where LEFT_CONC_ID = ? and RIGHT_CONC_ID = ?");
		statement.setInt(1, id1);
		statement.setInt(2, id2);
		ResultSet rs = statement.executeQuery();
		
		List<Integer> typelist = new LinkedList<Integer>();
		
		while(rs.next()){
			typelist.add(rs.getInt(1));
		}
//		System.out.println(typelist.size());
		rs.close();
		statement.close();
		connection.close();
		return typelist;
	}
	
	/**
	 * 找到两个概念之间的关系
	 * @param id1
	 * @param id2
	 * @return
	 * @throws SQLException
	 */
	public static List<Integer> findRelations(int id1, List<Integer> list) throws SQLException{
		Session session = HibernateSessionFactory.getSession();
		Connection connection = session.connection();
		
		PreparedStatement statement = connection.prepareStatement("select TYPE,RIGHT_CONC_ID from t_associative_rels where LEFT_CONC_ID = ? order by RIGHT_CONC_ID");
		statement.setInt(1, id1);
		ResultSet rs = statement.executeQuery();
		
		List<Integer> typelist = new LinkedList<Integer>();
		int minId = list.get(0);
		int maxId = list.get(list.size()-1);
		while(rs.next()){
			int trid = rs.getInt(2);
			if(trid < minId)
				continue;
			
			if(maxId < trid)
				break;
//			boolean
			
			if(Search.binarysearch(trid, list) != -1){
				typelist.add(rs.getInt(1));//将关系添加进结果向量
			}
			
		}
//		System.out.println(typelist.size());
		rs.close();
		statement.close();
		connection.close();
		return typelist;
	}
	
	
	
//	@Test
	public void testPredict() throws SQLException{
//		predict(33645,10);
//		predict(164718, 163750);
		predict(164718, 6108);
	}
	
//	@Test
	public void testFindChildIds() throws SQLException{
		findChildIds(77183);
	}
	
//	@Test
	public void testFindRelations() throws SQLException{
		findRelations(163750, 164718);
		findRelations(164718, 163750);
	}
	
//	@Test
	public void testPressureOfPredict() throws SQLException {
		for (int i = 0; i < 20; i++) {
			predict(16306, 15854);
		}
	}
	
//	@Test
	public void testPressure() throws SQLException {
		for (int i = 0; true; i++) {
//			findFatherIds(15854);
			findRelations(16306, 15854);
			System.out.println(i);
		}
	}
	
	
	/**
	 * 验证算法是否有效,
	 * @throws SQLException 
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testValidity() throws SQLException, FileNotFoundException{
		PrintWriter pw = new PrintWriter(new File("d://test_validate.txt"));
		int max = 10000;
		int count=1;
		for(int i=1;i<max;i++){
			for(int j=i+1;j<max;j++){
				List<Integer> list = findRelations(i, j);
				if(list.size()!=0){
					StringBuffer sb = new StringBuffer();
					
					//实际关系
					for(int type:list)
						sb.append(" ").append(type);
					
					sb.append(" : ");
					
					//预测关系
					Map<Integer, Integer> map = predict_1(i, j);
					for(int type : map.keySet()){
						int quantity = map.get(type);
						sb.append(" ").append(type).append(",").append(quantity);
					}
					pw.println(sb);
				}
				pw.flush();
				System.out.println(count++);
			}
		}
		pw.close();
	}
}
