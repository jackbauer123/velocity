package com.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringReader;

import org.junit.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;

public class HtmlToPdf {
	
	
	@Test
	public void htmlToPdfTest() throws Exception{
		
		Document document = new Document(PageSize.LETTER);  
		PdfWriter.getInstance(document, new FileOutputStream("mergePDF.pdf"));  
		document.open();  
		HTMLWorker htmlWorker = new HTMLWorker(document);  
		htmlWorker.parse(new StringReader("<h1>This is a test!</h1>"));  
		document.close(); 
	}
	
}
