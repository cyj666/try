package com.tayall.test;

import java.util.Map;
import java.util.Set;

import javax.management.Query;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.alibaba.druid.sql.ast.statement.SQLWithSubqueryClause.Entry;
import com.tryall.pojo.User;

public class SolrTest {

	private static final String url = "jdbc:sqlserver://192.168.2.106:1433;DatabaseName=JobsOtherweb51jobDB";  
    private static String user = "sa";  
    private static String password = "sa";  
    private String Corenum;  
    public static int JobsId = 219443;// start jobsid  
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

	public static void query() throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("name:admin");
		solrQuery.setRows(10);
		
		 SolrDocumentList docs = solrServer.query(solrQuery).getResults();  
         for (SolrDocument sd : docs) {  
             System.out.println("id:"+sd.getFieldValue("id"));   
             System.out.println("name:"+sd.getFieldValue("name"));
         }  
		long url = solrServer.query(solrQuery).getElapsedTime();
         solrServer.shutdown();  
         
                // return user;
	}
	
	public static void main(String[] args) throws SolrServerException {
		query();
	}

}
