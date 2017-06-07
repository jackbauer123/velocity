package com.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.junit.Test;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfOutline;
import com.itextpdf.text.pdf.PdfWriter;

public class OrderPage {


	@Test
	public void orderPage() throws Exception{
		Document doc = new Document();
		FileOutputStream out = new FileOutputStream("Chap01.pdf");

		PdfWriter writer = PdfWriter.getInstance(doc, out);  
		writer.setLinearPageMode();  
		  
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
		  
		int[] order = {4,3,2,1};  
		writer.reorderPages(order);  
		
		
		doc.close();
	}
	
	@Test
	public void directoryPdf() throws Exception{
		
		Document document = new Document();
		FileOutputStream out = new FileOutputStream("Chap01.pdf");

		PdfWriter writer = PdfWriter.getInstance(document, out); 
		document.open();
		// Code 1  
		document.add(new Chunk("Chapter 1").setLocalDestination("1"));  
		  
		document.newPage();  
		document.add(new Chunk("Chapter 2").setLocalDestination("2"));  
		document.add(new Paragraph(new Chunk("Sub 2.1").setLocalDestination("2.1")));  
		document.add(new Paragraph(new Chunk("Sub 2.2").setLocalDestination("2.2")));  
		  
		document.newPage();  
		document.add(new Chunk("Chapter 3").setLocalDestination("3"));  
		  
		// Code 2  
		PdfContentByte cb = writer.getDirectContent();  
		PdfOutline root = cb.getRootOutline();  
		  
		// Code 3  
		@SuppressWarnings("unused")  
		PdfOutline oline1 = new PdfOutline(root, PdfAction.gotoLocalPage("1", false), "Chapter 1");  
		  
		PdfOutline oline2 = new PdfOutline(root, PdfAction.gotoLocalPage("2", false), "Chapter 2");  
		oline2.setOpen(false);  
		  
		@SuppressWarnings("unused")  
		PdfOutline oline2_1 = new PdfOutline(oline2, PdfAction.gotoLocalPage("2.1", false), "Sub 2.1");  
		@SuppressWarnings("unused")  
		PdfOutline oline2_2 = new PdfOutline(oline2, PdfAction.gotoLocalPage("2.2", false), "Sub 2.2");  
		  
		@SuppressWarnings("unused")  
		PdfOutline oline3 = new PdfOutline(root, PdfAction.gotoLocalPage("3", false), "Chapter 3"); 
		document.close();
	
	}
	
}
