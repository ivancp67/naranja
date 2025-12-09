package ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.service;

import java.util.List;

import ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.modelo.Libro;
import ceu.dam.ad.ejerciciosTema2.xml.exceptions.XMLExportException;

public interface Ejercicio04Service {

	public void exportLibroXml(String ruta, Libro libro) throws LibroXmlException;

}