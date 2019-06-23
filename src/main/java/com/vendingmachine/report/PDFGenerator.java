package com.vendingmachine.report;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Queue;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.vendingmachine.machine.product.Product;

public class PDFGenerator {

	private static final String FILE_PATH = "src/main/resources/";

	public void generatePDF(Queue<Product> produtQueue) {
		Document document = new Document(PageSize.A4.rotate());
		try {
			PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_PATH + "/Report1.pdf")));
			document.open();
			document.newPage();
			float[] columnWidths = { 1, 5 };
			PdfPTable table = new PdfPTable(columnWidths);
			table.setWidthPercentage(100);
			table.getDefaultCell().setUseAscender(true);
			table.getDefaultCell().setUseDescender(true);
			Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
			PdfPCell cell = new PdfPCell(new Phrase("This is a header", f));
			cell.setBackgroundColor(GrayColor.GRAYBLACK);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(2);
			table.addCell(cell);
			table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
			table.setHeaderRows(1);
			table.addCell("ID");
			table.addCell("Price");

			table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			double total = 0.0;
			for (Product product : produtQueue) {
				table.addCell(String.valueOf(product.getId()));
				table.addCell(String.valueOf(product.getPrice()));
				total += product.getPrice();
			}

			document.add(table);
			Paragraph paragraph = new Paragraph();
			paragraph.add("Total is: " + total);
			document.add(paragraph);
			document.close();
			System.out.println("Done");
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
	}
}
