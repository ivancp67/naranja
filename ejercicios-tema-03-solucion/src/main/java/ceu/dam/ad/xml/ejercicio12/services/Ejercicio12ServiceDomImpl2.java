package ceu.dam.ad.xml.ejercicio12.services;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ceu.dam.ad.xml.ejercicio12.modelo.Catalogo;

public class Ejercicio12ServiceDomImpl2 {

	public void exportXML(Catalogo catalogo, String pathFile) throws CatalogoXMLException {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document xml = builder.newDocument();
			
			Element catalogoTag = xml.createElement("catálogo");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
