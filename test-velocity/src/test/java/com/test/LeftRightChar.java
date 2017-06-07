package com.test;

import java.io.FileOutputStream;

import org.junit.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class LeftRightChar {
	
	//左右文字
	@Test
	public void leftAndRightChar() throws Exception{
		Document doc = new Document();
		FileOutputStream out = new FileOutputStream("Chap01.pdf");

		PdfWriter writer = PdfWriter.getInstance(doc, out); 
		
		doc.open();  
		  
		PdfContentByte canvas = writer.getDirectContent();  
		  
		Phrase phrase1 = new Phrase("This is a test!left");  
		Phrase phrase2 = new Phrase("This is a test!right");  
		Phrase phrase3 = new Phrase("This is a test!center");  
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase1, 100, 100, 0);  
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase2, 200, 200, 10);  
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase3, 300, 300, 0); 
		doc.close();
	}
}
