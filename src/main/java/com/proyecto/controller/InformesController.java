package com.proyecto.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;
import com.proyecto.transferObject.UsuarioTO;

@Controller
public class InformesController {
	@RequestMapping(value = "generarTicket")
	public ModelAndView generarPDF(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);

		String nombreFichero = "ticket.pdf";

		// Escribir PDF

		Document document = new Document(PageSize.LETTER);
		PdfWriter.getInstance(document, new FileOutputStream(new File(nombreFichero)));
		document.open();
		document.addAuthor("Casino UBB");
		document.addCreator("Sistema de Reserva de almuerzo");
		document.addCreationDate();
		document.addTitle("Ticket");

		Paragraph paragraph = new Paragraph();

		paragraph.add("Hello World!");

		document.add(paragraph);

		document.close();
		System.out.println("PDF Creado Correctamente");
		// Descargar PDF
		/*response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreFichero + "\"");
		FileInputStream is = new FileInputStream(nombreFichero);
		IOUtils.copy(is, response.getOutputStream());
		response.flushBuffer();*/
		return new ModelAndView("indexUsuario");

	}
}
