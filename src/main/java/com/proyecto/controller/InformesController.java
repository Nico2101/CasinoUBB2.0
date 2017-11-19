package com.proyecto.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.proyecto.persistence.MenuDAO;
import com.proyecto.persistence.ReservaDAO;
import com.proyecto.transferObject.MenuTO;
import com.proyecto.transferObject.ReservaTO;

@Controller
public class InformesController {

	@RequestMapping(value = "generarTicket")
	public ModelAndView generarTicket(ModelAndView vista, HttpServletRequest request) {
		HttpSession sesion = request.getSession();
		int idUsuario = (int) sesion.getAttribute("id");
		ReservaDAO reservaDAO = new ReservaDAO();
		LinkedList<ReservaTO> reserva = new LinkedList<>();
		if (!reservaDAO.existenMenus(idUsuario).isEmpty()) {
			reserva = reservaDAO.existenMenus(idUsuario);

			// Obtener Datos del menu
			vista.addObject("menu", reservaDAO.datosTicket(idUsuario));

			// obtener datos Horario reservado

			vista.setViewName("generarTicket");
		} else {
			vista.addObject("SinReservas", "Usuario no tiene reservas");
			vista.setViewName("indexUsuario");
		}

		return vista;
	}

	@RequestMapping(value = "getTicket")
	public void generarPDF(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Escribir PDF

		String FILE_NAME = "ticket.pdf";

		// Declaramos un documento como un objecto Document.
		Document documento = new Document(PageSize.LETTER);
		// writer es declarado como el método utilizado para escribir en el archivo.
		PdfWriter pdfWriter = null;

		try {
			// Obtenemos la instancia del archivo a utilizar.
			pdfWriter = PdfWriter.getInstance(documento, new FileOutputStream(new File(FILE_NAME)));
		} catch (FileNotFoundException | DocumentException ex) {
			ex.getMessage();
		}

		// Abrimos el documento a editar.
		documento.open();

		// Creamos un párrafo nuevo llamado "vacio1" para espaciar los elementos.
		Paragraph vacio1 = new Paragraph();
		vacio1.add("\n\n");

		// Declaramos un texto como Paragraph. Le podemos dar formato alineado, tamaño,
		// color, etc.
		Paragraph titulo = new Paragraph();
		Paragraph sup = new Paragraph();
		Paragraph saltolinea = new Paragraph();
		Paragraph mensaje = new Paragraph();

		Image imagen = Image
				.getInstance("https://github.com/Nico2101/CasinoUBB2.0/blob/master/Screenshot_2.png?raw=true");

		imagen.scalePercent(75f);
		imagen.setAlignment(Element.ALIGN_JUSTIFIED);

		titulo.setFont(FontFactory.getFont("Times New Roman", 14, Font.BOLD));
		sup.add("_ _ _ _ _ _ _ _ _ _ _ _ _\n");
		titulo.add("| *******Ticket*******|\n");

		saltolinea.add("|                                     |\n");
		mensaje.add("Recorta el ticket y presentalo en el casino");

		Paragraph contenido = new Paragraph();
		contenido.setFont(FontFactory.getFont("Times New Roman", 12));
		contenido.add("|    1x  colacion  1900    |");

		sup.setAlignment(Element.ALIGN_CENTER);
		saltolinea.setAlignment(Element.ALIGN_CENTER);
		titulo.setAlignment(Element.ALIGN_CENTER);
		contenido.setAlignment(Element.ALIGN_CENTER);
		mensaje.setAlignment(Element.ALIGN_CENTER);

		try {
			// Agregamos el texto al documento.
			documento.add(imagen);

			documento.add(sup);
			documento.add(saltolinea);
			documento.add(titulo);
			documento.add(saltolinea);
			documento.add(contenido);
			documento.add(vacio1);
			documento.add(mensaje);

		} catch (DocumentException ex) {
			ex.getMessage();
		}

		// Cerramos el documento.
		documento.close();
		// Cerramos el writer.
		pdfWriter.close();

		System.out.println("PDF Creado Correctamente");

		//// Descargar PDF////

		FileInputStream fis = new FileInputStream(FILE_NAME);

		int read = 0;
		String contentType = "application/pdf";
		response.setContentType(contentType);
		response.setHeader("Content-Disposition", "attachment;filename=\"" + FILE_NAME + "\"");

		ServletOutputStream out = response.getOutputStream();

		byte[] bytes = new byte[1000];

		while ((read = fis.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}

		out.flush();
		out.close();

		// return new ModelAndView("indexUsuario");

	}
}
