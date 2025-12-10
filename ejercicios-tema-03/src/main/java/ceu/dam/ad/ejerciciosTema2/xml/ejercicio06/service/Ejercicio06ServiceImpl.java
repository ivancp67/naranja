package ceu.dam.ad.ejerciciosTema2.xml.ejercicio06.service;

import java.io.File;
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

import ceu.dam.ad.ejerciciosTema2.xml.ejercicio06.modelo.Largometraje;
import ceu.dam.ad.ejerciciosTema2.xml.ejercicio06.modelo.Persona;
import ceu.dam.ad.ejerciciosTema2.xml.exceptions.XMLExportException;

public class Ejercicio06ServiceImpl implements Ejercicio06Service{

	@Override
	public void exportarXML(List<Largometraje> peliculas, String fichero) throws XMLExportException, ParserConfigurationException {
		try {
            // Crear el documento XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            
            // Elemento raíz
            Element root = document.createElement("peliculas");
            document.appendChild(root);
            
            // Iterar sobre cada película
            for (Largometraje pelicula : peliculas) {
                Element peliculaElement = document.createElement("pelicula");
                root.appendChild(peliculaElement);
                
                // Título
                Element titulo = document.createElement("titulo");
                titulo.setTextContent(pelicula.getTitulo());
                peliculaElement.appendChild(titulo);
                
                // Duración
                Element duracion = document.createElement("duracion");
                duracion.setTextContent(pelicula.getDuracion().toString());
                peliculaElement.appendChild(duracion);
                
                // Año
                Element año = document.createElement("año");
                año.setTextContent(pelicula.getAño().toString());
                peliculaElement.appendChild(año);
                
                // Artistas
                Element artistas = document.createElement("artistas");
                peliculaElement.appendChild(artistas);
            }
            
            // Escribir el documento a un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            File file = new File(fichero);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	

}
