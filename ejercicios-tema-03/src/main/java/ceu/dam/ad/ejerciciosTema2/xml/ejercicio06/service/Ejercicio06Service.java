package ceu.dam.ad.ejerciciosTema2.xml.ejercicio06.service;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import ceu.dam.ad.ejerciciosTema2.xml.ejercicio06.modelo.Largometraje;
import ceu.dam.ad.ejerciciosTema2.xml.exceptions.XMLExportException;

public interface Ejercicio06Service {

	public void exportarXML(List<Largometraje> peliculas, String fichero) throws XMLExportException, ParserConfigurationException;

}