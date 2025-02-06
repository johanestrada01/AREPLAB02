package edu.escuelaing;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.TreeMap;
import java.util.function.BiFunction;

public class PruebaServer extends HttpServer {

    private static TreeMap<String, Integer> grades = new TreeMap<>();

    public static void main(String[] args) {
        BiFunction<String, Integer, Integer> f1 = (name, grade) -> grades.put(name, grade);
        get("/app/insert", f1);
        System.out.println(grades); 
        BiFunction<String, Integer, String> f2 = (name, grade) -> {
            grades.put(name, grade);
            StringBuilder data = new StringBuilder("{");
            for (String key : grades.keySet()) {
                data.append("\"").append(key).append("\":").append(grades.get(key)).append(",");
            }
            if (!grades.isEmpty()) {
                data.deleteCharAt(data.length() - 1);
            }
            data.append("}");

            return data.toString();
        };
        get("/app/get", f2);
        staticFiles("webroot/public");
        try {
            startServer(35000);
        } catch (IOException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}