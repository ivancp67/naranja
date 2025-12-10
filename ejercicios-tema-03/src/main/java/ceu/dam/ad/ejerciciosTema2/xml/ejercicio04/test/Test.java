package ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.test;

import java.util.ArrayList;
import java.util.List;

import ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.modelo.Edicion;
import ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.modelo.Libro;
import ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.service.Ejercicio04Service;
import ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.service.Ejercicio04ServiceImpl;
import ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.service.LibroXmlException;

public class Test {

public static void main(String[] args) {
		
		Ejercicio04Service service = new Ejercicio04ServiceImpl();
		try {
			Libro castillo = createLibro();
			service.exportLibroXml("c:/temporal/castillo.xml", castillo);
		
		} catch (LibroXmlException e) {
			e.printStackTrace();
		}
	}

	public static Libro createLibro() {
		Libro libros = new Libro();

	    // ============================================================
	    // ======================   LIBRO 1   ==========================
	    // ============================================================

	    Libro l1 = new Libro();
	    l1.setIsbn(405297764);
	    l1.setTitulo("mxqzi cpavqs");

	    // Autores
	    List<String> autores1 = new ArrayList<>();
	    autores1.add("hce icpoeomqjq");
	    l1.setAutores(autores1);

	    // Ediciones
	    List<Edicion> ediciones1 = new ArrayList<>();

	    Edicion e11 = new Edicion();
	    e11.setAño(1969);
	    e11.setEditorial("qpce qlpwfiawc ntisjojos");

	    Edicion e12 = new Edicion();
	    e12.setAño(1965);
	    e12.setEditorial("esagghkk kplevsmpgn webkpczh");

	    ediciones1.add(e11);
	    ediciones1.add(e12);
	    l1.setEdiciones(ediciones1);

	    libros.add(l1);



	    // ============================================================
	    // ======================   LIBRO 2   ==========================
	    // ============================================================

	    Libro l2 = new Libro();
	    l2.setIsbn(353157915);
	    l2.setTitulo("auf ozgni dmmdc");

	    // Autores
	    List<String> autores2 = new ArrayList<>();
	    autores2.add("enlrp pwbowgh");
	    l2.setAutores(autores2);

	    // Ediciones
	    List<Edicion> ediciones2 = new ArrayList<>();

	    Edicion e21 = new Edicion();
	    e21.setAño(1958);
	    e21.setEditorial("kgxkc eioiflpofo xzwmltxiw");

	    ediciones2.add(e21);
	    l2.setEdiciones(ediciones2);

	    libros.add(l2);



	    // ============================================================
	    // ======================   LIBRO 3   ==========================
	    // ============================================================

	    Libro l3 = new Libro();
	    l3.setIsbn(280516166);
	    l3.setTitulo("uxwrexlae ebefrvqvb");

	    // Autores
	    List<String> autores3 = new ArrayList<>();
	    autores3.add("hjxufk gdknwt");
	    autores3.add("woungd tytmdukjbi");
	    l3.setAutores(autores3);

	    // Ediciones
	    List<Edicion> ediciones3 = new ArrayList<>();

	    Edicion e31 = new Edicion();
	    e31.setAño(1991);
	    e31.setEditorial("adt myjlxww zqzmpmb");

	    ediciones3.add(e31);
	    l3.setEdiciones(ediciones3);

	    libros.add(l3);



	    // ============================================================
	    // ======================   LIBRO 4   ==========================
	    // ============================================================

	    Libro l4 = new Libro();
	    l4.setIsbn(796113700);
	    l4.setTitulo("jzrdgc zbimxbie ktp");

	    // Autores
	    List<String> autores4 = new ArrayList<>();
	    autores4.add("xdvkhmtles vodxgl");
	    autores4.add("rekb uzsk");
	    l4.setAutores(autores4);

	    // Ediciones
	    List<Edicion> ediciones4 = new ArrayList<>();

	    Edicion e41 = new Edicion();
	    e41.setAño(1964);
	    e41.setEditorial("bwruhflni avmlw ovamvlugj");

	    Edicion e42 = new Edicion();
	    e42.setAño(1992);
	    e42.setEditorial("ciinr iwpfl");

	    ediciones4.add(e41);
	    ediciones4.add(e42);
	    l4.setEdiciones(ediciones4);

	    libros.add(l4);



	    // ============================================================
	    // ======================   LIBRO 5   ==========================
	    // ============================================================

	    Libro l5 = new Libro();
	    l5.setIsbn(764885581);
	    l5.setTitulo("najobmtdkl rmmewtv");

	    // Autores
	    List<String> autores5 = new ArrayList<>();
	    autores5.add("bzmjwi tdgppavwbp");
	    l5.setAutores(autores5);

	    // Ediciones
	    List<Edicion> ediciones5 = new ArrayList<>();

	    Edicion e51 = new Edicion();
	    e51.setAño(1961);
	    e51.setEditorial("igiwcch ddgcccu");

	    ediciones5.add(e51);
	    l5.setEdiciones(ediciones5);

	    libros.add(l5);



	    return libros;
		
	}

}
