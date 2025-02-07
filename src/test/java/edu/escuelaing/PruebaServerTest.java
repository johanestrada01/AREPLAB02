package edu.escuelaing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PruebaServerTest {

    @Mock
    private HttpRequest mockRequest;
    
    @Mock
    private HttpResponse mockResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        PruebaServer.main(new String[]{}); // Inicializar el servidor y registrar rutas
    }

    @Test
    void testInsertGrade() {
        String requestBody = new JSONObject()
                .put("nombre", "Juan")
                .put("nota", 90)
                .toString();
        
        when(mockRequest.getQuery()).thenReturn(requestBody);
        
        int response = (int) HttpServer.services.get("/app/insert").apply(mockRequest, mockResponse);
        
        assertEquals(90, response);
        assertTrue(PruebaServer.grades.containsKey("Juan"));
        assertEquals(90, PruebaServer.grades.get("Juan"));
    }

    @Test
    void testGetGrades() throws IOException {
        // Insertar valores manualmente
        PruebaServer.grades.put("Ana", 85);
        PruebaServer.grades.put("Carlos", 78);

        String response = (String) HttpServer.services.get("/app/get").apply(mockRequest, mockResponse);
        
        Map<String, Integer> result = new ObjectMapper().readValue(response, Map.class);
        
        assertEquals(2, result.size());
        assertEquals(85, result.get("Ana"));
        assertEquals(78, result.get("Carlos"));
    }
}
