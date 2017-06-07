package com.test;

import java.io.FileOutputStream;

import org.junit.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTransition;
import com.itextpdf.text.pdf.PdfWriter;

public class HuanDongPian {
	
	
	//换灯片pdf
	
	@Test
	public void  huanDongPianTest() throws Exception{
		Document doc = new Document();
		FileOutputStream out = new FileOutputStream("Chap01.pdf");

		PdfWriter writer = PdfWriter.getInstance(doc, out); 
		
		writer.setViewerPreferences(PdfWriter.PageModeFullScreen);//全屏  
		writer.setPageEvent(new PdfPageEventHelper() {  
		    public void onStartPage(PdfWriter writer, Document doc) {  
		        writer.setTransition(new PdfTransition(PdfTransition.DISSOLVE, 3));  
		        writer.setDuration(2);//间隔时间  
		    }  
		});  
		  
		doc.open();  
		doc.add(new Paragraph("1 page"));  
		doc.newPage();  
		doc.add(new Paragraph("2 page"));  
		doc.newPage();  
		doc.add(new Paragraph("3 page"));  
		doc.newPage();  
		doc.add(new Paragraph("4 page"));  
		doc.newPage();  
		doc.add(new Paragraph("5 page")); 
		
		
		doc.close();
	}
	
}
