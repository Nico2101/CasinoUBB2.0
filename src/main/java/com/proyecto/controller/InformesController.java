package com.proyecto.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Time;
import java.util.LinkedList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.proyecto.persistence.HorarioDAO;
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
		HorarioDAO horarioDAO = new HorarioDAO();

		if (!reservaDAO.existenMenus(idUsuario).isEmpty()) {

			// Obtener Datos del menu
			vista.addObject("menu", reservaDAO.datosTicket(idUsuario));

			// obtener datos Horario reservado
			vista.addObject("datosHorario", horarioDAO.getHorario(idUsuario));
			vista.setViewName("generarTicket");
		} else {
			vista.addObject("SinReservas", "Usuario no tiene reservas");
			vista.setViewName("indexUsuario");
		}

		return vista;
	}

	@RequestMapping(value = "getTicket")
	public void generarPDF(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "menu") String nombreMenu, @RequestParam(value = "tipo") String tipo,
			@RequestParam(value = "precio") int precio, @RequestParam(value = "fecha") String fecha,
			@RequestParam(value = "cantRaciones") int cantRaciones, @RequestParam(value = "horaI") Time horaI,
			@RequestParam(value = "horaF") Time horaF) throws Exception {
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
		Paragraph footer = new Paragraph();

		Image imagen = Image
				.getInstance("https://github.com/Nico2101/CasinoUBB2.0/blob/master/Screenshot_2.png?raw=true");

		imagen.scalePercent(75f);
		imagen.setAlignment(Element.ALIGN_JUSTIFIED);

		titulo.setFont(FontFactory.getFont("Times New Roman", 14, Font.BOLD));
		sup.add("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n");
		titulo.add("|    *******Ticket*******    |\n");

		saltolinea.add("|                                            |\n");
		mensaje.add("Recorta el ticket y presentalo en el casino");

		Paragraph contenido = new Paragraph();
		contenido.setFont(FontFactory.getFont("Times New Roman", 12));

		if (tipo.equalsIgnoreCase("Normal")) {
			contenido.add("|        " + cantRaciones + "x  " + tipo + "  " + cantRaciones * precio + "        |\n");
		}

		if (tipo.equalsIgnoreCase("Extra")) {
			contenido.add("|          " + cantRaciones + "x  " + tipo + "  " + cantRaciones * precio + "          |\n");
		}

		contenido.add("|          Menu: " + nombreMenu + "         |\n");
		contenido.add("|       Fecha: " + fecha + "      |\n");
		contenido.add("|   Hora: " + horaI + "-" + horaF + "  |\n");

		footer.add("|_ _ _ _ _ _ _ _ _ _ _ _ _ _ _|\n");

		sup.setAlignment(Element.ALIGN_CENTER);
		saltolinea.setAlignment(Element.ALIGN_CENTER);
		titulo.setAlignment(Element.ALIGN_CENTER);
		contenido.setAlignment(Element.ALIGN_CENTER);
		mensaje.setAlignment(Element.ALIGN_CENTER);
		footer.setAlignment(Element.ALIGN_CENTER);

		try {
			// Agregamos el texto al documento.
			documento.add(imagen);

			documento.add(sup);
			documento.add(saltolinea);
			documento.add(titulo);
			documento.add(saltolinea);
			documento.add(contenido);
			documento.add(saltolinea);
			documento.add(footer);
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

	@RequestMapping(value = "informeAlmuerzos")
	public ModelAndView informeAlmuerzos() {
		ModelAndView vista = new ModelAndView();
		vista.setViewName("informeAlmuerzos");
		return vista;
	}

	@RequestMapping(value = "MostrarInformeAlmuerzos")
	public ModelAndView mostrarAlmuerzos(ModelAndView vista, HttpSession sesion, HttpServletRequest request,
			@RequestParam(value = "mes") int mes, @RequestParam(value = "year") int year) {

		if (mes == -1 || year == -1) {
			vista.setViewName("informeAlmuerzos");
			vista.addObject("seleccion", "Debe seleccionar los datos requeridos");
			return vista;
		}

		MenuDAO menuDAO = new MenuDAO();

		String fechaInicio = year + "-" + mes + "-01";
		String fechaFin = year + "-" + mes + "-" + dias(mes);

		if (!menuDAO.informeMenu(fechaInicio, fechaFin).isEmpty()) {
			vista.addObject("total", menuDAO.totalAlmuerzosVendidos(fechaInicio, fechaFin));
			vista.addObject("listaMenuVendidos", menuDAO.informeMenu(fechaInicio, fechaFin));
			vista.setViewName("MostrarInformeAlmuerzos");
			sesion = request.getSession(true);

			sesion.setAttribute("total", menuDAO.totalAlmuerzosVendidos(fechaInicio, fechaFin));
			sesion.setAttribute("mes", mes);
			sesion.setAttribute("year", year);
			sesion.setAttribute("fechaInicio", fechaInicio);
			sesion.setAttribute("fechaFin", fechaFin);
		}

		return vista;

	}

	@RequestMapping(value = "generarInforme")
	public ModelAndView generarInforme(ModelAndView vista, HttpSession sesion, HttpServletRequest request,
			HttpServletResponse response) throws BadElementException, MalformedURLException, IOException {
		sesion = request.getSession(true);
		MenuDAO menuDAO = new MenuDAO();

		LinkedList<MenuTO> menu = new LinkedList<>();
		menu = menuDAO.informeMenu((String) sesion.getAttribute("fechaInicio"),
				(String) sesion.getAttribute("fechaFin"));

		// Escribir PDF

		String FILE_NAME = "informe.pdf";

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
		Paragraph saltolinea = new Paragraph();
		Paragraph footer = new Paragraph();

		Image imagen = Image
				.getInstance("https://github.com/Nico2101/CasinoUBB2.0/blob/master/Screenshot_2.png?raw=true");

		imagen.scalePercent(75f);
		imagen.setAlignment(Element.ALIGN_JUSTIFIED);

		titulo.setFont(FontFactory.getFont("Times New Roman", 14, Font.BOLD));

		titulo.add("Informe Total Almuerzos Vendidos en el mes de " + mes((int) sesion.getAttribute("mes")) + " de "
				+ sesion.getAttribute("year"));

		titulo.setAlignment(Element.ALIGN_CENTER);

		// creamos la tabla con 3 columnas
		PdfPTable tabla = new PdfPTable(4);
		// añadimos contenido a las celdas

		// Cabecera
		tabla.addCell(new Paragraph("Nombre", FontFactory.getFont("arial", 14, Font.BOLD)));
		tabla.addCell(new Paragraph("Tipo", FontFactory.getFont("arial", 14, Font.BOLD)));
		tabla.addCell(new Paragraph("Precio", FontFactory.getFont("arial", 14, Font.BOLD)));
		tabla.addCell(new Paragraph("Total Vendidos", FontFactory.getFont("arial", 14, Font.BOLD)));

		// Contenido

		for (int i = 0; i < menu.size(); i++) {
			tabla.addCell(new Paragraph(menu.get(i).getNombre()));
			tabla.addCell(new Paragraph(menu.get(i).getTipo()));
			tabla.addCell(new Paragraph(("" + menu.get(i).getPrecio())));
			tabla.addCell(new Paragraph("" + menu.get(i).getCantRaciones()));
		}

		PdfPCell celdaFinal = new PdfPCell(new Paragraph("Total"));

		// Indicamos cuantas columnas ocupa la celda
		celdaFinal.setColspan(3);
		tabla.addCell(celdaFinal);
		tabla.addCell(new Paragraph("" + sesion.getAttribute("total")));

		try {
			// Agregamos el texto al documento.
			documento.add(imagen);

			documento.add(vacio1);

			documento.add(saltolinea);
			documento.add(titulo);
			documento.add(saltolinea);
			documento.add(vacio1);
			documento.add(tabla);
			documento.add(saltolinea);
			documento.add(footer);
			documento.add(vacio1);

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

		return vista;
	}

	// Función que retorna el numero de días que tiene el mes
	private int dias(int mes) {
		if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12)
			return 31;
		else if (mes == 2)
			return 28;
		else
			return 30;
	}

	// funcion que retorna el nombre del mes
	private String mes(int mes) {
		switch (mes) {
		case 1:
			return "Enero";

		case 2:
			return "Febrero";

		case 3:
			return "Marzo";

		case 4:
			return "Abril";

		case 5:
			return "Mayo";

		case 6:
			return "Junio";

		case 7:
			return "Julio";

		case 8:
			return "Agosto";

		case 9:
			return "Septiembre";

		case 10:
			return "Octubre";

		case 11:
			return "Noviembre";

		case 12:
			return "Diciembre";

		}
		return null;

	}
}
