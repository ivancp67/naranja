package ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.service;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.modelo.Edicion;
import ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.modelo.Libro;

public class Ejercicio04ServiceImpl  implements Ejercicio04Service{
	
	public void exportLibroXml(String ruta, Libro libro) throws LibroXmlException {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			
			Element libroTag = doc.createElement("libro");
			doc.appendChild(libroTag);
			
			Element isbnTag = doc.createElement("isbn");
			isbnTag.setTextContent(libro.getIsbn().toString());
			libroTag.appendChild(isbnTag);
			
			// <titulo>
			Element tituloTag = doc.createElement("titulo");
			tituloTag.setTextContent(libro.getTitulo());
			libroTag.appendChild(tituloTag);
			
			// <autores>
			Element autoresTag = doc.createElement("autores");
			libroTag.appendChild(autoresTag);
			
			for (String autor : libro.getAutores()) {
				Element autorTag = doc.createElement("autor");
				autorTag.setTextContent(autor);
				autoresTag.appendChild(autorTag);
			}
			
			Element edicionesTag = doc.createElement("ediciones");
			libroTag.appendChild(edicionesTag);
			
			for (Edicion ed : libro.getEdiciones()) {
				
				Element edicionTag = doc.createElement("edicion");
				edicionesTag.appendChild(edicionTag);
				
				Element anioTag = doc.createElement("año");
				anioTag.setTextContent(ed.getAño().toString());
				edicionTag.appendChild(anioTag);
				
				Element editorialTag = doc.createElement("editorial");
				editorialTag.setTextContent(ed.getEditorial());
				edicionTag.appendChild(editorialTag);
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			File file = new File(ruta);
			StreamResult result = new StreamResult(file);
			//StreamResult result = new StreamResult(new File(ruta));
			transformer.transform(source, result);
		} catch (Exception e) {
			throw new LibroXmlException("Error al generar el XML del libro", e);
		}
	}

}