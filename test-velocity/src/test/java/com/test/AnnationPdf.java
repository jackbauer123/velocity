package com.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.junit.Test;

import com.itextpdf.text.Annotation;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfString;
import com.itextpdf.text.pdf.PdfWriter;

public class AnnationPdf {
	
	
	//注释提示
	@Test
	public void annationPdfTest() throws Exception{
		
		Document doc = new Document();  
		FileOutputStream out = new FileOutputStream("mergePDF.pdf");  
		PdfWriter writer = PdfWriter.getInstance(doc, out);  
		writer.setLinearPageMode();  
		  
		doc.open();  
		doc.add(new Paragraph("1 page"));  
		doc.add(new Annotation("Title", "This is a annotation!"));  
		  
		doc.newPage();  
		doc.add(new Paragraph("2 page"));  
		Chunk chunk = new Chunk("\u00a0");  
		chunk.setAnnotation(PdfAnnotation.createText(writer, null, "Title", "This is a another annotation!", false, "Comment"));  
		doc.add(chunk);  
		  
		//添加附件  
		doc.newPage();  
		doc.add(new Paragraph("3 page"));  
		Chunk chunk2 = new Chunk("\u00a0\u00a0");  
		PdfAnnotation annotation = PdfAnnotation.createFileAttachment(  
		        writer, null, "Title", null,  
		        "src/main/resources/test.jpg",  
		        "img.jpg");  
		annotation.put(PdfName.NAME,  
		        new PdfString("Paperclip"));  
		chunk2.setAnnotation(annotation);  
		doc.add(chunk2); 
		doc.close();
	}
	
}
