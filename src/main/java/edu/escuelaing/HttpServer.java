package edu.escuelaing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class HttpServer {

    private static Map<String, BiFunction<?, ?, ?>> services = new HashMap();
    private static String staticRute = "";

    public static void main(String[] args){
        try {
            staticFiles("webroot/public");
            startServer(35000);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static String readHtmlFile(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader("target/classes/edu/escuelaing/" + getStaticRoute() + filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }
        return contentBuilder.toString();
    }

    public static void startServer(int port) throws IOException, URISyntaxException{
        boolean running = true;
        ServerSocket serverSocket = new ServerSocket(port);
        while(running){
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            HttpRequest request = readInput(in);
            HttpResponse response = new HttpResponse();
            String output;
            if(request.getPath().contains("app")){
                output = processRequest(request, response);
            }
            else{
                String type = "html";
                if(request.getPath().length() == 1){
                    request.setPath("/index.html");
                }
                else{
                    type = request.getPath().split("\\.")[1];
                }
                output = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/" + type + "\r\n"
                + "Content-Length: " + readHtmlFile(request.getPath()).length() + "\r\n"
                + "\r\n"
                + readHtmlFile(request.getPath());
                if(type.equals("png")){
                    output = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: image/png \r\n"
                    + "Content-Length: " + readHtmlFile(request.getPath()).length() + "\r\n"
                    + "\r\n";
                    OutputStream outputStream = clientSocket.getOutputStream();
                    File file = new File("target/classes/edu/escuelaing/" + getStaticRoute() + request.getPath());
                    sendImage(output, outputStream, file);

                }
            }
            out.print(output);   
            out.close();
            clientSocket.close();
        }
    }

    private static void sendImage(String outputLine, OutputStream output, File file) throws IOException{
        output.write(outputLine.getBytes());
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }
    }

    private static HttpRequest readInput(BufferedReader in) throws IOException, URISyntaxException {
        String inputLine;
        URI uri = new URI("/index.html");
        boolean isFirstLine = true;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            if (isFirstLine) {
                String[] data = inputLine.split(" ");
                uri = new URI(data[1]);
                isFirstLine = false;
                System.out.println(data[0]);
            }
            if (inputLine.isEmpty()) {
                break;
            }
        }
        return new HttpRequest(uri.getPath(), uri.getQuery());
    }

    public static <T> void get(String route, BiFunction<?, ?, T> service) {
        services.put(route, service);
    }


    public static void staticFiles(String route){
        staticRute = route;
    }

    private static String getStaticRoute(){
        return staticRute;
    }
    
    private static String processRequest(HttpRequest req, HttpResponse resp) {
        BiFunction<HttpRequest, HttpResponse, ?> s = (BiFunction<HttpRequest, HttpResponse, ?>) services.get(req.getPath());
        System.out.println(req.getQuery() + " a " + req.getPath());
        return "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: application/json\r\n"
                        + "\r\n"
                        + "{\"response\":\""+ s.apply(req, resp) +"\"}";
    }

}
