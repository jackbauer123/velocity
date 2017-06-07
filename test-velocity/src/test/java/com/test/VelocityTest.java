package com.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.velocity.PDFBuild;


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
	
	@Test
	public void htmlToPdf() throws FileNotFoundException, DocumentException{
		//Document document = new Document(PageSize.A4.rotate());
		Document document = new Document(PageSize.A5, 36, 72, 108, 180);
		
		PdfWriter.getInstance(document, new FileOutputStream("Chap01.pdf"));
			
		document.open();
		
		document.add(new Paragraph("Hello World"));
		
		document.close();
	}
	
	@Test
	public void setPageXXX() throws FileNotFoundException, DocumentException{
		
		FileOutputStream out=new FileOutputStream("Chap02xx.pdf");
		//页面大小  
		Rectangle rect = new Rectangle(PageSize.B5.rotate());  
		//页面背景色  
		//rect.setBackgroundColor(BaseColor.ORANGE);  
		  
		Document doc = new Document(rect);  
		  
		PdfWriter writer = PdfWriter.getInstance(doc, out);  
		  
		//PDF版本(默认1.4)  
		writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);  
		  
		//文档属性  
		doc.addTitle("Title@sample");  
		doc.addAuthor("Author@rensanning");  
		doc.addSubject("Subject@iText sample");  
		doc.addKeywords("Keywords@iText");  
		doc.addCreator("Creator@iText");  
		
		// 设置密码为："World"  
		/*writer.setEncryption("Hello".getBytes(), "World".getBytes(),  
		        PdfWriter.ALLOW_SCREENREADERS,  
		        PdfWriter.STANDARD_ENCRYPTION_128); */ 
		
		//页边空白  
		doc.setMargins(10, 20, 30, 40);  
		  
		doc.open();  
		doc.add(new Paragraph("Hello World"));
		//新增page
		doc.add(new Paragraph("First page")); 
		doc.newPage();  
		writer.setPageEmpty(false); 
		doc.newPage();  
		doc.add(new Paragraph("New page"));
		doc.close();
	}
	
	
	/*
	 * 增加水印背景
	 * 
	 * */
	@Test
	public void addBackGround() throws Exception{
		//图片水印  
		PdfReader reader = new PdfReader("Chap01.pdf");  //待加水印的pdf
		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream("watermark.pdf"));  //加水印后的pdf
		Image img = Image.getInstance("src/main/resources/test.jpg");  //水印图片
		img.setAbsolutePosition(10, 10);  
		PdfContentByte under = stamp.getUnderContent(1);  
		under.addImage(img); 
		  
		//文字水印  
		PdfContentByte over = stamp.getOverContent(1);  //getOverContent(2)表示对第二页进行操作
		over.beginText();  
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI,  
		        BaseFont.EMBEDDED);  
		over.setFontAndSize(bf, 18);  
		over.setTextMatrix(30, 30);  
		over.showTextAligned(Element.ALIGN_LEFT, "DUPLICATE", 230, 430, 45);  
		over.endText();  
		  
		//背景图  
		Image img2 = Image.getInstance("src/main/resources/test.jpg");  
		img2.setAbsolutePosition(0, 0);  
		PdfContentByte under2 = stamp.getUnderContent(3);  
		under2.addImage(img2);  
		  
		stamp.close();  
		reader.close();
	}
	
	
	//水印，中文
	@Test 
	public void addBackGround1(){
		
		 String imageFilePath = "G:/watermark.jpg"; // 水印图片路径  
	     String pdfFilePath = "G:/itext.pdf"; // 文件生成路径  
	     PDFBuild.buidPDF(pdfFilePath, imageFilePath, "正版授权", 16);  
		
		
		
	}
	
	
}
