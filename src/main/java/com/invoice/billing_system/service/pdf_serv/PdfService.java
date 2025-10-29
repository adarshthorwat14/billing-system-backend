package com.invoice.billing_system.service.pdf_serv;

import com.invoice.billing_system.model.invoice.Invoice;
import com.invoice.billing_system.model.invoice.InvoiceItem;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.List;

@Service
public class PdfService {

	public byte[] generateInvoicePdf(Invoice invoice) {
	    try {
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        Document document = new Document(PageSize.A4, 36, 36, 50, 50);
	        PdfWriter.getInstance(document, baos);
	        document.open();

	        Font headerFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.WHITE);
	        Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
	        Font normalFont = new Font(Font.FontFamily.HELVETICA, 11);
	        DecimalFormat df = new DecimalFormat("#0.00");

	        // Header section
	        PdfPTable headerTable = new PdfPTable(1);
	        PdfPCell headerCell = new PdfPCell(new Phrase("BeUnique Pvt. Ltd.\nAddress Line\nGSTIN: 1234567890\nContact: 9876543210", headerFont));
	        headerCell.setBackgroundColor(new BaseColor(34, 139, 34));
	        headerCell.setPadding(10);
	        headerCell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        headerCell.setBorder(Rectangle.NO_BORDER);
	        headerTable.addCell(headerCell);
	        document.add(headerTable);
	        document.add(Chunk.NEWLINE);

	        // Invoice and client info
	        PdfPTable infoTable = new PdfPTable(2);
	        infoTable.setWidthPercentage(100);
	        infoTable.setWidths(new float[]{60, 40});

	        PdfPCell leftCell = new PdfPCell();
	        leftCell.setBorder(Rectangle.NO_BORDER);
	        leftCell.addElement(new Paragraph("BILL TO:", boldFont));
	        leftCell.addElement(new Paragraph("Name: " + invoice.getClient().getName(), normalFont));
	        leftCell.addElement(new Paragraph("Email: " + invoice.getClient().getEmail(), normalFont));
	        leftCell.addElement(new Paragraph("Phone: " + invoice.getClient().getPhone(), normalFont));
	        leftCell.addElement(new Paragraph("Address: " + invoice.getClient().getAddress(), normalFont));
	        leftCell.addElement(new Paragraph("GSTIN: " + (invoice.getClient().getGst() != null ? invoice.getClient().getGst() : "N/A"), normalFont));

	        PdfPCell rightCell = new PdfPCell();
	        rightCell.setBorder(Rectangle.NO_BORDER);
	        rightCell.addElement(new Paragraph("INVOICE NO: " + invoice.getInvoiceId(), normalFont));
	        rightCell.addElement(new Paragraph("DATE: " + invoice.getInvoiceDate(), normalFont));
	        rightCell.addElement(new Paragraph("DUE DATE: " + invoice.getDueDate(), normalFont));

	        infoTable.addCell(leftCell);
	        infoTable.addCell(rightCell);
	        document.add(infoTable);

	        document.add(Chunk.NEWLINE);

	        // Invoice item table
	        PdfPTable itemTable = new PdfPTable(6);
	        itemTable.setWidthPercentage(100);
	        itemTable.setWidths(new float[]{3, 2, 1, 1, 1, 1});

	        addTableHeader(itemTable, new String[]{"Product", "Description", "Qty", "Price", "Tax (%)", "Amount"});

	        double subtotal = 0;
	        double totalTaxAmount = 0;
	        double grandTotal = 0;

	        for (InvoiceItem item : invoice.getInvoiceItems()) {
	            double itemSubtotal = item.getPrice() * item.getQuantity();
	            double itemTaxAmount = itemSubtotal * item.getTaxRate() / 100;
	            double itemTotal = itemSubtotal + itemTaxAmount;

	            itemTable.addCell(item.getProduct().getName());
	            itemTable.addCell(item.getProduct().getDescription());
	            itemTable.addCell(String.valueOf(item.getQuantity()));
	            itemTable.addCell("₹" + df.format(item.getPrice()));
	            itemTable.addCell(df.format(item.getTaxRate()) + "%");
	            itemTable.addCell("₹" + df.format(itemTotal));

	            subtotal += itemSubtotal;
	            totalTaxAmount += itemTaxAmount;
	            grandTotal += itemTotal;
	        }

	        document.add(itemTable);
	        document.add(Chunk.NEWLINE);

	        // Summary section
	        PdfPTable summary = new PdfPTable(2);
	        summary.setWidthPercentage(40);
	        summary.setHorizontalAlignment(Element.ALIGN_RIGHT);

	        summary.addCell(getCell("Subtotal:", boldFont, Element.ALIGN_RIGHT));
	        summary.addCell(getCell("₹" + df.format(subtotal), normalFont, Element.ALIGN_RIGHT));

	        summary.addCell(getCell("Total Tax:", boldFont, Element.ALIGN_RIGHT));
	        summary.addCell(getCell("₹" + df.format(totalTaxAmount), normalFont, Element.ALIGN_RIGHT));

	        summary.addCell(getCell("Grand Total:", boldFont, Element.ALIGN_RIGHT));
	        summary.addCell(getCell("₹" + df.format(grandTotal), boldFont, Element.ALIGN_RIGHT));

	        document.add(summary);
	        document.add(Chunk.NEWLINE);

	        // Footer section
	        PdfPTable bottomTable = new PdfPTable(2);
	        bottomTable.setWidthPercentage(100);
	        bottomTable.setWidths(new float[]{60, 40});
	        bottomTable.addCell(getCell("Terms & Conditions:\n- Goods once sold will not be taken back.\n- Payment due within 7 days.", normalFont, Element.ALIGN_LEFT));
	        bottomTable.addCell(getCell("Seal & Signature", normalFont, Element.ALIGN_RIGHT));
	        document.add(bottomTable);

	        Paragraph thankYou = new Paragraph("Thank you for your business!", new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC));
	        thankYou.setAlignment(Element.ALIGN_CENTER);
	        document.add(Chunk.NEWLINE);
	        document.add(thankYou);

	        document.close();
	        return baos.toByteArray();
	    } catch (Exception e) {
	        throw new RuntimeException("Error generating PDF", e);
	    }
	}

	private void addTableHeader(PdfPTable table, String[] headers) {
	    Font headFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
	    for (String header : headers) {
	        PdfPCell cell = new PdfPCell(new Phrase(header, headFont));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        cell.setPadding(5);
	        table.addCell(cell);
	    }
	}

	private PdfPCell getCell(String text, Font font, int alignment) {
	    PdfPCell cell = new PdfPCell(new Phrase(text, font));
	    cell.setHorizontalAlignment(alignment);
	    cell.setPadding(5);
	    return cell;
	}
}
