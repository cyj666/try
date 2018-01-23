package com.tryall.tool;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.tayall.test.SolrTest;
import com.tryall.pojo.User;

public class SolrUtil {
	/*private static final String url = "jdbc:sqlserver://192.168.2.106:1433;DatabaseName=JobsOtherweb51jobDB";  
    private static String user = "sa";  
    private static String password = "sa";  
    private String Corenum;  
    public static int JobsId = 219443;// start jobsid */ 
    public static HttpSolrServer solrServer = null;// new 
                                        
	
	static {
		// 创建solrserver对象：
		try {
			solrServer = new HttpSolrServer("http://localhost:8983/solr");
			solrServer.setConnectionTimeout(100);
			solrServer.setDefaultMaxConnectionsPerHost(100);
			solrServer.setMaxTotalConnections(100);
			System.out.println("创建成功");
		} catch (Exception e) {
			System.out.println("请检查服务器或端口是否开启!");
			e.printStackTrace();

		}
	}

	@SuppressWarnings("null")
	public static User query(int id) throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:"+id);
		solrQuery.setRows(10);
		User user = null;
		//List<User> list = new ArrayList<>();
		 SolrDocumentList docs = solrServer.query(solrQuery).getResults();  
         for (SolrDocument sd : docs) {  
        	 user = new User();
             System.out.println("id:"+sd.getFieldValue("id"));   
             System.out.println("name:"+sd.getFieldValue("name"));
             user.setUserId(Integer.decode(sd.getFieldValue("id").toString()));
             user.setUsername((String) sd.getFieldValue("name"));
            // list.add(user);
         }  
        //User user = (User) solrServer.query(solrQuery).getBeans(User.class);
         solrServer.shutdown();  
        // return list;
         return user;
	}
	
	public static void main(String[] args) throws SolrServerException {
		  System.out.println(query(1));
	}

}
