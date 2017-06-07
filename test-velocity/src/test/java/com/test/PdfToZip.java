package com.test;

import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfToZip {
	
	@Test
	public void pdfToZipTest() throws Exception{
		
		
		ZipOutputStream zip = new ZipOutputStream(new FileOutputStream("zipPDF.zip"));  
		for (int i = 1; i <= 3; i++) {  
		    ZipEntry entry = new ZipEntry("hello_" + i + ".pdf");  
		    zip.putNextEntry(entry);  
		    Document document = new Document();  
		    PdfWriter writer = PdfWriter.getInstance(document, zip);  
		    writer.setCloseStream(false);  
		    document.open();  
		    document.add(new Paragraph("Hello " + i));  
		    document.close();  
		    zip.closeEntry();  
		}  
		zip.close();
		
	}
	
	
	
	
}
