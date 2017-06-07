package com.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class CombatPdf {
	
	//合并pdf
	@Test
	public void testCombatPdf() throws Exception{
		PdfReader reader1 = new PdfReader("splitPDF1.pdf");  
		PdfReader reader2 = new PdfReader("splitPDF2.pdf");  
		  
		FileOutputStream out = new FileOutputStream("mergePDF.pdf");  
		  
		Document document = new Document();  
		PdfWriter writer = PdfWriter.getInstance(document, out);  
		  
		document.open();  
		PdfContentByte cb = writer.getDirectContent();  
		  
		int totalPages = 0;  
		totalPages += reader1.getNumberOfPages();  
		totalPages += reader2.getNumberOfPages();  
		  
		java.util.List<PdfReader> readers = new ArrayList<PdfReader>();  
		readers.add(reader1);  
		readers.add(reader2);  
		  
		int pageOfCurrentReaderPDF = 0;  
		Iterator<PdfReader> iteratorPDFReader = readers.iterator();  
		  
		// Loop through the PDF files and add to the output.  
		while (iteratorPDFReader.hasNext()) {  
		    PdfReader pdfReader = iteratorPDFReader.next();  
		  
		    // Create a new page in the target for each source page.  
		    while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {  
		        document.newPage();  
		        pageOfCurrentReaderPDF++;  
		        PdfImportedPage page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);  
		        cb.addTemplate(page, 0, 0);  
		    }  
		    pageOfCurrentReaderPDF = 0;  
		}  
		out.flush();  
		document.close();  
		out.close();  

		
	}
}
