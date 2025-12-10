package ceu.dam.ad.castillo.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ceu.dam.ad.castillo.model.Caballero;
import ceu.dam.ad.castillo.model.Castillo;
import ceu.dam.ad.castillo.model.Dragon;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.dataformat.xml.XmlMapper;

public class CastilloXmlServiceImpl implements CastilloXmlService {

	@Override
	public void exportCastilloToXmlJackson(String fileName, Castillo castillo) throws CastilloXmlException {
		// XmlMapper xmlMapper = new XmlMapper();
		JsonMapper xmlMapper = new JsonMapper();
		File file = new File(fileName);
		xmlMapper.writeValue(file, castillo);
		
	}
	
	@Override
	public void exportCastillosToXmlJackson(String fileName, List<Castillo> castillos) throws CastilloXmlException {
		// XmlMapper xmlMapper = new XmlMapper();
		XmlMapper xmlMapper = new XmlMapper();
		Castillos castillosObject = new Castillos();
		castillosObject.setCastillos(castillos);
		File file = new File(fileName);
		xmlMapper.writeValue(file, castillosObject);
		
	}
	
	@Override
	public Castillo importCastilloToXmlJackson(String fileName, Castillo castillo) throws CastilloXmlException {
		XmlMapper xmlMapper = new XmlMapper();
		File file = new File(fileName);
		return xmlMapper.readValue(file, Castillo.class);
		
	}
	
	@Override
	public void exportCastilloToXml(String fileName, Castillo castillo) throws CastilloXmlException {
		try {
			// Crear documento
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			
			// Construir el documento
			Element castilloTag = document.createElement("castillo");
			document.appendChild(castilloTag);
			
			Element fosoTag = document.createElement("foso");
			castilloTag.appendChild(fosoTag);
			fosoTag.setTextContent(castillo.getFoso().toString());
			
			Element cabellerosTag = document.createElement("caballeros");
			castilloTag.appendChild(cabellerosTag);
			List<Caballero> caballeros = castillo.getCaballeros();
			for (Caballero caballero : caballeros) {
				Element cabelleroTag = document.createElement("caballero");
				cabellerosTag.appendChild(cabelleroTag);
				Element nombreTag = document.createElement("nombre");
				cabelleroTag.appendChild(nombreTag);
				nombreTag.setTextContent(caballero.getNombre());
				Element caballoTag = document.createElement("caballo");
				cabelleroTag.appendChild(caballoTag);
				caballoTag.setTextContent(caballero.getCaballo());
				Element escuderoTag = document.createElement("escudero");
				cabelleroTag.appendChild(escuderoTag);
				escuderoTag.setTextContent(caballero.getEscudero());
				Element edadTag = document.createElement("edad");
				cabelleroTag.appendChild(edadTag);
				edadTag.setTextContent(caballero.getEdad().toString());
			}
			
			Element dragonesTag = document.createElement("caballeros");
			castilloTag.appendChild(dragonesTag);
			List<Dragon> dragones = castillo.getDragones();
		
			
			
			
			
			
			// Exportar documento a fichero
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			File file = new File(fileName);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);
			
		} catch (Exception e) {
			throw new CastilloXmlException("Error generando",e);
		}
	}

	@Override
	public Castillo importCastilloFromXml(String fileName) throws CastilloXmlException{
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			File file = new File(fileName);
			Document document = builder.parse(file);
			
			Element castilloTag = document.getDocumentElement();
			Castillo castillo = new Castillo();
			
			Element fosoTag = (Element) castilloTag.getElementsByTagName("foso").item(0);
			castillo.setFoso(Boolean.parseBoolean(fosoTag.getTextContent()));
			
			Element caballerosTag = (Element) castilloTag.getElementsByTagName("caballeros").item(0);
			List<Caballero> caballeros = new ArrayList<>();
			castillo.setCaballeros(caballeros);
			
			
			NodeList caballeroTagList = caballerosTag.getElementsByTagName("caballero");
			for (int i = 0; i < caballeroTagList.getLength(); i++) {
				Element caballeroTag = (Element) caballeroTagList.item(i);
				Caballero caballero = new Caballero();
				castillo.getCaballeros().add(caballero);
				
				Element nombreTag = (Element) caballeroTag.getElementsByTagName("nombre").item(0);
				caballero.setNombre(nombreTag.getTextContent());
				Element escuderoTag = (Element) caballeroTag.getElementsByTagName("escudero").item(0);
				caballero.setEscudero(escuderoTag.getTextContent());
				Element caballoTag = (Element) caballeroTag.getElementsByTagName("caballo").item(0);
				caballero.setCaballo(caballoTag.getTextContent());
				Element edadTag = (Element) caballeroTag.getElementsByTagName("edad").item(0);
				caballero.setEdad(Integer.valueOf(edadTag.getTextContent()));
				
			}
			/*
			Element dragonesTag = (Element) castilloTag.getElementsByTagName("dragones").item(0);
			List<Dragon> dragones = new ArrayList<>();
			castillo.setDragones(dragones);
			
			
			NodeList dragonTagList = dragonesTag.getElementsByTagName("dragon");
			for (int i = 0; i < dragonTagList.getLength(); i++) {
				Element dragonTag = (Element) dragonTagList.item(i);
				Dragon dragon = new Dragon();
				castillo.getDragones().add(dragon);
				
				Element nombreTag = (Element) dragonTag.getElementsByTagName("nombre").item(0);
				dragon.setAlas(Boolean.valueOf(dragonTag.getAttribute("alas")));
				Element colorTag = (Element) dragonTag.getElementsByTagName("color").item(0);
				dragon.setColor(dragonTag.getAttribute("color"));
				Element razaTag = (Element) dragonTag.getElementsByTagName("raza").item(0);
				dragon.setRaza(dragonTag.getAttribute("raza"));
				Element poderTag = (Element) dragonTag.getElementsByTagName("poder").item(0);
				dragon.setPoder(Integer.valueOf(edadTag.getTextContent()));
				
			}
			*/
			return castillo;
		} catch (Exception e) {
			throw new CastilloXmlException("Error generando",e);
		}
		
	}

}
