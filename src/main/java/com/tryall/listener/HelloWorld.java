package com.tryall.listener;

import java.text.SimpleDateFormat;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;

import org.comet4j.core.CometConnection;
import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;
import org.comet4j.core.event.ConnectEvent;
import org.comet4j.core.listener.ConnectListener;
import org.springframework.http.HttpRequest;


public class HelloWorld  implements ServletContextListener {
	 // 频道1
    private static final String CHANNEL = "hello";
    // 频道2
    private static final String CHANNEL2 = "ok";
    
    private String msg;
    
    public void setMsg(String msg) {
		this.msg = msg;
	}
    
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
    		// CometContext ： Comet4J上下文，负责初始化配置、引擎对象、连接器对象、消息缓存等。
            CometContext cc = CometContext.getInstance();
            // 注册频道，即标识哪些字段可用当成频道，用来作为向前台传送数据的“通道”
            cc.registChannel(CHANNEL);//注册应用的channel
            cc.registChannel(CHANNEL2);//注册应用的channel2
            Thread helloAppModule = new Thread(new HelloAppModule(), "Sender App Module");
            // 下面的内部类的方法是个死循环，设置helloAppModule线程为“守护线程”，则当jvm只剩“守护线程”时(主线程结束)，该线程也会结束
            helloAppModule.setDaemon(true);
            // 开始线程
            helloAppModule.start();
            
            
    }
    
    
    
    class JoinListener extends ConnectListener{

		@Override
		public boolean handleEvent(ConnectEvent arg0) {
			// TODO Auto-generated method stub
			CometConnection conn =arg0.getConn();
			CometContext.getInstance().getEngine().sendTo(CHANNEL, conn,"欢迎上线");
			return false;
		}
    	
    }

    
    
    
    class HelloAppModule implements Runnable {
    	public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            public void run() {           	
                    while (true) {
                            try {
                                    Thread.sleep(1000);
                            } catch (Exception ex) {
                                    ex.printStackTrace();
                            }
                            // CometEngine ： 引擎，负责管理和维持连接，并能够必要的发送服务
                            CometEngine engine = CometContext.getInstance().getEngine();
                            // 参数的意思：通过什么频道（CHANNEL1）发送什么数据（number1++），前台可用可用频道的值（result1）来获取某频道发送的数据
                            //engine.sendToAll(CHANNEL, Runtime.getRuntime().freeMemory()/1024);
                            engine.addConnectListener(new JoinListener());
                            
                            engine.sendToAll(CHANNEL, simpleDateFormat.format(System.currentTimeMillis()));
                            //engine.sendToAll(CHANNEL,msg);
                    }
            }
    }
    
    public void test(String msg) {
    	CometEngine engine = CometContext.getInstance().getEngine();
        // 参数的意思：通过什么频道（CHANNEL1）发送什么数据（number1++），前台可用可用频道的值（result1）来获取某频道发送的数据
        //engine.sendToAll(CHANNEL, Runtime.getRuntime().freeMemory()/1024);
        engine.addConnectListener(new JoinListener());
        engine.sendToAll(CHANNEL, msg);
        Thread helloAppModule = new Thread(new HelloAppModule(), "Sender App Module");
        helloAppModule.setDaemon(true);
        // 开始线程
        helloAppModule.start();
    }
    
   /* public void test2(String msg,HttpServletRequest request) {
    	CometEngine engine = CometContext.getInstance().getEngine();
        // 参数的意思：通过什么频道（CHANNEL1）发送什么数据（number1++），前台可用可用频道的值（result1）来获取某频道发送的数据
        //engine.sendToAll(CHANNEL, Runtime.getRuntime().freeMemory()/1024);
        engine.addConnectListener(new JoinListener());
        CometConnection c = engine.getConnection(request);
        engine.sendTo(CHANNEL, c, msg);
        /*Thread helloAppModule = new Thread(new HelloAppModule(), "Sender App Module");
        helloAppModule.setDaemon(true);
        // 开始线程
        helloAppModule.start();*/
  //  }
    public void contextDestroyed(ServletContextEvent arg0) {

    }
}
