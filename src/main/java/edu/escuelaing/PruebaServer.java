package edu.escuelaing;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.TreeMap;
import org.json.JSONObject;
import java.util.function.BiFunction;
import com.fasterxml.jackson.databind.ObjectMapper; // Importa Jackson

public class PruebaServer extends HttpServer {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static TreeMap<String, Integer> grades = new TreeMap<>();

    public static void main(String[] args) {
        BiFunction<HttpRequest, HttpResponse, Integer> f1 = (request, response) -> {
            String requestBody = request.getQuery();
            JSONObject json = new JSONObject(requestBody);
            String nombre = json.getString("nombre");
            int nota = json.getInt("nota");
            grades.put(nombre, nota);
            return nota;
        };
        get("/app/insert", f1);
        
        BiFunction<HttpRequest, HttpResponse, String> f2 = (request, response) -> {
            try {
                return objectMapper.writeValueAsString(grades);
            } catch (IOException e) {
                e.printStackTrace();
                return "{}"; // Devuelve un JSON vacío en caso de error
            }
        };
        get("/app/get", f2);
        
        staticFiles("webroot/public");
        try {
            startServer(35000);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
