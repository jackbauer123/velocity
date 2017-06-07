package com.test;

import java.io.FileOutputStream;

import org.junit.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Velocity5 {
	
	@Test
	public void setParagraphPosition() throws Exception{
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("Chap01.pdf"));
		document.open();
		
		Paragraph p = new Paragraph("In the previous example, you added a header and footer with the showTextAligned() method. This example demonstrates that it’s sometimes more interesting to use PdfPTable and writeSelectedRows(). You can define a bottom border for each cell so that the header is underlined. This is the most elegant way to add headers and footers, because the table mechanism allows you to position and align lines, images, and text.");  
		  
		//默认  
		p.setAlignment(Element.ALIGN_JUSTIFIED);  
		document.add(p);  
		  
		document.newPage();  
		p.setAlignment(Element.ALIGN_JUSTIFIED);  
		p.setIndentationLeft(1 * 15f);  
		p.setIndentationRight((5 - 1) * 15f);  
		document.add(p);  
		  
		//居右  
		document.newPage();  
		p.setAlignment(Element.ALIGN_RIGHT);  
		p.setSpacingAfter(15f);  
		document.add(p);  
		  
		//居左  
		document.newPage();  
		p.setAlignment(Element.ALIGN_LEFT);  
		p.setSpacingBefore(15f);  
		document.add(p);  
		  
		//居中  
		document.newPage();  
		p.setAlignment(Element.ALIGN_CENTER);  
		p.setSpacingAfter(15f);  
		p.setSpacingBefore(15f);  
		document.add(p);  
		document.close();
	}
	
}
