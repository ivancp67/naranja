package ceu.dam.ad.ejerciciosTema2.xml.ejercicio06.test;

import ceu.dam.ad.ejerciciosTema2.xml.ejercicio06.modelo.Largometraje;
import ceu.dam.ad.ejerciciosTema2.xml.ejercicio06.service.Ejercicio06Service;
import ceu.dam.ad.ejerciciosTema2.xml.ejercicio06.service.Ejercicio06ServiceImpl;

public class Test {

	public static void main(String[] args) {
		Ejercicio06Service service = new Ejercicio06ServiceImpl();
		try {
			Largometraje castillo = createCastillo();
			service.exportCastilloToXml("c:/temporal/castillo.xml", castillo);
		}catch (CastilloXmlException e) {
			e.printStackTrace();
		}
	}

}
