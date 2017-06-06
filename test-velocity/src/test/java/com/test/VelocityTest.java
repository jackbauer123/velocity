package com.test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;

public class VelocityTest {
	
	@Test
	public void velocityTest(){
		
		VelocityEngine ve=new VelocityEngine();
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());  
		ve.init();
		Template t = ve.getTemplate("hellovelocity.vm");
		VelocityContext ctx = new VelocityContext();
		
		ctx.put("name", "velocity");
		ctx.put("date", (new Date()).toString());
		
		List temp = new ArrayList();
		temp.add("1");
		temp.add("2");
		ctx.put("list", temp);
		
		StringWriter sw = new StringWriter();
		
		t.merge(ctx, sw);
		
		System.out.println(sw.toString());
		
	}
	
	@Test
	public void genateHtml(){
		try {
			VelocityEngine ve=new VelocityEngine();
			ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());  
			ve.init();
			
            //初始化vm模板
            Template template=ve.getTemplate("genateHtml.vm","UTF-8");
            //初始化上下文
            VelocityContext context=new VelocityContext();
            //添加数据到上下文中
            context.put("title", "我的第一个velocity页面");
            //生成html页面
            PrintWriter pw=new PrintWriter("f:\\myvelocity.html");
            template.merge(context, pw);
            //关闭流
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        } catch (ParseErrorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

	
}
