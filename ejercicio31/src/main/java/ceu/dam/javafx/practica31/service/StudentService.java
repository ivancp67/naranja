package ceu.dam.javafx.practica31.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.print.DocFlavor.URL;

import ceu.dam.javafx.practica31.modelo.Usuario;


public class StudentService {

	private static final String URL_API = "http://localhost:8080/students";

    public String getAllStudents() throws Exception {
        URL url = new URL(URL_API);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        int status = conn.getResponseCode();
        if (status != 200) {
            throw new Exception("Error API: " + status);
        }

        BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream())
        );

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }

        conn.disconnect();
        return response.toString();
    }
}
