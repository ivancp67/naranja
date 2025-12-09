package ceu.dam.ad.ejerciciosTema2.xml.ejercicio05.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.modelo.Libro;
import ceu.dam.ad.ejerciciosTema2.xml.exceptions.XMLImportException;

public class Ejercicio05ServiceImpl implements Ejercicio05Service{

	@Override
	public List<Libro> importXML(String pathFile) throws XMLImportException {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			File file = new File(pathFile);
			Document document = builder.parse(file);
			
			Element libroTag = document.getDocumentElement();
			List<Libro> libroList = new ArrayList<>();
			
			
			
			return libroList;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}

	

}
