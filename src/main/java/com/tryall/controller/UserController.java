package com.tryall.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;
//import javax.jms.Destination;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tryall.listener.HelloWorld;
import com.tryall.pojo.User;
import com.tryall.realm.MyRealm;
import com.tryall.realm.UserRealm;
import com.tryall.service.UserService;
//import com.test.activeMQ.ProducerService;


@Controller
@SessionAttributes("username")                  //放入session中
public class UserController {

	@Autowired  
	private SessionDAO sessionDAO;
	
	@Autowired
	UserService userService;
	
	/*@Autowired
	ProducerService producerService;*/
	
	/*@Resource(name="queueDestination") 
    private Destination receiveQueue;*/
	
	//@RequestMapping()  
    public String list(Model model) {  
        Collection<Session> sessions =  sessionDAO.getActiveSessions(); 
        
        model.addAttribute("sessions", sessions);  
        model.addAttribute("sesessionCount", sessions.size());  
        return "sessions/list";  
    }  
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String checkLogin(@RequestParam(value="account",required=false)String username,
			@RequestParam(value="password",required=false)String password,
			@RequestParam(value="captcha",required=false)String captcha,HttpSession session
			,HttpServletRequest request,HttpServletResponse response,Model model) {
			String msg = "";
			System.out.println(username+"/"+password);
			//CredentialsMatcher cm = new SimpleCredentialsMatcher();
			//HttpSession session2 = request.getSession();
			
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			model.addAttribute("user", username);
			
			token.setHost(request.getRemoteAddr());
			//记住我
			token.setRememberMe(true);
			
			 
			model.addAttribute("lastTime", SimpleDateFormat.getInstance().format(session.getLastAccessedTime()));
			
			
			
			Subject subject = SecurityUtils.getSubject();
			
			//list(model);
			Collection<Session> sessions = sessionDAO.getActiveSessions();

			Iterator it = sessions.iterator();

			Set<String> loginNames = new HashSet<String>();

			
			model.addAttribute("sesessionCount",(int)Math.ceil(sessions.size()/2)); 
			
			try {  
		        subject.login(token); 
		        msg="ok"+token.getHost();
		       // System.out.println(msg);  
		        model.addAttribute("message", msg);
		        
		        /*if (subject.isAuthenticated()) {  
		            return "redirect:/";  
		        } else {  
		            return "login";  
		        }  */
		    } catch (IncorrectCredentialsException e) {  
		        msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";  
		        model.addAttribute("message", msg);  
		        System.out.println(msg);  
		    } catch (ExcessiveAttemptsException e) {  
		        msg = "登录失败次数过多";  
		        model.addAttribute("message", msg);  
		        System.out.println(msg);  
		    } catch (LockedAccountException e) {  
		        msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";  
		        model.addAttribute("message", msg);  
		        System.out.println(msg);  
		    } catch (DisabledAccountException e) {  
		        msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";  
		        model.addAttribute("message", msg);  
		        System.out.println(msg);  
		    } catch (ExpiredCredentialsException e) {  
		        msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";  
		        model.addAttribute("message", msg);  
		        System.out.println(msg);  
		    } catch (UnknownAccountException e) {  
		        msg = "帐号不存在. There is no user with username of " + token.getPrincipal();  
		        model.addAttribute("message", msg);  
		        System.out.println(msg);  
		    } catch (UnauthorizedException e) {  
		        msg = "您没有得到相应的授权！" + e.getMessage();  
		        model.addAttribute("message", msg);  
		        System.out.println(msg);  
		    } catch (AuthenticationException e) {
				// TODO: handle exception
		    	 msg = "验证错误" + e.getMessage();  
			     model.addAttribute("message", msg);  
			     System.out.println(msg);
			} catch (Exception e) {
				// TODO: handle exception
		    	 msg = "未知错误" + e.getMessage();  
			     model.addAttribute("message", msg);  
			     System.out.println(msg);
			} 
			
				
		    return "login";  
	}
	
	
	@RequestMapping(value="/register.do",method=RequestMethod.POST)
	public String register(Model model,@RequestParam(name="username",required=true)String username,
			@RequestParam(value="password",required=true)String password)throws Exception {
		userService.addUser(username, password);
		return "login";
	}
	
	@RequestMapping(value="/getUserById",method=RequestMethod.GET)
	public String getUserById(@RequestParam(name="userId",required=false)int id,Model model,
			ServletRequest request,ServletResponse response) throws ServletException, IOException {
		User user = userService.getUser(id);
		model.addAttribute("user", user);
		/*System.out.println(request.getCharacterEncoding()+"/"+request.getLocalName()+"/"+request.getLocalAddr()
		+"/"+request.getLocalPort()+"/"+request.getRemoteHost()+"/"+request.getServerName());
		request.getRequestDispatcher("login").forward(request, response);*/
		return "login";
	}
	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public void test(Model model,@RequestParam(name="value",required=true)String value,
			HttpServletRequest request)throws Exception {
		/*HttpSession session = request.getSession();
		if (session.getAttribute("sessionId")==null) {
			session.setAttribute("sessionId", session.getId());
		}*/
		//new HelloWorld().test(value);
		//producerService.sendMessage(receiveQueue, "my name is cyj!"); 
	}
	
	

}
