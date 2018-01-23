package com.tryall.service;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tryall.mapper.UserMapper;
import com.tryall.pojo.User;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper usermapper;
	
	@Autowired  
    private HttpSolrServer httpSolrServer;
	
	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return usermapper.getUser(id);
	}

	
	
	
	@Override
	public void addUser(String username, String password) {
		// TODO Auto-generated method stub
		SimpleHash simpleHash = new SimpleHash("MD5", password);
		 usermapper.addUser(username, simpleHash.toString());
		 
	}




	@Override
	public User findUserByUsername(String username) {		
		return usermapper.findUserByUsername(username);
	}
	
	
	public User solrTest(int userId) {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("id:"+userId);
		QueryResponse queryResponse = null;
		try {
			queryResponse = this.httpSolrServer.query(solrQuery);
			System.out.println(queryResponse);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println((User)queryResponse.getBeans(User.class));
		return (User) queryResponse.getBeans(User.class); 
	}
	
	  public List<User> search(int keywords, Integer page, Integer rows) throws Exception {
	        SolrQuery solrQuery = new SolrQuery(); //构造搜索条件
	        solrQuery.setQuery("id:" + keywords); //搜索关键词
	        // 设置分页 start=0就是从0开始，，rows=5当前返回5条记录，第二页就是变化start这个值为5就可以了。
	        solrQuery.setStart((Math.max(page, 1) - 1) * rows);
	        solrQuery.setRows(rows);

	       
	        // 执行查询
	        QueryResponse queryResponse = this.httpSolrServer.query(solrQuery);
	        List<User> users = queryResponse.getBeans(User.class);
	     
	            // 将高亮的标题数据写回到数据对象中
	            Map<String, Map<String, List<String>>> map = queryResponse.getHighlighting();
	            for (Map.Entry<String, Map<String, List<String>>> highlighting : map.entrySet()) {
	                for (User u : users) {
	                    if (!highlighting.getKey().equals(u.getUserId())) {
	                        continue;
	                    }  
	                    break;
	                }
	            }
	        

	        return users;
	    }

}
