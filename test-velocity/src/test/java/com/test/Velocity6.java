package com.test;

import java.io.FileOutputStream;

import org.junit.Test;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class Velocity6 {
	
	@Test
	public void deletePage() throws Exception{
		FileOutputStream out = new FileOutputStream("deletePage.pdf");  
		  
		Document document = new Document();  
		  
		PdfWriter writer = PdfWriter.getInstance(document, out);  
		  
		document.open();  
		document.add(new Paragraph("First page"));  
		//document.add(new Paragraph(Document.getVersion()));  
		  
		document.newPage();  
		writer.setPageEmpty(false);  
		  
		document.newPage();  
		document.add(new Paragraph("New page"));  
		  
		document.close();  
		  
		PdfReader reader = new PdfReader("deletePage.pdf");  
		reader.selectPages("1,3");  
		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream("deletePage2.pdf"));  
		stamp.close();  
		reader.close(); 
	}
	
	
	@Test
	public void insertPage() throws Exception{
		FileOutputStream out = new FileOutputStream("insertPage.pdf");  
		  
		Document document = new Document();  
		  
		PdfWriter.getInstance(document, out);  
		  
		document.open();  
		document.add(new Paragraph("1 page"));  
		  
		document.newPage();  
		document.add(new Paragraph("2 page"));  
		  
		document.newPage();  
		document.add(new Paragraph("3 page"));  
		  
		document.close();  
		  
		PdfReader reader = new PdfReader("insertPage.pdf");  
		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream("insertPage2.pdf"));  
		  
		stamp.insertPage(2, reader.getPageSize(1));  
		  
		ColumnText ct = new ColumnText(null);  
		ct.addElement(new Paragraph(24, new Chunk("INSERT PAGE")));  
		ct.setCanvas(stamp.getOverContent(2));  
		ct.setSimpleColumn(36, 36, 559, 770);  
		  
		stamp.close();  
		reader.close();  
	}
	
	
	
}
